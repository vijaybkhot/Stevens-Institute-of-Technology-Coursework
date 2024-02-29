/*
Name: Vijay Khot
CWID: 20021838
*/
package com.example.demo1;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * A class to display a String "WELCOME TO JAVA" in a circular shape
 */
public class Welcome extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        // Create a Pane to hold the Text String
        Pane pane = new Pane();
        pane.setPadding(new Insets(5, 5, 5, 5));
        // Create a new Scene to hold the pane
        Scene scene = new Scene(pane, 500, 500);

        // Calculate the 'X' and 'Y' co-ordinates for the centre of the circular shape
        double centerX = scene.getWidth() / 2;
        double centerY = scene.getHeight() / 2;
        double radius = 150; // Radius of the circular shape

        // The text string to be displayed
        String text = "WELCOME TO JAVA ";
        int length = text.length(); // The length of the string
        double angleChange = 360 / (double) length; // The angle to progressively rotate each character in a circle

        // A loop to iterate through each character of the string and assign rotation angle and display accordingly
        for (int i = 0; i < length; i++) {
            String character = String.valueOf(text.charAt(i)); // Access individual String at index 'i'
            double angle = i * angleChange; // Calculate the rotation angle for that String

            Text rotatedText = new Text(character); // Creating a new Text object by passing the above individual String
            rotatedText.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 70)); // Setting the Font style
            rotatedText.setFill(Color.BLACK); // Setting the fill color of the Font

            // Calculating the X and Y co-ordinate for the above individual string to place on the circle
            double x = centerX + radius * Math.cos(Math.toRadians(angle));
            double y = centerY + radius * Math.sin(Math.toRadians(angle));

            // Setting the X and Y co-ordinates for the individual string
            rotatedText.setX(x);
            rotatedText.setY(y);

            // Calculate rotation angle of the individual string
            double rotateAngle = angle - 270; // Angle to align individual string along the circumference of the circle
            Rotate rotate = new Rotate(rotateAngle, x, y); //Create a new Rotate object by passing above angle and co-ordinates
            rotatedText.getTransforms().add(rotate); // Apply the Rotate to the above individual string

            pane.getChildren().add(rotatedText); // Add the rotated text to the Pane
        }

        stage.setTitle("Welcome"); // Set the title of the Stage
        stage.setScene(scene); // Set the Scene of the Stage to 'scene'
        stage.show(); // Show the Stage
    }
}
