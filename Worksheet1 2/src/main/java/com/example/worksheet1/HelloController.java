package com.example.worksheet1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.Text;

import java.io.*;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    Slider slidersat = new Slider();

    @FXML
    Slider sliderHue = new Slider();

    @FXML
    Slider sliderBrightness = new Slider();
    @FXML
    Label Sat = new Label();

    @FXML
    Label bright = new Label();

    @FXML
    Label hue = new Label();

    @FXML
    private Button AddPillButton;


    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField dosageTextField;

    @FXML
    private TextField nameTextField;


    @FXML
    private Label pillDosageLabel;
    @FXML
    private Label pillDescriptionLabel;

    @FXML
    private Label pillNameLabel;


    private static Map<String, allPillPixels> pillMap = new HashMap<>();
    private Collection<Object> allPillPixel;
    private String consoleOutput;

    public static Map<String, allPillPixels> getPillMap() {
        return pillMap;
    }


    int[] pixelArray;

    private Map<Integer, Color> colorMap;

    @FXML
    Button button = new Button("Open A File");


    @FXML
    Label label = new Label();

    @FXML
    private TextArea pillInfoTextBox;
    @FXML
    ImageView imgView = new ImageView();

    @FXML
    ImageView view = new ImageView();

    @FXML
    ImageView view2 = new ImageView();


    @FXML
    public Text estimatedClusterSize;
    @FXML
    private int clusterCounter;


    // Method to find the root of an element in a disjoint set data structure
    public int find(int[] pixelArray, int data) {
        if (pixelArray[data] == -1) return -1;
        // Check if the current element is its own parent (indicating it's a root)
        if (pixelArray[data] == data)
            // Return the current element if it's a root
            return data;
        else
            // Recursively call find method to find the root of the current element's parent
            return find(pixelArray, pixelArray[data]);
    }


    // Method to perform union operation on elements in a disjoint set data structure
    public void union(int[] pixelArray, int index, int j) {
        HelloApplication.ds.union(index, j);
        return;

//        // Initialize a variable to store the width
//        int width = 0;
//
//        // Iterate through elements in the disjoint set data structure's parent array
//        for (int i = 0; i < HelloApplication.ds.parent.length - 1; i++) {
//            // Check if the element's parent is not -1 (indicating it's not a root)
//            if (HelloApplication.ds.find(i) != -1) {
//                // Union the current element with the next element
//                HelloApplication.ds.union(i, i + 1);
//
//                // Check if the current element's right neighbor is within the bounds and is not a root
//                if (i + width < HelloApplication.ds.parent.length && HelloApplication.ds.find(i + width) != -1) {
//                    // Union the current element with its right neighbor
//                    HelloApplication.ds.union(i, i + width);
//                }
//            }
//        }
//
//        // Display the disjoint set data structure as text
//        HelloApplication.ds.displayDSAsText(width);
    }














    // Method to find the roots in an array of pixels
    private HashSet<Integer> findRoots(int[] pixelArray) {
        // Initialize a HashSet to store the roots
        HashSet<Integer> roots = new HashSet<>();

        // Check if the pixelArray is not null
        if (pixelArray != null) {
            // Iterate through each element in the pixelArray
            for (int i = 0; i < pixelArray.length; i++) {
                // Check if the current element is not -1 (indicating a root)
                if (pixelArray[i] != -1) {
                    // Add the root found by calling the find method to the HashSet
                    roots.add(find(pixelArray, i));
                }
            }
        }
        // Return the HashSet containing the roots
        return roots;
    }


    // Method to count the number of non-black pixels in an image
    public int pixelCounter(PixelReader pixelreader, int imageWidth, int imageHeight, Color color) {
        Image image = view.getImage();

        // Initialize a counter for non-black pixels
        int count = 0;

        // Iterate through each pixel in the image
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                // Get the color of the pixel at (i, j)
                Color pixelColor = pixelreader.getColor(i, j);
                // Check if the pixel color is not black
                if (!pixelColor.equals(Color.BLACK)) {
                    // Increment the count if the pixel color is not black
                    count++;
                }
            }
        }
        // Return the count of non-black pixels
        return count;
    }


    // Method to estimate the cluster size triggered by an ActionEvent
    public void estimateClusterSize(ActionEvent event) {
        // Remove the estimatedClusterSize Node from its parent AnchorPane's children
        ((AnchorPane) view.getParent()).getChildren().removeAll((Node) estimatedClusterSize);

        // Set the text content of the estimatedClusterSize Node to display the estimated pill cluster size
        estimatedClusterSize.setTextContent("Estimated pill cluster size: " + getClusterCounter());

        // Add the estimatedClusterSize Node back to its parent AnchorPane's children
        ((AnchorPane) view.getParent()).getChildren().add((Node) estimatedClusterSize);
    }


    // Estimates the cluster area size for each cluster (number of white pixels in a cluster)
    public int estimateAreaSize(int r) {
        int areaCounter = 0;
        for (int j : pixelArray) {
            if (r == j) {
                areaCounter++;
            }
        }
        return areaCounter;
    }


    //HOW TO OPEN AN IMAGEEEE
    @FXML
    public void button(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\"));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG image", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG image", "*.png"),
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png")
        );

        Stage stage = (Stage) button.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            label.setText(selectedFile.getName());
            Image image = new Image(selectedFile.toURI().toString(), imgView.getFitWidth(), imgView.getFitHeight(), false, true);
            imgView.setImage(image);
            Image img = new Image(selectedFile.toURI().toString(), view2.getFitWidth(), view2.getFitHeight(), false, true);
            view2.setImage(img);
            HelloApplication.ds = new UnionFind((int) (image.getWidth() * image.getHeight()));
            pixelArray = HelloApplication.ds.parent; //alias

        } else {
            System.out.println("No file has been selected");
        }
    }


    ///CONVERTING TO GRAYSCALE THIS IS FROM WORKSHEET 1 I JUST LEFT IT THERE
    @FXML
    public void convertToGrayScale() {
        // Get the image from the image view
        Image image = imgView.getImage();

        // Check if the image view is not null
        if (imgView != null) {
            // Create a writable image with dimensions matching the image view's fit width and fit height
            WritableImage result = new WritableImage((int) imgView.getFitWidth(), (int) imgView.getFitHeight());
            // Get the pixel reader for the original image
            PixelReader pixelreader = image.getPixelReader();
            // Get the pixel writer for the writable image
            PixelWriter pixelwriter = result.getPixelWriter();

            // Iterate through each pixel in the image
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    // Get the color of the current pixel
                    Color c = pixelreader.getColor(i, j);

                    // Calculate the brightness of the current pixel
                    double brightness = c.getBrightness();

                    // Check if the brightness is less than 0.5
                    if (brightness < 0.5) {
                        // Set the color of the pixel to black if the brightness is less than 0.5
                        pixelwriter.setColor(i, j, Color.BLACK);
                    } else {
                        // Set the color of the pixel to white if the brightness is greater than or equal to 0.5
                        pixelwriter.setColor(i, j, Color.WHITE);
                    }
                }
                // Update the image view with the modified image after processing each row
                view.setImage(result);
                // Trigger mouse clicked event on the image view
                view.getOnMouseClicked();
            }
        }
    }


    //MAKES IMAGE GO BLACK AND WHITE WHEN YOU PICK A PILL

    @FXML
    public void choosePillSample(MouseEvent e) {
        // Get the image from the image view
        Image image = imgView.getImage();

        // Check if the image view is not null
        if (imgView != null) {
            // Create a writable image with dimensions matching the image view's fit width and fit height
            WritableImage result = new WritableImage((int) imgView.getFitWidth(), (int) imgView.getFitHeight());
            // Get the pixel reader for the original image
            PixelReader pixelreader = image.getPixelReader();
            // Get the pixel writer for the writable image
            PixelWriter pixelwriter = result.getPixelWriter();

            // Get the color of the pixel at the mouse coordinates
            Color sample = pixelreader.getColor((int) e.getX(), (int) e.getY());
            // Get the hue value of the sampled color
            double sampleHue = sample.getHue();

            // Iterate through each pixel in the image
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    // Get the color of the current pixel
                    Color c = pixelreader.getColor(i, j);
                    // Get the saturation and hue values of the current pixel
                    double saturation = c.getSaturation();
                    double hue = c.getHue();


                    // Check if the hue and saturation of the current pixel are within certain thresholds
                    if (Math.abs(hue - sampleHue) < 10 && Math.abs(saturation - sampleHue) > 10) {
                        // Set the color of the pixel to white if it matches the criteria
                        pixelwriter.setColor(i, j, Color.WHITE);
                    } else {
                        // Set the color of the pixel to black if it does not match the criteria
                        pixelwriter.setColor(i, j, Color.BLACK);
                        // Update the parent array in the HelloApplication.ds object if it's not null
                        if (HelloApplication.ds != null) {
                            HelloApplication.ds.parent[(int) (j * image.getWidth() + i)] = -1;
                        }
                    }

                    // Update the image view with the modified image
                    view.setImage(result);
                    // Trigger mouse clicked event on the image view
                    view.getOnMouseClicked();
                }
            }

            // If the HelloApplication.ds object is not null, display its data structure as text
            if (HelloApplication.ds != null) {
                HelloApplication.ds.displayDSAsText((int) image.getWidth());
            }
        }
    }


    ///TRYING TO DRAW RECTANGLES DON'T KNOW IF IT WORKS PROPERLY
    @FXML
 Label rectlabel = new Label();

    @FXML
    public void drawRectangles(MouseEvent e) {
        // Find roots of connected regions in the image
        HashSet<Integer> roots = findRoots(pixelArray);

        // Get the parent AnchorPane of the image view
        AnchorPane parent = (AnchorPane) view2.getParent();

        // Get the position of the image view in its parent
        Bounds boundsInParent = view2.getBoundsInParent();
        double imgViewX = boundsInParent.getMinX();
        double imgViewY = boundsInParent.getMinY();

        int counter = 1; // Counter for the number of rectangles drawn

        ArrayList<Integer> sortedRoots = new ArrayList<>(roots);
        Collections.sort(sortedRoots);

        // Iterate through each root (connected region) found
        for (int root : sortedRoots) {
            // Calculate bounds (bounding box) of the connected region
            int[] bounds = calculateBounds(pixelArray, root);

            // Check if the calculated bounds are valid
            if (isValidBounds(bounds)) {
                // Create a rectangle based on the bounds and position it relative to the image view
                Rectangle rect = createRectangle(bounds, imgViewX, imgViewY);

                // Add the rectangle to the parent AnchorPane
                parent.getChildren().add(rect);

                // Label the rectangle with the counter value
                rectangleLabel(rect, counter);

                // Increment the counter for the next rectangle
                counter++;
            }
        }

        // Print the number of rectangles drawn
        System.out.println("Number of rectangles drawn: " + (counter - 1));
    }

    public void rectangleLabel(Rectangle rect, int counter) {

        // Create a label for the rectangle
        Label label = new Label(Integer.toString(counter));

        // Calculate the position of the label relative to view2
        double labelX = rect.getLayoutX() + rect.getWidth();
        double labelY = rect.getLayoutY() + rect.getHeight();

        // Set the position of the label
        label.setLayoutX(labelX);
        label.setLayoutY(labelY);

        // Get the parent AnchorPane of view2
        AnchorPane parent = (AnchorPane) view2.getParent();

        // Add the label to the parent AnchorPane
        parent.getChildren().add(label);

    }











    int width;

    // Method to calculate the bounding box of a connected region
    private int[] calculateBounds(int[] pixelArray, int root) {
        int[] bounds = new int[4];
        ArrayList<Integer> elem = new ArrayList<>();
        for (int i = 0; i < pixelArray.length; i++)
            if (find(pixelArray, i) == root) elem.add(i);
        width = (int) view2.getImage().getWidth();
        bounds[1] = Collections.min(elem) / width;
        bounds[0] = Collections.min(elem, (a, b) -> a % width - b % width) % width;
        bounds[3] = Collections.max(elem) / width;
        bounds[2] = Collections.max(elem, (a, b) -> a % width - b % width) % width;

        return bounds;

    }

    // Method to check if the calculated bounds are valid (area within certain limits)
    private boolean isValidBounds(int[] bounds) {
        // Calculate the area of the bounding box
        int area = (bounds[2] - bounds[0]) * (bounds[3] - bounds[1]);
        // Check if the area is within the specified limits
        return area > 150 && area < 1000000;
    }

    // Method to create a rectangle based on the calculated bounds
    private Rectangle createRectangle(int[] bounds, double imgViewX, double imgViewY) {
        // Create a new Rectangle object
        Rectangle rect = new Rectangle();

        // Set the fill color of the rectangle to transparent
        rect.setFill(Color.TRANSPARENT);

        // Set the stroke color of the rectangle to blue
        rect.setStroke(Color.BLUE);

        // Set the stroke width of the rectangle
        rect.setStrokeWidth(2);

        // Set the layout position of the rectangle relative to the image view
        rect.setLayoutX(imgViewX + bounds[0]);
        rect.setLayoutY(imgViewY + bounds[1]);

        // Set the width and height of the rectangle
        rect.setWidth(bounds[2] - bounds[0]);
        rect.setHeight(bounds[3] - bounds[1]);

        // Return the created rectangle
        return rect;
    }






    @FXML
    void addPillButtonHandler(MouseEvent event) {
        // Check if the event is a mouse click
        // Get the x and y coordinates of the mouse click
        double mouseX = event.getX();
        double mouseY = event.getY();

        // Get the pixel reader for the image
        PixelReader pixelReader = view.getImage().getPixelReader();

        // Get the color of the pixel at the clicked position
        Color pixelColor = pixelReader.getColor((int) mouseX, (int) mouseY);

        // Check if the color of the clicked pixel is white
        if (pixelColor.equals(Color.WHITE)) {
            // Retrieve and trim input from text fields
            String pillName = nameTextField.getText().trim();
            String dosage = dosageTextField.getText().trim();
            String description = descriptionTextField.getText().trim();

            // Check if the name, dosage, and description are not empty
            if (!pillName.isEmpty() && !dosage.isEmpty() && !description.isEmpty()) {
                // Create a new pill object
                allPillPixels pill = new allPillPixels(pillName, dosage, description);

                // Add the pill to the collection
                pillMap.put(pillName, pill);
                allPillPixel.add(pill);

                System.out.println("Success: Pill added");
            } else {
                System.out.println("Error: Please fill in all fields");
            }
        } else {
            System.out.println("Error: Please click on a white area to add the pill");
        }
    }


    public void listPills() {
        // Iterating through each Pill object in the allPills collection using an enhanced for loop
        for (Object p : allPillPixel) {
            // Printing each Pill object using its 'toString()' method or its representation
            System.out.println(p);
        }
    }


    public void savePillDetails() {
        try {
            // Open the output stream to the file "AllGameSystemInfo.txt"
            FileOutputStream fos = new FileOutputStream("AllPillSysInfo.txt");
            // Wrap the FileOutputStream with DataOutputStream for efficient writing
            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
            if(allPillPixel!=null) {
                // Write details of all games into the file
                for (Object pill : allPillPixel) {
                    outStream.writeUTF(pill.toString() + "\n");
                }
            }
            // Close the output stream after writing all the details
            outStream.close();
        } catch (IOException e) {
            // In case of an exception, print the stack trace
            System.out.println(e.toString() + "\n");
        }
    }


    // random colours
    @FXML
    public void assignRandomColorToPill(ActionEvent e) {


        // Get the image from the view
        Image image = view.getImage();
        // Check if the view is null
        if (view == null) {
            // Print error message and return if no image is provided
            System.out.println("No black and white image provided");
            return;
        }
        // Get the pixel reader for the image
        PixelReader pixelreader = image.getPixelReader();
        // Get the width and height of the image
        int imageWidth = (int) image.getWidth();
        int imageHeight = (int) image.getHeight();

        // Create a new writable image to store the colorized result
        WritableImage change = new WritableImage(imageWidth, imageHeight);

        // Initialize an array to store the root index for each pixel
        // pixelArray = new int[imageWidth * imageHeight];

        // Scan and categorize pixels into connected components using Union-Find
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                int index = y * imageWidth + x;
                // Check if the pixel is not part of a connected component
                if (pixelArray[index] != -1) {
                    // Union adjacent pixels if they belong to the same component
                    if (x + 1 < imageWidth && pixelArray[index + 1] != -1) {
                        union(pixelArray, index, index + 1);
                    }
                    if (index + imageWidth < pixelArray.length && pixelArray[index + imageWidth] != -1) {
                        union(pixelArray, index, index + imageWidth);
                    }
                }
            }
        }
        displayDSAsText();


        // Assign random colors to each pill cluster
        HashMap<Integer, Color> colorMap = new HashMap<>();
        for (int i = 0; i < pixelArray.length; i++) {
            int root = find(pixelArray, i);
            // Check if the pixel belongs to a valid component and has not been assigned a color yet
            if (root != -1 && !colorMap.containsKey(root)) {
                // Generate a random color for the component
                colorMap.put(root, Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
            }
        }

        // Set colors of each pixel based on its component root value
        for (int i = 0; i < pixelArray.length; i++) {
            int row = i / imageWidth;
            int col = i % imageWidth;
            int root = find(pixelArray, i);
            // Check if the pixel belongs to a valid component
            if (root != -1) {
                // Get the color corresponding to the component
                Color color = colorMap.get(root);
                // Set the color of the pixel in the new image
                change.getPixelWriter().setColor(col, row, color);
            } else {
                // Set the color of the pixel to black if it does not belong to any component
                change.getPixelWriter().setColor(col, row, Color.BLACK);
            }
        }

        // Count the number of pills (number of unique colors)
        int numberOfPills = colorMap.size();
        System.out.println("There are " + numberOfPills + " pills in this image");

        // Set the colorized image to the view
        view.setImage(change);
        //view.getOnMouseClicked();

        // Save the processed image to a file if needed
    }



    public void displayDSAsText() {
        for (int i = 0; i < pixelArray.length; i++)
            System.out.print(find(pixelArray, i) + ((i + 1) % imgView.getImage().getWidth() == 0 ? "\n" : " "));
    }

    public void setClusterCounter(int clusterCounter) {
        // Set the clusterCounter field to the provided value
        this.clusterCounter = clusterCounter;
    }


    public int getClusterCounter() {
        // Return the current value of the clusterCounter field
        return clusterCounter;
    }


    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public ImageView getImgView() {
        return imgView;
    }


    public String getConsoleOutput() {
        return consoleOutput;
    }

    public void setConsoleOutput(String consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setPixelArray(int[] pixelArray) {


    }


    public Object getPixelArray() {
        return pixelArray;
    }


}




























































  /*  public void changeColours(ActionEvent event) {
        Image image = imgView.getImage();
        if (image != null) {
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();

            PixelReader pixelReader = image.getPixelReader();
            WritableImage writableImage = new WritableImage(width, height);
            PixelWriter pixelWriter = writableImage.getPixelWriter();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color color = pixelReader.getColor(x, y);
                    Color newColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
                    pixelWriter.setColor(x, y, newColor);
                }
            }

            imgView.setImage(writableImage);
        }
    }
}



   */



