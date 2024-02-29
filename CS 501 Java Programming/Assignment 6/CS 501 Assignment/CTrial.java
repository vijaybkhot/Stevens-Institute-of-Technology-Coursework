package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class CTrial extends Application {

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
        GridPane.setHalignment(previousButton, HPos.CENTER);
        GridPane.setHalignment(nextButton, HPos.CENTER);
        GridPane.setValignment(previousButton, VPos.BOTTOM);
        GridPane.setValignment(nextButton, VPos.BOTTOM);

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

        // Create a row for the days of the week
        String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            dayLabel.setPrefSize(60, 60);
            dayLabel.setStyle("-fx-font-size: 14; -fx-text-fill: black;");
            grid.add(dayLabel, i, 0);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");

        for (int i = 1; i <= daysInMonth; i++) {
            int row = (i + dayOfWeek - 2) / 7;
            int col = (i + dayOfWeek - 2) % 7;

            Label dayLabel = new Label(Integer.toString(i));
            dayLabel.setPrefSize(60, 60);
            dayLabel.setStyle("-fx-font-size: 16; -fx-text-fill: black;");

            if (date.getMonth() == LocalDate.now().getMonth()) {
                dayLabel.setStyle("-fx-font-size: 16; -fx-text-fill: black;");
            } else {
                dayLabel.setStyle("-fx-font-size: 16; -fx-text-fill: gray;");
            }

            grid.add(dayLabel, col, row + 1);
        }

        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
