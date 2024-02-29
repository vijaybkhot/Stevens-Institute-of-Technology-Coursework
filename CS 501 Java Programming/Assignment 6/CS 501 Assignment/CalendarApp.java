package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalendarApp extends Application {

    private LocalDate currentDate;

    @Override
    public void start(Stage primaryStage) {
        currentDate = LocalDate.now();

        GridPane grid = createCalendarGrid(currentDate);

        Button previousButton = new Button("Previous");
        Button nextButton = new Button("Next");

        previousButton.setOnAction(e -> {
            currentDate = currentDate.minusMonths(1);
            grid.getChildren().clear();
            grid.add(createCalendarGrid(currentDate), 0, 1);
        });

        nextButton.setOnAction(e -> {
            currentDate = currentDate.plusMonths(1);
            grid.getChildren().clear();
            grid.add(createCalendarGrid(currentDate), 0, 1);
        });

        GridPane buttons = new GridPane();
        buttons.add(previousButton, 0, 0);
        buttons.add(nextButton, 2, 0);
        GridPane.setHalignment(previousButton, HPos.LEFT);
        GridPane.setHalignment(nextButton, HPos.RIGHT);

        GridPane root = new GridPane();
        root.add(buttons, 0, 0);
        root.add(grid, 0, 1);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("Calendar App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createCalendarGrid(LocalDate date) {
        int daysInMonth = date.lengthOfMonth();
        int dayOfWeek = date.withDayOfMonth(1).getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");

        // Display dates from the previous month in gray
        LocalDate previousMonthDate = date.minusMonths(1);
        int daysInPreviousMonth = previousMonthDate.lengthOfMonth();
        int previousMonthDaysToDisplay = dayOfWeek - 1;

        for (int i = 0; i < previousMonthDaysToDisplay; i++) {
            Button button = new Button(Integer.toString(daysInPreviousMonth - previousMonthDaysToDisplay + i + 1));
            button.setPrefSize(60, 60);
            button.setStyle("-fx-background-color: white; -fx-font-size: 20; -fx-text-fill: rgb(128,128,128)");
            grid.add(button, i, 0);
        }

        // Display dates of the current month in black
        for (int i = 1; i <= daysInMonth; i++) {
            int row = (i + dayOfWeek - 2) / 7;
            int col = (i + dayOfWeek - 2) % 7;

            Button button = new Button(Integer.toString(i));
            button.setPrefSize(60, 60);
            button.setStyle("-fx-background-color: white; -fx-font-size: 20; -fx-text-fill: black");
            grid.add(button, col, row);
        }

        LocalDate nextMonthDate = date.plusMonths(1);
        int nextMonthDaysToDisplay = 0;

        for (int i = 0; i < 7 - ((daysInMonth + dayOfWeek - 2) % 7); i++) {
            Button button = new Button(Integer.toString(i + 1));
            button.setPrefSize(60, 60);
            button.setStyle("-fx-background-color: white; -fx-font-size: 20; -fx-text-fill: rgb(128,128,128)");
            grid.add(button, (daysInMonth + dayOfWeek - 2) % 7 + i, (daysInMonth + dayOfWeek - 2) / 7);
        }
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
