package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CFinal extends Application {

    private LocalDate currentDate;

    @Override
    public void start(Stage primaryStage) {
        currentDate = LocalDate.now();

        GridPane grid = createCalendarGrid(currentDate);

        Button priorButton = new Button("Prior");
        Button nextButton = new Button("Next");

        priorButton.setOnAction(e -> {
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
        buttons.setPrefWidth(100);
        buttons.setPrefHeight(60);
        buttons.add(priorButton, 0, 0);
        buttons.add(nextButton, 2, 0);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setStyle("-fx-background-color: #efebeb");
        priorButton.setPrefSize(100, 60);
        priorButton.setStyle("-fx-font-size: 20; -fx-text-fill: black; -fx-alignment: CENTER; -fx-font-weight: BOLD");
        nextButton.setPrefSize(100, 60);
        nextButton.setStyle("-fx-font-size: 20; -fx-text-fill: black; -fx-alignment: CENTER; -fx-font-weight: BOLD");



        GridPane root = new GridPane();
        root.add(buttons, 0, 2);
        root.add(grid, 0, 1);

        Scene scene = new Scene(root, 840, 400);

        primaryStage.setTitle("Calendar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createCalendarGrid(LocalDate date) {
        int daysInMonth = date.lengthOfMonth();
        int dayOfWeek = date.withDayOfMonth(1).getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setStyle("-fx-background-color: #efebeb");

        // Create a label for the current month and year
        String monthYear = date.getMonth().toString() + ", " + date.getYear();
        Label monthYearLabel = new Label(monthYear);
        monthYearLabel.setPrefSize(180, 30);
        monthYearLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;  -fx-font-weight: BOLD");
        GridPane.setColumnSpan(monthYearLabel, 7); // Span all 7 columns
        GridPane.setHalignment(monthYearLabel, HPos.CENTER); // Align to center
        grid.add(monthYearLabel, 0, 0);

        // Create a row for the days of the week
        String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            dayLabel.setPrefSize(120, 60);
            dayLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black; -fx-alignment: CENTER-LEFT; -fx-font-weight: BOLD");
            grid.add(dayLabel, i, 1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");

        // Display dates from the previous month in gray
        LocalDate previousMonthDate = date.minusMonths(1);
        int daysInPreviousMonth = previousMonthDate.lengthOfMonth();
        int previousMonthDaysToDisplay = dayOfWeek - 1;

        for (int i = 0; i < previousMonthDaysToDisplay; i++) {
            Button button = new Button(Integer.toString(daysInPreviousMonth - previousMonthDaysToDisplay + i + 1));
            button.setPrefSize(120, 60);
            button.setStyle("-fx-background-color: #efebeb; -fx-font-size: 20; -fx-text-fill: rgb(128,128,128); -fx-alignment: CENTER-LEFT");
            grid.add(button, i, 2);
        }

        // Display dates of the current month in black
        for (int i = 1; i <= daysInMonth; i++) {
            int row = (i + dayOfWeek - 2) / 7;
            int col = (i + dayOfWeek - 2) % 7;

            Button button = new Button(Integer.toString(i));
            button.setPrefSize(120, 60);
            button.setStyle("-fx-background-color: rgb(239,235,235); -fx-font-size: 20; -fx-text-fill: black; -fx-font-weight: BOLD; -fx-alignment: CENTER-LEFT");
            grid.add(button, col, row + 2);
        }

        LocalDate nextMonthDate = date.plusMonths(1);
        int nextMonthDaysToDisplay = 0;

        for (int i = 0; i < 7 - ((daysInMonth + dayOfWeek - 2) % 7); i++) {
            Button button = new Button(Integer.toString(i + 1));
            button.setPrefSize(120, 60);
            button.setStyle("-fx-background-color: #efebeb; -fx-font-size: 20; -fx-text-fill: rgb(128,128,128); -fx-alignment: CENTER-LEFT");
            grid.add(button, (daysInMonth + dayOfWeek - 2) % 7 + i, (((daysInMonth + dayOfWeek - 2) / 7)) + 2);
        }

        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