///RANDOM COLOURS

   /* public void changeColours(ActionEvent event) {
        Image image = view.getImage();
        if (image != null) {
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();

            Color pillClickerColor = Color.rgb(255, 255, 255); // Example: Red color

            PixelReader pixelReader = image.getPixelReader();
            WritableImage writableImage = new WritableImage(width, height);
            PixelWriter pixelWriter = writableImage.getPixelWriter();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color color = pixelReader.getColor(x, y);
                    if (color.equals(pillClickerColor) && isIsolatedPixel(pixelReader, x, y, width, height)) {
                        // Change color if the pixel belongs to the pill clicker area and it's an isolated pixel
                        Color newColor = getRandomColor();
                        pixelWriter.setColor(x, y, newColor);
                    } else {
                        // Maintain original color
                        pixelWriter.setColor(x, y, color);
                    }
                }
            }

            view.setImage(writableImage);
        }
    }

    // Function to check if a pixel is isolated (not connected to other pixels)
    private boolean isIsolatedPixel(PixelReader pixelReader, int x, int y, int width, int height) {
        Color targetColor = pixelReader.getColor(x, y);
        // Check neighboring pixels in a 3x3 grid around the current pixel
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // Check if the neighboring pixel is the same as the target pixel and not out of bounds
                if (i != x || j != y) {
                    if (i >= 0 && i < width && j >= 0 && j < height && pixelReader.getColor(i, j).equals(targetColor)) {
                        return false; // If any neighboring pixel has the same color, the pixel is not isolated
                    }
                }
            }
        }
        // All neighboring pixels have different colors, so the pixel is isolated
        return true;
    }

    // Function to generate a random color
    private Color getRandomColor() {
        return Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }


    */




   /* public void updateNewColor(ActionEvent event) {
        double finalHue = sliderHue.getValue();
        double finalSat = saturation.getValue();
        double finalBri = sliderBrightness.getValue();

        PixelReader pixelReader = imgView.getImage().getPixelReader();
        WritableImage result = new WritableImage((int) imgView.getImage().getWidth(), (int) imgView.getImage().getHeight());
        PixelWriter writer = result.getPixelWriter();

        for (int i = 0; i < imgView.getImage().getWidth(); i++) {
            for (int j = 0; j < imgView.getImage().getHeight(); j++) {
                Color oldColor = pixelReader.getColor(i, j);
                Color newColor = transformColor(oldColor, finalHue, finalSat, finalBri);
                writer.setColor(i, j, newColor);
            }
        }

        imgView.setImage(result);
    }






    */



























  /*  @FXML
    public void addPill(MouseEvent e) {
        if (view2 != null) {
            // Retrieve and trim input from text fields
            String pillName = nameTextField.getText().trim();
            String dosage = dosageTextField.getText().trim();
            String pixelCount = pixelCountTextField.getText().trim();

            // Flag to track the validity of input data
            boolean dataValid = true;

            // Check if the pill is already in the linked list
            boolean alreadyAdded = false;
            if (allPillPixel != null)
                for (Object obj : allPillPixel) {
                    allPillPixels pill = (allPillPixels) obj; // Explicitly cast obj to allPillPixels it gave me that correction it would not work otherwise
                    if (pill.getPillName().equals(pillName)) {
                        alreadyAdded = true;
                        break;
                    }
                }
            if (alreadyAdded) {
                // Draw rectangles around the pill
                drawRectangles(e);
            } else {
                // Check if the name is empty
                if (pillName.isEmpty()) {
                    System.out.println("Error: Pill name cannot be blank");
                    dataValid = false;
                }

                // Check if the dosage is empty
                if (dataValid && dosage.isEmpty()) {
                    System.out.println("Error: Dosage cannot be blank");
                    dataValid = false;
                }

                // Check if the pixel count is empty
                if (dataValid && pixelCount.isEmpty()) {
                    System.out.println("Error: Pixel count cannot be blank");
                    dataValid = false;
                }

                // If all data is valid, create and add the pill
                if (dataValid) {
                    allPillPixels pill = new allPillPixels(pillName, dosage, pixelCount);
                    pillMap.put(pillName, pill);
                    allPillPixel.add(pill);
                    System.out.println("Success: Pill Added");
                }
            }
        } else {
            System.out.println("Error: Image view is not available");
        }
    }








   */














