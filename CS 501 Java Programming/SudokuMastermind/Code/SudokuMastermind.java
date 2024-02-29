package com.example.sudokumastermind;
/*
CS 501 
Projectile Simulation
Group : Project 4
Vijay Khot
&
Sai Hitesh M R
 */

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Optional;
import java.util.Random;

public class SudokuMastermind extends Application {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private TextField[][] gridValues = new TextField[SIZE][SIZE];
    private int mistakeCounter = 0;
    private int[][] solutionCopy;
    private int[][] originalSolution;
    Label mistakeLabel;
    private TextField lastSelectedCell;
    private Timeline flickerTimeline;
    private Color[][] originalCellColors;
    private Timeline timerTimeline;
    private Duration elapsedTime;
    private Label timerLabel;
    Button hintButton;
    private int maxMistakesAllowed;
    private int maxHintsAllowed;
    private int hintCounter;
    private Difficulty difficulty;


    @Override
    public void start(Stage primaryStage) {
        // Create a welcome scene
        VBox welcomeLayout = createWelcomeLayout(primaryStage);
        Scene welcomeScene = new Scene(welcomeLayout, 500, 550);

        // Set the welcome scene as the initial scene
        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Welcome to Sudoku Mastermind");
        primaryStage.show();
    }

    private VBox createWelcomeLayout(Stage primaryStage) {
        VBox welcomeLayout = new VBox();
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setSpacing(0);
        welcomeLayout.setPrefWidth(750);
        welcomeLayout.setPrefHeight(550);

        // Add a welcome label
        Label welcomeLabel = new Label("Welcome to Sudoku Mastermind!");
        welcomeLabel.setStyle("-fx-font-size: 20; -fx-font-weight: BOLD");

        // Add a start button
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 20");
        startButton.setOnAction(e -> {
            // Launch the main game scene
            initializeScene(primaryStage, Difficulty.MEDIUM);
        });

        // Add an image if desired
        ImageView welcomeImage = new ImageView(new Image("6641382.jpg"));
        welcomeImage.setPreserveRatio(true);
        welcomeImage.setFitHeight(450);
        welcomeImage.setFitWidth(750);
        // Add components to the welcome layout
        welcomeLayout.getChildren().addAll(welcomeLabel, startButton, welcomeImage);
        return welcomeLayout;
    }

