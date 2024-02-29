package com.example.arrowarcade;
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
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class ArrowArcade extends Application {
    private static final double GRAVITY = 9.81;
    private static final double INITIAL_VELOCITY = 20;
    private Pane root;
    PathTransition arrowTransition;
    PathTransition pathTransitionApple;
    private int remainingTrials = 5; // Initial number of trials
    private boolean scoreRecorded = false;
    private boolean welcomeScreenShown = false;
    double arrowAngle = 0;
    Circle dummy;
    private PauseTransition delay;
    Label trialsLabel;
    Button resetButton;
    QuadCurve qcUpdate;
    private double appleSize = 12.5;  // Default size
    private double arrowSize = 1.0;   // Default size
    Circle apple;
    Polygon arrowhead;
    Button launchButton;


    @Override
    public void start(Stage stage) throws Exception {


        root = new Pane();
        Scene scene = new Scene(root, 800, 800, Color.LIGHTBLUE);

        // Create the body of the apple (circle)
        apple = new Circle(800, 400, 12.5, Color.RED);
        dummy = new Circle(800, 400, 12.5, Color.RED);


        // Create arrow body
        arrowhead = new Polygon();
        arrowhead.getPoints().addAll(50.0, 600.0,
                40.0, 590.0, 40.0, 595.0, 0.0, 595.0, 0.0, 605.0,
                40.0, 605.0, 40.0, 610.0);
        arrowhead.setFill(Color.RED);
        arrowhead.setStrokeWidth(2.0);
        arrowhead.setLayoutX(0.0);
        arrowhead.setLayoutY(0);

        arrowhead.setScaleX(arrowSize);  // Set the scale based on difficulty
        arrowhead.setScaleY(arrowSize);

        // Add arrow and apple to root
        root.getChildren().addAll(apple, arrowhead, dummy);


        // Create the path for the travel of the arrow and apple using Quad curve
        QuadCurve qc = new QuadCurve();
        qc.setStartX(0);
        qc.setStartY(600);
        qc.setEndX(arrowHorizontal(arrowAngle));        // Calculate
        qc.setEndY(600);
        qc.setControlX((qc.getStartX() + qc.getEndX())/2);
        qc.setControlY(600 - (arrowVertical(arrowAngle)*2));    // Calculate height * 2
        qc.setStrokeWidth(5);

        QuadCurve qcApple = new QuadCurve();
        qcApple.setControlX(800);
        qcApple.setControlY(200);
        qcApple.setStartX(800 + appleHorizontal());
        qcApple.setStartY(600);
        qcApple.setEndX(800 - appleHorizontal());
        qcApple.setEndY(600);
        root.getChildren().add(qcApple);
        qcApple.setVisible(false);


        // Create a path transition for the arrow
        arrowTransition = new PathTransition();
        arrowTransition.setNode(arrowhead);
        arrowTransition.setPath(qc);
        arrowTransition.setCycleCount(1);
        arrowTransition.setRate(0.1); // Adjust the rate as needed
        arrowTransition.setAutoReverse(true);
        arrowTransition.stop();


        // Create a path transition for the apple
        pathTransitionApple = new PathTransition();
        pathTransitionApple.setNode(apple);
        pathTransitionApple.setPath(qcApple);
        pathTransitionApple.setCycleCount(1);
        pathTransitionApple.setRate(0.1); // Adjust the rate as needed
        pathTransitionApple.stop();

        qcApple.setStroke(Color.GREEN);
        qcApple.setStrokeWidth(2.0);
        qcApple.setFill(null);
        qcApple.getStrokeDashArray().addAll(5.0, 5.0);

        delay = new PauseTransition(Duration.seconds(1.735));
        delay.setOnFinished(event -> {
            // Start the arrowTransition after the delay
            arrowTransition.play();
            dummy.setVisible(false);
        });


        // Create a UI for user input:
        Label angleLabel = new Label("Set Arrow Angle here:");
        angleLabel.setTextFill(Color.BLACK);
        angleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));


        angleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextField angleTextField = new TextField();
        angleTextField.setMaxWidth(100);
        angleTextField.setPromptText("Enter angle");

        trialsLabel = new Label("Trials Remaining: " + remainingTrials);
        trialsLabel.setTextFill(Color.BLACK);
        trialsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        resetButton = new Button("Reset");
        resetButton.setStyle("-fx-font-weight: BOLD; -fx-font-size: 20");
        resetButton.setOnAction(event -> {
            launchButton.setDisable(false);
            // Reset arrow and apple positions
            arrowhead.setTranslateX(0);
            arrowhead.setTranslateY(0);
            apple.setTranslateX(800);

            qcApple.setVisible(false);
            qcUpdate.setVisible(false);

            // Reset QuadCurve and PathTransition
            QuadCurve qcUpdate = new QuadCurve();
            qcUpdate.setStartX(0);
            qcUpdate.setStartY(600);
            qcUpdate.setEndX(arrowHorizontal(arrowAngle));
            qcUpdate.setEndY(600);
            qcUpdate.setControlX((qcUpdate.getStartX() + qcUpdate.getEndX()) / 2);
            qcUpdate.setControlY(600 - (arrowVertical(arrowAngle) * 2));

            arrowTransition.setPath(qcUpdate);

            // Restart the animation
            arrowTransition.stop();
            pathTransitionApple.stop();

            // Reset trials
            remainingTrials = 5;
            trialsLabel.setText("Trials Remaining: " + remainingTrials);

            angleTextField.clear();

            // Show difficulty dialog
            showDifficultyDialog();

        });

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-weight: BOLD; -fx-font-size: 20");
        exitButton.setOnAction(event -> {
            System.exit(0);
        });


        launchButton = new Button("Launch");
        launchButton.setStyle("-fx-font-weight: BOLD; -fx-font-size: 20");

        launchButton.setOnAction(event -> {
                    try {
                        double newArrowAngle = Double.parseDouble(angleTextField.getText());

                        if (newArrowAngle >= 1.0 && newArrowAngle <= 89.0) {
                            double arrowAngle = Math.toRadians(newArrowAngle);

                            root.getChildren().remove(qcUpdate);
                            qcUpdate = new QuadCurve();
                            qcUpdate.setFill(null);
                            qcUpdate.setStroke(Color.GREEN);
                            qcUpdate.setStrokeWidth(2);
                            qcUpdate.getStrokeDashArray().addAll(5.0, 5.0);

                            qcUpdate.setStartX(0);
                            qcUpdate.setStartY(600);
                            qcUpdate.setEndX(arrowHorizontal(arrowAngle));
                            qcUpdate.setEndY(600);
                            qcUpdate.setControlX((qcUpdate.getStartX() + qcUpdate.getEndX()) / 2);
                            qcUpdate.setControlY(600 - (arrowVertical(arrowAngle) * 2));
                            root.getChildren().add(qcUpdate);
                            qcUpdate.setVisible(false);
                            dummy.setVisible(true);

                            arrowTransition.stop();
                            delay.stop();
                            pathTransitionApple.stop();
                            qcApple.setVisible(false);
                            qcUpdate.setVisible(false);
                            arrowTransition.setPath(qcUpdate);
                            pathTransitionApple.play();
                            delay.play();
                            launchButton.setDisable(true);

                        } else {
                            Alert angleAlert = new Alert(Alert.AlertType.ERROR);
                            angleAlert.setHeaderText("Incorrect angle");
                            angleAlert.setContentText(angleTextField.getText() + " is invalid input. Input the angle value between 0 & 90");
                            angleAlert.show(); // Show the alert to the player

                        }
                    } catch (NumberFormatException e) {
                        // Code for invalid input (not a double)
                        Alert angleAlert = new Alert(Alert.AlertType.ERROR);
                        angleAlert.setHeaderText("Incorrect input");
                        angleAlert.setContentText(angleTextField.getText() + " is invalid input. Please input a numeric value between 0 and 90");
                        angleAlert.show(); // Show the alert to the player
                    }
                }

        );

        pathTransitionApple.setOnFinished(event -> {
            remainingTrials--;

            // Update the trialsLabel
            trialsLabel.setText("Trials Remaining: " + remainingTrials);

            // Check if remaining trials are zero
            if (remainingTrials <= 0) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Game Over!");
                    alert.setContentText("You've run out of trials. What would you like to do?");

                    ButtonType replayButton = new ButtonType("Replay");
                    ButtonType exitButton1 = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(replayButton, exitButton1);

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == replayButton) {
                        // User chose to replay
                        resetButton.fire();
                    } else {
                        // User chose to exit
                        System.exit(0);
                    }
                });
            }
            // Reset arrow and apple positions
            apple.setTranslateX(800);
            qcApple.setVisible(true);
            dummy.setVisible(true);
        });

        arrowTransition.setOnFinished(event -> {
            launchButton.setDisable(false);
            qcUpdate.setVisible(true);
            arrowhead.setTranslateX(0);
            arrowhead.setTranslateY(0);
        });

        // Collision detection for arrow and apple
        Timeline collisionDetection = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    if (arrowhead.getBoundsInParent().intersects(apple.getBoundsInParent())) {
                        // Record the score only if it hasn't been recorded in this cycle
                        if (!scoreRecorded) {
                            scoreRecorded = true; // Set the flag to true
                            arrowTransition.pause();
                            pathTransitionApple.pause();
                            dummy.setVisible(false);
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setHeaderText("BINGO!!!\n You won!\nWhat would you like to do?");
                                alert.setContentText("You've hit the apple. What would you like to do?");

                                ButtonType replayButton = new ButtonType("Play again!");
                                ButtonType exitButton1 = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

                                alert.getButtonTypes().setAll(replayButton, exitButton1);

                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.isPresent() && result.get() == replayButton) {
                                    // User chose to replay
                                    resetButton.fire();
                                } else {
                                    // User chose to exit
                                    System.exit(0);
                                }
                            });
                        }

                    } else {
                        // Reset the flag when there is no collision
                        scoreRecorded = false;
                    }

                })
        );

        collisionDetection.setCycleCount(Timeline.INDEFINITE);
        collisionDetection.play();


        HBox inputBox = new HBox(10, angleLabel, angleTextField, launchButton, resetButton, exitButton, trialsLabel);
        inputBox.setLayoutX(0);
        inputBox.setLayoutY(50);
        inputBox.setPrefWidth(800);

        // Create a linear gradient for the HBox background
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.LIGHTBLUE),
                new Stop(1, Color.GREEN)
        );

        // Set the gradient background for the HBox
        BackgroundFill hboxBackgroundFill = new BackgroundFill(gradient, null, null);
        Background hboxBackground = new Background(hboxBackgroundFill);
        inputBox.setBackground(hboxBackground);

        // Set the background color of the root Pane
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

        // Create a Rectangle to represent the ground (land)
        Rectangle ground = new Rectangle(800, 800, Color.GREEN);
        ground.setY(600); // Set the position of the ground
        root.getChildren().add(ground);

        // Create a Rectangle to represent the brown ground
        Rectangle brownGround = new Rectangle(800, 200, Color.BROWN);
        brownGround.setY(700); // Set the position of the brown ground
        root.getChildren().add(brownGround);
        arrowhead.toFront();

        // Create a VBox to organize the welcome text and start button
        VBox welcomeBox = new VBox(20); // Set the spacing between children
        welcomeBox.setAlignment(Pos.CENTER);

        // Load the background image
        Image backgroundImage = new Image("10835.jpg"); // Replace "background_image.jpg" with your actual image file path
        Image instructionBackground = new Image("light-gray-concrete-wall.jpg");

        // Create a background image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );

        BackgroundImage instructionBackImage = new BackgroundImage(instructionBackground,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

        // Set the background image for the instructionBox
        welcomeBox.setBackground(new Background(background));


        VBox instructionBox = new VBox(20); // Set the spacing between children
        instructionBox.setAlignment(Pos.CENTER);



        Text welcomeText = new Text("Welcome to ArrowArcade!");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        welcomeText.setFill(Color.RED);
        welcomeText.toFront();

        Text instructionText = new Text("""
        Welcome to Arrow Arcade! The Arrow Apple simulator!

        Game Objective:
        Your goal is to hit the apple thrown horizontally from a cliff using an arrow shot from the ground.

        Number of Trials:
        You have 5 trials to hit the apple.

        Arrow Launch Parameters:
        - Arrow speed (v): Pre-defined at 20 m/sec.
        - Launch angle (θ): User input (between 0 and 90 degrees).

        Game Simulation:
        - An apple is thrown horizontally from a cliff at a height of 20 meters.
        - On the ground, an arrow is shot from a pre-defined horizontal distance of 80 meters.
        - The arrow and apple have pre-defined speed.

        Simulation Outcome:
        - If the arrow hits the apple, you win!
        - If the arrow misses the apple, the simulation will provide the path of both the arrow and apple.

        Instructions:
        1. Set the arrow launch angle: Input launch angle (θ degrees) between 0 and 90 degrees.
        2. Click the "Launch" button to shoot the arrow.
        3. The simulation will display the outcome based on the arrow's trajectory.
        4. If the arrow misses, optimize the angle in the next trial to hit the target.
        5. You have 5 trials to improve your aim.
        6. Use the "Reset" button to start a new set of trials.

        Note: The apple's initial velocity (v) remains constant throughout the trials.
        The difficulty level affects the size of the arrow and apple.

        Good luck, archer!""");
        instructionText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        instructionText.setFill(Color.BLACK);
        instructionBox.setBackground(new Background(instructionBackImage));
        instructionBox.setMaxWidth(900);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(instructionText);



        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 20;");

        Button okButton = new Button("Lets Play!!!");
        okButton.setStyle("-fx-font-size: 20;");

        welcomeBox.getChildren().addAll(welcomeText, startButton);
        instructionBox.getChildren().addAll(instructionText, okButton, scrollPane);
        instructionBox.setBackground(new Background(instructionBackImage));

        // Set the position and size of the Start button
        startButton.setStyle("-fx-font-size: 25; -fx-font-weight: BOLD"); // Increase the font size
        startButton.setTranslateY(100); // Move the button 20 units down


        //Fade transition for the welcome text
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), welcomeText);
        fadeTransition.setFromValue(1.0); // Starting opacity
        fadeTransition.setToValue(0.0);   // Ending opacity
        fadeTransition.setAutoReverse(true); // Enable auto-reverse for flickering effect
        fadeTransition.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely

        // Create a timeline to control the visibility of the welcome text
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> welcomeText.setVisible(true)),
                new KeyFrame(Duration.seconds(1.0), event -> welcomeText.setVisible(false))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely


        // Set the action for the start button to switch to the game screen
        startButton.setOnAction(event -> {
            welcomeBox.setVisible(false);
            root.getChildren().add(instructionBox); // Corrected line
            instructionBox.setVisible(true);
            pathTransitionApple.stop();
            arrowTransition.stop();

        });

        // Set a timeline to show the welcome screen after a delay
        Timeline showWelcomeScreen = new Timeline(
                new KeyFrame(Duration.seconds(0.001), event -> {
                    if (!welcomeScreenShown) {
                        root.getChildren().add(welcomeBox);
                        welcomeScreenShown = true;
                        fadeTransition.play();
                    }
                })
        );
        showWelcomeScreen.setCycleCount(1);
        showWelcomeScreen.play();

        // Set the size of the VBox to fill the entire scene
        welcomeBox.setMinSize(scene.getWidth(), scene.getHeight());
        welcomeBox.setMaxSize(scene.getWidth(), scene.getHeight());

        // Set the action for the OK button to switch to the game screen
        okButton.setOnAction(event -> {
            instructionBox.setVisible(false);
            root.getChildren().add(inputBox);
            pathTransitionApple.stop();
            arrowTransition.stop();
            // Show difficulty dialog
            showDifficultyDialog();

        });

        // Set the size of the instructionBox to fill the entire scene
        instructionBox.setMinSize(scene.getWidth(), scene.getHeight());
        instructionBox.setMaxSize(scene.getWidth(), scene.getHeight());

        stage.setTitle("ArrowArcade: Projectile Precision");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        // Start the animation
    }

    // Calculate the horizontal distance of the projectile of Arrow
    private double arrowHorizontal(double angle) {
        return 10 * 2 * (Math.pow(INITIAL_VELOCITY, 2) * Math.sin(2 * angle)) / GRAVITY;
    }
    // Calculate the vertical distance of the projectile of Arrow
    private double arrowVertical(double angle) {

        return 10 * 2 *(Math.pow(INITIAL_VELOCITY, 2) * Math.pow(Math.sin(angle), 2)) / (2 * GRAVITY);
    }

    // Calculate the horizontal distance of the projectile of the apple
    private double appleHorizontal() {
        return 20 * (2 * 10 / GRAVITY) * INITIAL_VELOCITY;
    }

    // Difficulty dialog box
    private void showDifficultyDialog() {
        List<String> choices = Arrays.asList("Easy", "Medium", "Hard");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Easy", choices);
        dialog.setTitle("Choose Difficulty");
        dialog.setHeaderText("Choose the difficulty level");
        dialog.setContentText("Difficulty:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(difficulty -> setDifficulty(Difficulty.valueOf(difficulty.toUpperCase())));
    }
    private void setDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                appleSize = 12.5;
                arrowSize = 1.0;
                break;
            case MEDIUM:
                appleSize = 10.0;
                arrowSize = 0.8;
                break;
            case HARD:
                appleSize = 7.5;
                arrowSize = 0.6;
                break;
        }

        // Update the sizes of the apple and arrow
        apple.setRadius(appleSize);
        dummy.setRadius(appleSize);
        arrowhead.setScaleX(arrowSize);
        arrowhead.setScaleY(arrowSize);
    }

    private enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

}


