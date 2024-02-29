/*
Name: Vijay Khot
CWID: 20021838
*/
package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * A class named Grid that displays four images in a GridPane
 */
public class Grid extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Create a GridPane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(11.5, 11.5, 11.5, 11.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        //Create ImageView objects by passing all four image png files attached along with the submission zip
        ImageView germanFlag = new ImageView("german_flag.png");
        ImageView chineseFlag = new ImageView("chinese_flag.png");
        ImageView frenchFlag = new ImageView("french_flag.png");
        ImageView americanFlag = new ImageView("american_flag.png");


        // Bind the size of the images to the scene's width and height
        germanFlag.fitWidthProperty().bind(pane.widthProperty().divide(2).subtract(5));
        germanFlag.fitHeightProperty().bind(pane.heightProperty().divide(2).subtract(5));
        chineseFlag.fitWidthProperty().bind(pane.widthProperty().divide(2).subtract(5));
        chineseFlag.fitHeightProperty().bind(pane.heightProperty().divide(2).subtract(5));
        frenchFlag.fitWidthProperty().bind(pane.widthProperty().divide(2).subtract(5));
        frenchFlag.fitHeightProperty().bind(pane.heightProperty().divide(2).subtract(5));
        americanFlag.fitWidthProperty().bind(pane.widthProperty().divide(2).subtract(5));
        americanFlag.fitHeightProperty().bind(pane.heightProperty().divide(2).subtract(5));

        // Preserver the original Height and width ratio of the images
        germanFlag.setPreserveRatio(true);
        chineseFlag.setPreserveRatio(true);
        frenchFlag.setPreserveRatio(true);
        americanFlag.setPreserveRatio(true);

        // Add the ImageView objects to the GridPane created above. Give the location co-ordinates
        pane.add(germanFlag, 0, 0);
        pane.add(chineseFlag, 1, 0);
        pane.add(frenchFlag, 0, 1);
        pane.add(americanFlag, 1, 1);

        //Create a new Scene object by passing the above GridPane
        Scene scene = new Scene(pane, 850,500);
        stage.setTitle("Grid");  // Set the title of the Stage
        stage.setScene(scene);  //  Place the scene on to the Stage
        stage.show();   // Show the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}