    private void initializeScene(Stage primaryStage, Difficulty difficulty) {
        GridPane gridPane = createSudokuGrid();
        this.difficulty = difficulty;
        generateSudoku(difficulty);

        VBox buttonBox = createButtonBox();
        // Initialize the timer
        timerTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateElapsedTime()));
        timerTimeline.setCycleCount(Animation.INDEFINITE);

        // Initialize the elapsed time
        elapsedTime = Duration.ZERO;

        // Initialize the timer
        timerTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateElapsedTime()));
        timerTimeline.setCycleCount(Animation.INDEFINITE);
        timerTimeline.play(); // Start the timer

        // Initialize the maxMistakesAllowed based on the selected difficulty
        switch (difficulty) {
            case EASY:
                maxMistakesAllowed = 5;
                maxHintsAllowed = 3;
                break;
            case MEDIUM:
                maxMistakesAllowed = 3;
                maxHintsAllowed = 2;
                break;
            case HARD:
                maxMistakesAllowed = 1;
                maxHintsAllowed = 1;
                break;
        }
        hintCounter = maxHintsAllowed;

        // Set the initial value of the mistakeLabel
        mistakeLabel.setText("Mistakes: 0/" + maxMistakesAllowed);

        // Set the initial text on the hintButton
        updateHintButtonText();

        HBox mainLayout = new HBox(gridPane, buttonBox);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout, 750, 550);

        // Add event filter to handle hint button
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.H) {
                // Handle the hint button (H key)
                revealHint();
            }
        });

        primaryStage.setTitle("Sudoku Mastermind");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private GridPane createSudokuGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        int cellSize = 60; // Set the size of each cell

        gridValues = new TextField[SIZE][SIZE]; // Initialize the array
        originalCellColors = new Color[SIZE][SIZE]; // Initialize the array
        Color[][] editableCellColors = new Color[SIZE][SIZE]; // Separate array for editable cell colors

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final int rowIndex = i; // Local final variable
                final int colIndex = j; // Local final variable

                TextField cell = new TextField();
                cell.setPrefWidth(cellSize);
                cell.setPrefHeight(cellSize);
                cell.setAlignment(Pos.CENTER);
                cell.setStyle("-fx-border-color: black; -fx-border-width: 0.5; -fx-font-size: 30; -fx-font-weight: BOLD");

                // Save the original color of the cell
                int subgridRow = i / SUBGRID_SIZE;
                int subgridCol = j / SUBGRID_SIZE;
                originalCellColors[i][j] = getSubgridColor(subgridRow, subgridCol);

                if (cell.isEditable()) {
                    editableCellColors[i][j] = getSubgridColor(subgridRow, subgridCol);
                } else {
                    originalCellColors[i][j] = getSubgridColor(subgridRow, subgridCol);
                }

                // Add event filter to allow only numbers 1 to 9
                cell.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                    String character = event.getCharacter();
                    int row = GridPane.getRowIndex(cell);
                    int col = GridPane.getColumnIndex(cell);

                    if (!character.matches("[1-9]")) {
                        event.consume(); // Ignore the input
                    } else {
                        int inputNumber = Integer.parseInt(character);
                        int solutionNumber = getSolutionNumber(row, col);

                        if (inputNumber != solutionNumber) {
                            // Input does not match the solution, handle the mistake
                            handleMistake(cell, false);
                        } else {
                            // Input matches the solution, handle the correct input
                            handleCorrectInput(cell);
                        }
                    }
                });

                // Add focus event to handle cell selection
                cell.focusedProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal) {
                        lastSelectedCell = cell;
                        // Highlight the cell with a different color when selected
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: lightyellow;");
                    } else {
                        // Reset the background color when focus is lost
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: transparent;");
                    }
                });

                // Add hover effect to highlight selectable cells
                cell.setOnMouseEntered(e -> {
                    if (cell.isEditable()) {
                        // Highlight the cell with a different color when the mouse hovers over it
                        cell.setStyle(cell.getStyle() + "; -fx-background-color: lightyellow;");
                    }
                });

                cell.setOnMouseExited(e -> {
                    // Reset the background color when the mouse exits the cell
                    if (cell.isEditable()) {
                        cell.setStyle(cell.getStyle() + String.format("; -fx-background-color: %s;", toRGBCode(editableCellColors[rowIndex][colIndex])));
                    } else {
                        cell.setStyle(cell.getStyle() + String.format("; -fx-background-color: %s;", toRGBCode(originalCellColors[rowIndex][colIndex])));
                    }
                });

                Color subgridColor = getSubgridColor(subgridRow, subgridCol);
                cell.setStyle(cell.getStyle() + String.format("; -fx-background-color: %s;", toRGBCode(subgridColor)));

                gridPane.add(cell, j, i);
                GridPane.setRowIndex(cell, i); // Set the row index explicitly
                GridPane.setColumnIndex(cell, j);
                gridValues[i][j] = cell; // Assign the cell to the array
            }
        }
        // Set grid lines visible
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }

    // Method to update timer
    private void updateElapsedTime() {
        elapsedTime = elapsedTime.add(Duration.seconds(1));
        long totalSeconds = (long) elapsedTime.toSeconds();
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        String timeString = String.format("Time: %02d:%02d", minutes, seconds);
        timerLabel.setText(timeString);
    }
    // Helper method to convert Color to RGB code
    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
    // Helper method to get color for each 3x3 subgrid
    private Color getSubgridColor(int row, int col) {
        if ((row + col) % 2 == 0) {
            return Color.LIGHTGRAY;
        } else {
            return Color.WHITE;
        }
    }
    // Method to create button box which contains all buttons and labels
    private VBox createButtonBox() {
        VBox buttonBox = new VBox();
        buttonBox.setAlignment(Pos.TOP_LEFT);
        buttonBox.setSpacing(10);

        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> showDifficultyDialog());
        newGameButton.setPrefWidth(200);
        newGameButton.setPrefHeight(60);
        newGameButton.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: skyblue; -fx-font-size: 24");

        Button checkSolutionButton = new Button("Check Solution");
        checkSolutionButton.setOnAction(e -> checkSolution());
        checkSolutionButton.setPrefWidth(200);
        checkSolutionButton.setPrefHeight(60);
        checkSolutionButton.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: lightgreen; -fx-font-size: 24");

        Button undoButton = new Button("Undo");
        undoButton.setOnAction(e -> undoLastInput());
        undoButton.setPrefWidth(200);
        undoButton.setPrefHeight(60);
        undoButton.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: lightcoral; -fx-font-size: 24");

        // Check difficulty level before allowing undo
        if (difficulty == Difficulty.HARD) {
            undoButton.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Undo Disabled");
                alert.setHeaderText("Undo is Disabled for Hard Difficulty");
                alert.setContentText("Undo functionality is disabled for Hard difficulty level.");
                alert.showAndWait();
            });
        }
        // Hint Button:
        hintButton = new Button("Hints: " + hintCounter);
        hintButton.setOnAction(e -> revealHint());
        hintButton.setPrefWidth(200);
        hintButton.setPrefHeight(60);
        hintButton.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: lightyellow; -fx-font-size: 24");

        // Label for displaying the difficulty level
        Label difficultyLabel = new Label("Difficulty: " + difficulty.toString());
        difficultyLabel.setStyle("-fx-font-size: 24; -fx-text-fill: black;");
        difficultyLabel.setAlignment(Pos.CENTER);
        difficultyLabel.setPrefWidth(200);
        difficultyLabel.setPrefHeight(60);

        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(0);

        mistakeLabel = new Label("Mistakes: 0");
        mistakeLabel.setStyle("-fx-font-size: 16; -fx-text-fill: black; -fx-background-color: red");
        mistakeLabel.setAlignment(Pos.CENTER);
        mistakeLabel.setPrefWidth(100);
        mistakeLabel.setPrefHeight(50);

        timerLabel = new Label("Time: 00:00");
        timerLabel.setStyle("-fx-text-fill: black; -fx-font-size: 16; -fx-background-color: red");
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setPrefWidth(100);
        timerLabel.setPrefHeight(50);

        HBox labelAndSpacerBox = new HBox();
        labelAndSpacerBox.setAlignment(Pos.CENTER);
        labelAndSpacerBox.setSpacing(100);

        labelAndSpacerBox.getChildren().addAll(labelBox, new Region());

        ImageView mainPageImage = new ImageView(new Image("SL-021622-48580-22.jpg"));
        mainPageImage.setFitWidth(200);
        mainPageImage.setFitHeight(500);
        mainPageImage.setPreserveRatio(true);

        labelBox.getChildren().addAll(mistakeLabel, timerLabel);
        buttonBox.getChildren().addAll(undoButton, hintButton, checkSolutionButton, newGameButton, labelBox, labelAndSpacerBox, difficultyLabel, mainPageImage);
        return buttonBox;
    }


    private void revealHint() {
        if (hintCounter > 0 && lastSelectedCell != null && lastSelectedCell.isEditable()) {
            int row = GridPane.getRowIndex(lastSelectedCell);
            int col = GridPane.getColumnIndex(lastSelectedCell);

            if (row >= 0 && col >= 0 && row < SIZE && col < SIZE) {
                // Reveal the solution for the selected cell
                lastSelectedCell.setText(Integer.toString(originalSolution[row][col]));

                // Mark the revealed cell as non-editable
                lastSelectedCell.setEditable(false);
                lastSelectedCell.setStyle(lastSelectedCell.getStyle() + "; -fx-background-color: lightgray;");

                // Decrement the hint counter
                hintCounter--;

                // Update the text on the hint button
                updateHintButtonText();

                // Check if the player has used all hints and handle the end-game scenario
                if (hintCounter == 0) {
                    handleNoHintsLeft();
                }
            }
        }
    }

    private void updateHintButtonText() {
        // Update the text on the hint button
        hintButton.setText("Hints: " + hintCounter + "/" + maxHintsAllowed);
    }
    private void handleNoHintsLeft() {
        Alert noHintsAlert = new Alert(Alert.AlertType.INFORMATION);
        noHintsAlert.setTitle("No Hints Remaining");
        noHintsAlert.setHeaderText("No Hints Left");
        noHintsAlert.setContentText("You have used all available hints.");
        // Add OK button to the alert
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        noHintsAlert.getButtonTypes().setAll(okButton);
        // Show the alert and handle the result
        noHintsAlert.showAndWait();
    }

    private void undoLastInput() {
        if (lastSelectedCell != null && lastSelectedCell.isEditable()) {
            String currentText = lastSelectedCell.getText();
            if (!currentText.isEmpty()) {
                // Undo the last input
                lastSelectedCell.setText("");

                // Decrement the mistake counter
                if (mistakeCounter > 0) {
                    mistakeCounter--;
                    mistakeLabel.setText("Mistakes: " + mistakeCounter);
                }
            }
        }
    }

    private boolean solveSudokuRecursive(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(grid, i, j, num)) {
                            grid[i][j] = num;
                            if (solveSudokuRecursive(grid)) {
                                return true;
                            }
                            grid[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSafe(int[][] grid, int row, int col, int num) {
        return !usedInRow(grid, row, num) &&
                !usedInColumn(grid, col, num) &&
                !usedInSubgrid(grid, row - row % SUBGRID_SIZE, col - col % SUBGRID_SIZE, num);
    }

    private boolean usedInRow(int[][] grid, int row, int num) {
        for (int j = 0; j < SIZE; j++) {
            if (grid[row][j] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInColumn(int[][] grid, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInSubgrid(int[][] grid, int startRow, int startCol, int num) {
        for (int i = 0; i < SUBGRID_SIZE; i++) {
            for (int j = 0; j < SUBGRID_SIZE; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return true;
                }
            }
        }
        return false;
    }
    private void generateSudoku(Difficulty difficulty) {
        // Fade-in animation
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), gridValues[0][0].getParent());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        // Generate the solution
        int[][] solution = new int[SIZE][SIZE];
        originalSolution = new int[SIZE][SIZE];

        if (solveSudokuRecursive(solution)) {
            // Copy the solution to both solutionCopy and originalSolution
            solutionCopy = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                System.arraycopy(solution[i], 0, solutionCopy[i], 0, SIZE);
                System.arraycopy(solution[i], 0, originalSolution[i], 0, SIZE);
            }

            // Determine the number of cells to hide based on difficulty level
            int cellsToHide = 0;
            switch (difficulty) {
                case EASY:
                    cellsToHide = 30; // Adjust this value based on your preferences
                    break;
                case MEDIUM:
                    cellsToHide = 40; // Adjust this value based on your preferences
                    break;
                case HARD:
                    cellsToHide = 50; // Adjust this value based on your preferences
                    break;
            }
            Random random = new Random();
            // Remove numbers from the solutionCopy to create a puzzle
            int hiddenCellsCount = 0;
            while (hiddenCellsCount < cellsToHide) {
                int i = random.nextInt(SIZE);
                int j = random.nextInt(SIZE);

                if (solutionCopy[i][j] != 0) {
                    solutionCopy[i][j] = 0;
                    gridValues[i][j].setText("");
                    gridValues[i][j].setEditable(true);
                    hiddenCellsCount++;
                }
            }
            // Set the remaining cells as non-editable
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (solutionCopy[i][j] != 0) {
                        gridValues[i][j].setText(Integer.toString(originalSolution[i][j]));
                        gridValues[i][j].setEditable(false);
                    }
                }
            }
        } else {
            // If a valid solution cannot be found, retry
            generateSudoku(difficulty);
        }
    }
    private int getSolutionNumber(int row, int col) {
        return originalSolution[row][col];
    }

    // Enum for difficulty levels
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }
    private void handleMistake(TextField cell, boolean clearHighlight) {
        // Increment the mistake counter
        mistakeCounter++;
        // Update the mistake label text
        mistakeLabel.setText("Mistakes: " + mistakeCounter + "/" + maxMistakesAllowed);
        // Check if the maximum allowed mistakes have been reached
        if (mistakeCounter >= maxMistakesAllowed) {
            handleGameOver();
        }
        // Highlight the cell with red color
        cell.setStyle(cell.getStyle() + "; -fx-background-color: lightcoral;");
        cell.setStyle(cell.getStyle() + "; -fx-text-fill: red;");
        // Clear the highlight after a delay
        if (clearHighlight) {
            clearHighlightAfterDelay(cell);
        }
    }

    private void handleCorrectInput(TextField cell) {
        // Highlight the cell with green color
        cell.setStyle(cell.getStyle() + "; -fx-background-color: lightgreen;");
        cell.setStyle(cell.getStyle() + "; -fx-text-fill: #119cf5;");

        // Clear the highlight after a delay
        clearHighlightAfterDelay(cell);
        cell.setStyle(cell.getStyle() + "; -fx-text-fill: #119cf5;");

    }
    private void clearHighlightAfterDelay(TextField cell) {
        // Fade-out animation
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), cell);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        // Clear the highlight after a short delay
        PauseTransition pause = new PauseTransition(Duration.seconds(1)); // You can adjust the duration
        pause.setOnFinished(event -> {
            cell.setStyle(cell.getStyle() + "; -fx-background-color: transparent;");
            fadeTransition.stop(); // Stop the fade-out animation
            cell.setOpacity(1); // Reset the opacity to avoid issues with subsequent animations
        });
        pause.play();
    }
    private void stopTimer() {
        timerTimeline.stop();
    }
    private void showDifficultyDialog() {
        Dialog<Pair<String, Difficulty>> dialog = new Dialog<>();
        dialog.setTitle("Choose Difficulty");
        dialog.setHeaderText("Select the difficulty level:");

        ButtonType buttonTypeOk = new ButtonType("OK");
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ChoiceBox<Difficulty> difficultyChoiceBox = new ChoiceBox<>();
        difficultyChoiceBox.getItems().addAll(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD);
        difficultyChoiceBox.setValue(Difficulty.EASY);
        grid.add(new Label("Difficulty:"), 0, 0);
        grid.add(difficultyChoiceBox, 1, 0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeOk) {
                return new Pair<>("OK", difficultyChoiceBox.getValue());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            if (result.getKey().equals("OK")) {
                // Stop the timer if a new game is started
                stopTimer();

                // Restart a new game with the selected difficulty level
                resetGame(result.getValue());
            }
        });
    }

    private void resetGame(Difficulty difficulty) {
        // Reset variables and generate a new Sudoku puzzle
        mistakeCounter = 0;
        mistakeLabel.setText("Mistakes: 0/" + maxMistakesAllowed);
        // Reset the hint counter to its initial value
        hintCounter = maxHintsAllowed;
        // Update the text on the hint button
        updateHintButtonText();
        // Stop the existing timer
        stopTimer();
        initializeScene((Stage) mistakeLabel.getScene().getWindow(), difficulty);
    }

    private void checkSolution() {
        int[][] currentGrid = new int[SIZE][SIZE];
        boolean puzzleIncomplete = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String value = gridValues[i][j].getText();
                currentGrid[i][j] = value.isEmpty() ? 0 : Integer.parseInt(value);

                if (value.isEmpty()) {
                    // Highlight blank cells temporarily
                    highlightBlankCell(gridValues[i][j]);
                    puzzleIncomplete = true;
                }
            }
        }
        if (puzzleIncomplete) {
            // Puzzle is incomplete
            // You can show an alert or perform additional actions if needed
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Puzzle");
            alert.setHeaderText("Puzzle is not complete");
            alert.setContentText("Please fill in all the cells before checking the solution.");
            alert.showAndWait();
        } else {
            // Puzzle is complete, proceed with the solution check
            if (isGridSolved(currentGrid)) {
                // Stop the timer
                stopTimer();
                // Apply continuous animation to all cells
                applyFlickerAnimation();
                // Puzzle is solved correctly
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations");
                alert.setHeaderText("Puzzle Solved");
                alert.setContentText("Congratulations! You have successfully solved the puzzle.");
                alert.showAndWait();
                // Add buttons to the alert for restarting a new game or exiting the application
                ButtonType newGameButton = new ButtonType("New Game", ButtonBar.ButtonData.OK_DONE);
                ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(newGameButton, exitButton);
                // Show the alert and handle the result
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == newGameButton) {
                    // Stop the continuous animation
                    stopFlickerAnimation();
                    // Restart a new game
                    showDifficultyDialog();
                } else {
                    // Exit the application
                    System.exit(0);
                }
            } else {
                // Puzzle is not solved correctly
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Try Again");
                alert.setHeaderText("Puzzle Not Solved");
                alert.setContentText("Please check your solution. The puzzle is not solved correctly.");
                alert.showAndWait();
            }
        }
    }
    private void applyFlickerAnimation() {
        flickerTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> flickerCells(true)),
                new KeyFrame(Duration.millis(200), e -> flickerCells(false)),
                new KeyFrame(Duration.millis(400), e -> flickerCells(true)),
                new KeyFrame(Duration.millis(600), e -> flickerCells(false)),
                new KeyFrame(Duration.millis(800), e -> flickerCells(true)),
                new KeyFrame(Duration.millis(1000), e -> flickerCells(false)),
                new KeyFrame(Duration.millis(1200), e -> flickerCells(true)),
                new KeyFrame(Duration.millis(1400), e -> flickerCells(false))
        );
        flickerTimeline.setCycleCount(Animation.INDEFINITE);
        flickerTimeline.play();
    }
    private void stopFlickerAnimation() {
        // Stop the timeline to halt the flickering animation
        flickerTimeline.stop();
    }
    private void flickerCells(boolean visible) {
        // Method to toggle the visibility of all cells
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridValues[i][j].setVisible(visible);
            }
        }
    }

    private void highlightBlankCell(TextField cell) {
        // Highlight the blank cell with a different color temporarily
        cell.setStyle(cell.getStyle() + "; -fx-background-color: lightyellow;");

        // Clear the highlight after a delay
        clearHighlightAfterDelay(cell);
    }

    private boolean isGridSolved(int[][] grid) {
        // Compare the current state of the grid with the solution
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] != originalSolution[i][j]) {
                    return false; // Puzzle is not solved correctly
                }
            }
        }
        return true; // Puzzle is solved correctly
    }
    private void handleGameOver() {
        // Iterate through all cells and set background color to red
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridValues[i][j].setStyle("-fx-background-color: lightcoral;");
                gridValues[i][j].setEditable(false);
            }
        }
        // Show an alert indicating the game is over
        Alert gameOverAlert = new Alert(Alert.AlertType.INFORMATION);
        gameOverAlert.setTitle("Game Over");
        gameOverAlert.setHeaderText("Maximum Mistakes Reached");
        gameOverAlert.setContentText("You have reached the maximum allowed mistakes. Game over.");
        // Add buttons to the alert for restarting a new game or exiting the application
        ButtonType newGameButton = new ButtonType("New Game", ButtonBar.ButtonData.OK_DONE);
        ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
        gameOverAlert.getButtonTypes().setAll(newGameButton, exitButton);

        // Show the alert and handle the result
        gameOverAlert.showAndWait().ifPresent(result -> {
            if (result == newGameButton) {
                // Restart a new game
                showDifficultyDialog();
            } else {
                // Exit the application
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);

    }
}

