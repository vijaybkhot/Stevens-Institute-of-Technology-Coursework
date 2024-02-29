/*
Name: Vijay Khot
CWID: 20021838
*/
package com.example.demo1;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TrafficLight extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // Create three Circles for three lights
        Circle redCircle = new Circle(50, Color.RED);
        redCircle.setStroke(Color.BLACK);
        redCircle.setStrokeWidth(3.0);
        Circle yellowCircle = new Circle(50, Color.YELLOW);
        yellowCircle.setStroke(Color.BLACK);
        yellowCircle.setStrokeWidth(3.0);
        Circle greenCircle = new Circle(50, Color.GREEN);
        greenCircle.setStroke(Color.BLACK);
        greenCircle.setStrokeWidth(3.0);

        // Create a Rectangle to hold the circles
        Rectangle trafficLight = new Rectangle(150, 400, Color.WHITE);
        trafficLight.setStroke(Color.BLACK);
        trafficLight.setStrokeWidth(3.0);

        // Create a VBox to arrange the circles vertically
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(redCircle, yellowCircle, greenCircle);

        // Create a Stack Pane to hold the circles over the rectangle
        StackPane stackHolder = new StackPane();
        stackHolder.getChildren().addAll(trafficLight, vBox);

        // Create a ToggleGroup to toggle the radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();

        // Create the radio buttons and add them to the toggle group

        RadioButton redButton = new RadioButton("Red");
        RadioButton yellowButton = new RadioButton("Yellow");
        RadioButton greenButton = new RadioButton("Green");

        redButton.setToggleGroup(toggleGroup);
        redButton.setStyle("-fx-font-size: 24; -fx-pref-width: 150; -fx-pref-height: 40;");
        redButton.setOnAction(event -> {
            redCircle.setFill(Color.RED);
            yellowCircle.setFill(Color.WHITE);
            greenCircle.setFill(Color.WHITE);
        });

        yellowButton.setToggleGroup(toggleGroup);
        yellowButton.setStyle("-fx-font-size: 24; -fx-pref-width: 150; -fx-pref-height: 40;");
        yellowButton.setOnAction(event -> {
            redCircle.setFill(Color.WHITE);
            yellowCircle.setFill(Color.YELLOW);
            greenCircle.setFill(Color.WHITE);
        });

        greenButton.setToggleGroup(toggleGroup);
        greenButton.setStyle("-fx-font-size: 24; -fx-pref-width: 150; -fx-pref-height: 40;");
        greenButton.setOnAction(event -> {
            redCircle.setFill(Color.WHITE);
            yellowCircle.setFill(Color.WHITE);
            greenCircle.setFill(Color.GREEN);
        });

        // Create an HBox to hold the radio buttons
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(redButton,yellowButton,greenButton);
        VBox holder = new VBox(40);
        holder.setAlignment(Pos.CENTER);
        holder.getChildren().addAll(stackHolder, buttons);

        // Create a scene and set it on the stage
        Scene scene = new Scene(holder, 500, 500);
        stage.setTitle("Traffic Light");
        stage.setScene(scene);
        stage.show();
    }
}