//
//        // Initialize bounds with default values (image width and height)
//        int[] bounds = {(int) view2.getImage().getWidth(), (int) view2.getImage().getHeight(), 0, 0};
//
//        // Iterate through each pixel in the pixel array
//        for (int i = 0; i < pixelArray.length; i++) {
//            // Check if the pixel belongs to the current connected region (root)
//            if (pixelArray[i] != -1 && find(pixelArray, i) == root) {
//                // Calculate the x and y coordinates of the pixel
//                int rx = i % ((int) view2.getImage().getWidth());
//                int ry = i / ((int) view2.getImage().getWidth());
//                // Update bounds based on the coordinates of the current pixel
//                bounds[0] = Math.min(bounds[0], rx);
//                bounds[1] = Math.min(bounds[1], ry);
//                bounds[2] = Math.max(bounds[2], rx);
//                bounds[3] = Math.max(bounds[3], ry);
//            }
//        }
//        return bounds;









































/*
    @FXML
    public void handleRectangleMouseEvents(MouseEvent event ) {
            if (event.getSource() instanceof Pill) {
                Pill clickedPill = (Pill) event.getSource();
                Object allPills = clickedPill.getPillName();

                {
                    if (DisjointSetNode instanceof DisjointSetNode && allPills != clickedPill) {
                        DisjointSetNode disjointNode = (com.example.worksheet1.DisjointSetNode) DisjointSetNode;
                        if (disjointNode.getColor().equals(color)) {
                            drawSquareAroundDisjointNode(disjointNode);
                        }
                    }
                }
            }

                Image image = imgView.getImage();

                if (image != null) {
                    WritableImage result = new WritableImage((int) image.getWidth(), (int)
                            image.getHeight());
                    PixelReader pixelreader = image.getPixelReader();
                    PixelWriter pixelwriter = result.getPixelWriter();

                    for (int i = 0; i < result.getWidth(); i++) {
                        for (int j = 0; j < result.getHeight(); j++) {
                            Color c = pixelreader.getColor(i, j);
                            double grey = (c.getRed() * 0.299) + (c.getGreen() * 0.587) + (c.getBlue() * 0.114);


                            pixelwriter.setColor(i, j, Color.color(grey, grey, grey));
                        }
                        view.setImage(result);
                        view.getOnMouseClicked();

                    }
                }

            }

            @FXML
            public void drawSquareAroundDisjointNode(DisjointSetNode disjointNode) {
            double x = disjointNode.getX();
            double y = disjointNode.getY();
            double width = disjointNode.getWidth();
            double height = disjointNode.getHeight();

            Rectangle square = new Rectangle(5, 5, 10, 10);
            view.getImage();
        }





 */



   //https://stackoverflow.com/questions/46913118/trying-to-use-javafx-to-create-shapes-with-mouse



