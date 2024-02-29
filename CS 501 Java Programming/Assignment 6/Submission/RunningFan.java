/*
Name: Vijay Khot
CWID: 20021838
*/
package com.example.demo1;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RunningFan extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // Create and hold buttons in an HBox
        HBox fanButtons = new HBox();
        Button pauseFan = new Button("Pause");
        Button resumeFan = new Button("Resume");
        Button reverseFan = new Button("Reverse");
        fanButtons.setPadding(new javafx.geometry.Insets(0, 0, 0, 0));
        fanButtons.setSpacing(5);
        fanButtons.setAlignment(Pos.BOTTOM_CENTER);
        fanButtons.getChildren().addAll(pauseFan, resumeFan, reverseFan);
        pauseFan.setMinSize(125, 50);
        pauseFan.setStyle("-fx-font-size: 20px;"); // Set the font size to 20 pixels
        resumeFan.setMinSize(125, 50);
        resumeFan.setStyle("-fx-font-size: 20px;"); // Set the font size to 20 pixels
        reverseFan.setMinSize(125, 50);
        reverseFan.setStyle("-fx-font-size: 20px;"); // Set the font size to 20 pixels

        // Create a Stack Pane to hold the fan
        StackPane fanPane = new StackPane();
        fanPane.getChildren().addAll(fanButtons); // Add buttons to the fanPane
        fanPane.setAlignment(Pos.CENTER);

        // Create a Scene and add the fanPane to it
        Scene scene = new Scene(fanPane, 450, 450);
        stage.setTitle("Running Fan"); // Set Stage title
        stage.setScene(scene); // Add scene to the stage
        stage.show();  // Show stage

        // Create Fan objects: Circle and arcs
        Circle innerCircle = new Circle();
        innerCircle.setRadius(150);
        innerCircle.setCenterX(fanPane.getWidth()/2);
        innerCircle.setCenterY(fanPane.getHeight()/2);
        innerCircle.setStroke(Color.BLACK);
        innerCircle.setFill(Color.WHITE);
        fanPane.getChildren().addAll(innerCircle);

        // Create a group to hold the arcs
        Group arcsGroup = new Group();
        double angle1 = 0;
        for (int i = 0; i < 4; i++) {
            Arc arc = new Arc(fanPane.getWidth()/2, fanPane.getHeight()/2, 135, 135, angle1 + 90, 50);
            arc.setFill(Color.RED);
            arc.setType(ArcType.ROUND);
            arcsGroup.getChildren().add(arc);
            angle1 += 90;
        }

        // Calculate the offset to rotate the non-circular end around the center
        double offset = 0;
        arcsGroup.setTranslateX(offset);
        arcsGroup.setTranslateY(offset);

        /// Create a custom rotation  animation
        Timeline rotateTransition = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(arcsGroup.rotateProperty(), arcsGroup.getRotate() + 360)
                )
        );
        rotateTransition.setCycleCount(Animation.INDEFINITE);

        // Add the arcsGroup to the fanPane
        fanPane.getChildren().addAll(arcsGroup);

        // Start the rotation animation
        rotateTransition.play();

        // Create a custom reverse rotation animation
        Timeline reverseRotation = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(arcsGroup.rotateProperty(), arcsGroup.getRotate() - 360)
                )
        );

        reverseRotation.setCycleCount(Animation.INDEFINITE);

        // Create a boolean variable to track the rotation direction
        final boolean[] isClockwise = {true};

        // Set actions on events for all buttons
        // Reverse button action
        reverseFan.setOnAction(event -> {
            if (isClockwise[0]) {
                rotateTransition.pause();
                reverseRotation.play();
            } else {
                reverseRotation.pause();
                rotateTransition.play();
            }
            isClockwise[0] = !isClockwise[0]; // Toggle the rotation direction
        });

        // Pause button action
        pauseFan.setOnAction(event -> {
            rotateTransition.pause();
            reverseRotation.pause();
        });

        // Resume button action
        resumeFan.setOnAction(event -> {
            if (isClockwise[0]) {
                rotateTransition.play();
            } else {
                reverseRotation.play();
            }
        });

    }
}
