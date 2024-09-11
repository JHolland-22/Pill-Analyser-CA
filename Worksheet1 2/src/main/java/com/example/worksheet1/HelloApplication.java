package com.example.worksheet1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import javafx.scene.image.Image;


public class HelloApplication extends Application {

    public static LinkyList<allPillPixels> allPillPixel = new LinkyList<>();

    public static UnionFind ds;

    @Override
    public void start(Stage stage) throws IOException {
        // Create a new AnchorPane layout to hold the UI components
        AnchorPane layout = new AnchorPane();

        // Create a button for opening a file
        Button button = new Button("Open A File");

        // Create a label to display the selected file name
        Label label = new Label();

        // Create an image view to display the image
        ImageView imgView = new ImageView();

        // Define the action to be performed when the button is clicked
        button.setOnAction(actionEvent -> {
            // Create a file chooser dialog
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a file");
            fileChooser.setInitialDirectory(new File("C:\\"));

            // Add extension filters for JPEG and PNG images
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPEG image", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG image", "*.png"),
                    new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png")
            );

            // Show the file chooser dialog and wait for user input
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                // If a file is selected, display its name in the label
                label.setText(selectedFile.getName());

                // Load the selected image and display it in the image view
                Image image = new Image(selectedFile.toURI().toString(), imgView.getFitWidth(), imgView.getFitHeight(), false, true);
                imgView.setImage(image);

                // Initialize the UnionFind data structure with the image dimensions
                ds = new UnionFind((int) (image.getWidth() * image.getHeight()));
            } else {
                // If no file is selected, print a message to the console
                System.out.println("No file has been selected");
            }
        });

        // Load the FXML file for the main UI layout
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Create a scene using the FXML layout and set its dimensions
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Set the title of the stage
        stage.setTitle("Java FX File Chooser!");

        // Set the scene for the primary stage and make it visible
        stage.setScene(scene);
        stage.show();
    }

    public static void listGames() {
        // Iterating through each Port object in the allPorts collection using an enhanced for loop
        for (allPillPixels g : allPillPixel) {
            // Printing each Port object using its 'toString()' method or its representation
            System.out.println(g);
        }
    }

    public static void addPill(String pillName, String dosage, String description) {

        // Creating a new Port object with provided parameters
        allPillPixels game = new allPillPixels(pillName, dosage, description);

        // Adding the newly created Port object to the allPorts collection
        HelloApplication.allPillPixel.add(game);

        // Initializing a variable 'size' with value 1
        int size = 1;

        // Looping until 'size' is less than the size of allPorts
        while (size < allPillPixel.getSize()) {
            // Printing the current value of 'size'
            System.out.println(size);
            // Incrementing 'size' by 1 in each iteration
            size++;
        }


    }
}