/*  convertToGrayScale.setOnMouseClicked(event -> {
        String pillName = label.getText();
        System.out.println("Pill name: " + pillName);
    });

        convertToGrayScale.setOnMouseClicked(event -> {
        String pillNumber = label.getText();
        System.out.println("Pill number: " + pillNumber);
    });

        convertToGrayScale.setOnMouseClicked(event -> {
        String pillSize = label.getText();
        System.out.println("Pill number: " + pillSize);
    });


 convertToGrayScale.setOnMouseClicked(MouseEvent -> {
                    String pillName = label.getText();
                    System.out.println("Pill name: " + pillName);
                });

                convertToGrayScale.setOnMouseClicked(MouseEvent -> {
                    String pillNumber = label.getText();
                    System.out.println("Pill number: " + pillNumber);
                });

                convertToGrayScale.setOnMouseClicked(MouseEvent -> {
                    String pillSize = label.getText();
                    System.out.println("Pill number: " + pillSize);
                });








 */




































































/*


@FXML
    public void convertToRedScale(ActionEvent event) {
    Image image = imgView.getImage();
        if (image != null) {
            WritableImage result = new WritableImage((int) image.getWidth(), (int)
                    image.getHeight());
            PixelReader pixelreader = image.getPixelReader();
            PixelWriter pixelwriter = result.getPixelWriter();

            for (int i = 0; i < result.getWidth(); i++) {
                for (int j = 0; j < result.getHeight(); j++) {
                    Color c = pixelreader.getColor(i, j);
                    double red = (c.getRed() * 0.8) + (c.getGreen() * 0.1) + (c.getBlue() * 0.1);

                    pixelwriter.setColor(i, j, Color.color(red, 0, 0));
                }
                imgView.setImage(result);

            }
        }
    }
@FXML
    public void convertToGreenScale(ActionEvent event) {
    Image image = imgView.getImage();
        if (image != null) {
            WritableImage result = new WritableImage((int) image.getWidth(), (int)
                    image.getHeight());
            PixelReader pixelreader = image.getPixelReader();
            PixelWriter pixelwriter = result.getPixelWriter();

            for (int i = 0; i < result.getWidth(); i++) {
                for (int j = 0; j < result.getHeight(); j++) {
                    Color c = pixelreader.getColor(i, j);
                    double green = (c.getRed() * 0.1) + (c.getGreen() * 0.8) + (c.getBlue() * 0.1);

                    pixelwriter.setColor(i, j, Color.color(0, green, 0));
                }
                imgView.setImage(result);

            }
        }
    }

@FXML
    public void convertToBlueScale(ActionEvent event) {
    Image image = imgView.getImage();
        if (image != null) {
            WritableImage result = new WritableImage((int) image.getWidth(), (int)
                    image.getHeight());
            PixelReader pixelreader = image.getPixelReader();
            PixelWriter pixelwriter = result.getPixelWriter();

            for (int i = 0; i < result.getWidth(); i++) {
                for (int j = 0; j < result.getHeight(); j++) {
                    Color c = pixelreader.getColor(i, j);
                    double blue = (c.getRed() * 0.1) + (c.getGreen() * 0.1) + (c.getBlue() * 0.8);

                    pixelwriter.setColor(i, j, Color.color(0, 0, blue));
                }
                imgView.setImage(result);

            }
        }
    }


 
}


 */


