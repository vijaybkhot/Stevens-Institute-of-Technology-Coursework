package com.example.demo1;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RackingCar extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // Create a circle and set its properties
        Circle rearWheel = new Circle();
        rearWheel.setRadius(5);
        rearWheel.setStroke(Color.BLACK);
        rearWheel.setFill(Color.RED);

        Circle frontWheel = new Circle();
        frontWheel.setRadius(5);
        frontWheel.setStroke(Color.BLACK);
        frontWheel.setFill(Color.RED);

        Rectangle carBody = new Rectangle();
        carBody.setHeight(10);
        carBody.setWidth(50);
        carBody.setFill(Color.GREEN);
        carBody.setStroke(Color.BLACK);

        Polygon carRoof = new Polygon();
        carRoof.setFill(Color.RED);
        carRoof.setStroke(Color.BLACK);

        // Create a pane to hold the car
        Pane carPane = new Pane();
        carPane.getChildren().addAll(rearWheel, frontWheel, carBody, carRoof);

        // Hold buttons in an HBox
        HBox hBox = new HBox();
        Button pauseCar = new Button("Pause");;
        Button speedUp = new Button("Speed Up");
        Button speedDown = new Button("Speed Down");
        hBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 0));
        pauseCar.setMinWidth(20);
        pauseCar.setMinHeight(10);
        pauseCar.setAlignment(Pos.TOP_CENTER);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().addAll(pauseCar, speedUp, speedDown);

        Group car = new Group(rearWheel, frontWheel, carBody, carPane);

        // Create a scene and place it in the stage
        Scene scene = new Scene(new Group(car, hBox), 400, 400);
        hBox.setPrefWidth(scene.getWidth());



        // Calculate the bottom-left corner coordinates
        double bottomLeftX = rearWheel.getRadius() + 10;
        double bottomLeftY = scene.getHeight() - rearWheel.getRadius();

        rearWheel.setCenterX(bottomLeftX);
        rearWheel.setCenterY(bottomLeftY);
        frontWheel.setCenterX(bottomLeftX + 20);
        frontWheel.setCenterY(bottomLeftY);

        carBody.setX(bottomLeftX - 15);
        carBody.setY(bottomLeftY - 15);

        carRoof.getPoints().addAll(
                bottomLeftX - 5, bottomLeftY - 15,
                bottomLeftX + 5, bottomLeftY - 25,
                bottomLeftX + 15, bottomLeftY - 25,
                bottomLeftX + 25, bottomLeftY - 15
        );
        /*
        // Create the bindings for the car

        car.translateXProperty().bind(scene.widthProperty().divide(10));
        car.translateYProperty().bind(scene.heightProperty().divide(20));

         */

        Line path = new Line(bottomLeftX, bottomLeftY-200, bottomLeftX + scene.getWidth(), bottomLeftY-200); // Define the path from (100, 300) to (100, 50)
        PathTransition pt = new PathTransition(Duration.seconds(10), path, car);
        pt.setCycleCount(PathTransition.INDEFINITE);
        pt.play(); // Start animation

        speedUp.requestFocus();

        pauseCar.setOnMousePressed(mouseEvent -> {
            pt.pause();
            speedUp.requestFocus(); // Ensure the button can receive key events
        });
        pauseCar.setOnMouseReleased(mouseEvent -> {
            pt.play();
            speedUp.requestFocus();
        });

        speedUp.setOnAction(actionEvent -> {
            // Decrease the duration to increase speed
            pt.setRate(pt.getRate() * 2.0);
            speedUp.requestFocus(); // Ensure the button retains focus
        });

        speedDown.setOnAction(actionEvent -> {
            // Decrease the speed
            pt.setRate(pt.getRate() / 2.0);
            speedUp.requestFocus(); // Ensure the button retains focus
        });

        speedUp.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP: pt.setRate(pt.getRate() * 2.0); break;
                case DOWN: pt.setRate((pt.getRate() / 2.0)); break;
                default:
                }
            }
        );
        speedUp.requestFocus(); // Ensure the button can receive key events

/*
        speedDown.setOnKeyPressed(keyEvent -> {
                    switch (keyEvent.getCode()) {
                        case UP: pt.setRate(pt.getRate() * 2.0);
                        case DOWN: pt.setRate(pt.getRate() / 2.0);
                        default:
                    }
                }
        );
        speedDown.requestFocus(); // Ensure the button can receive key events

 */

        // Place the Scene in the stage
        stage.setTitle("Racking Car");
        stage.setScene(scene);
        stage.show();
    }
}
