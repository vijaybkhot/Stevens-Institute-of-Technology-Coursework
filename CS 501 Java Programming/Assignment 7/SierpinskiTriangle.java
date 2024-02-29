/*
Name: Vijay Khot
CW ID: 20021838
 */
package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class SierpinskiTriangle extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        // Create a Stack Pane to hold the triangle
        StackPane stackHolder = new StackPane();

        // Create a scene with the StackPane
        Scene scene = new Scene(stackHolder, 400, 400);

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.show();

        // Create a polygon with three vertices to represent a triangle
        Point2D p1 = new Point2D(stage.getWidth()/2, 20);
        Point2D p2 = new Point2D(20, stage.getHeight() - 20);
        Point2D p3 = new Point2D(stage.getWidth() - 20, stage.getHeight() - 20);

        // Use the recursive method defined below to draw Sierpinski triangle of order n=4
        Group triangles = sierpinski(4  , p1, p2, p3);

        stackHolder.getChildren().addAll(triangles);

    }

    public static Group sierpinski(int n, Point2D x1, Point2D x2, Point2D x3) {
        Group group = new Group();
        if (n == 0) {
            // Draw a triangle to connect three points
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(x1.getX(), x1.getY(), x2.getX(), x2.getY(), x3.getX(), x3.getY());
            triangle.setStroke(Color.BLACK);
            triangle.setFill(Color.WHITE);
            group.getChildren().addAll(triangle);
            return group;
        }
        else {
            // Get the midpoint on each edge in the triangle
            Point2D x12 = x1.midpoint(x2);
            Point2D x23 = x2.midpoint(x3);
            Point2D x31 = x3.midpoint(x1);

            group.getChildren().addAll(sierpinski(n-1, x1, x12, x31 ));
            group.getChildren().addAll(sierpinski(n-1, x12, x2, x23 ));
            group.getChildren().addAll(sierpinski(n-1, x31, x23, x3));
            return group;
        }
    }
}
