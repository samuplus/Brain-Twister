 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * InstructionsScene Class - reponsible for creating the graphic interface of the instructions scene
 * @author Samu
 *
 */
public class InstructionsScene {
	// creates static button of type MenuItem so that they can be accessed from GameApp
	static GameApp.MenuItem backButton = new GameApp.MenuItem("BACK");
	// creates a final String where the name of the instructions file is stored
	private final String INSTRUCTIONS_FILE = "instructions.dat";
	
	private Text instNumber, instContent, fileNotFound;
	private boolean fileOnFolder = false;
	private String instructions;
	
	/**
	 * Method that creates a new Pane and adds an image background, the instructions obtained from a text file and a button to it
	 * @return root
	 */
	public Parent createInstructions() {
		Pane root = new Pane();
		root.setPrefSize(840, 580); // sets the size of the root
		
		// Try catch exception handler that will notify user if file is not found
		try (InputStream inputStream = Files.newInputStream(Paths.get("res/images/BTmenu.jpg"))) { // Input Stream gives a presentation of the file (loads image).
			// Files.newInputStream retrieves the inputstream of that file and sizes it to the variable inputStream
			ImageView image = new ImageView(new Image(inputStream));	 // pass InputStream to image and image will be created from input stream
			// makes image fit on window with parameters defined inside ()
			image.setFitWidth(860);
			image.setFitHeight(600);
			image.setOpacity(0.5);
			image.setEffect(new GaussianBlur(8));
			root.getChildren().add(image); // adds image to root 
		}
		catch (IOException e) {
			System.out.println("It was not possible to load image");
		}
		
		// creates an object of type InstructionsTitle and send as parameter the title as it should be displayed
		InstructionsTitle instructionsTitle = new InstructionsTitle("I N S T R U C T I O N S");
		// set the position of the text which contains the name of the game at the window
		instructionsTitle.setTranslateX(305);
		instructionsTitle.setTranslateY(30);
		
		BufferedReader reader; 
	    String line = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    String lineSeparator = System.getProperty("line.separator"); // assigns to a variable of type String the "meaning of a line separator"
	    //Sequence of try and catch statements dedicated to opening and reading the instructions file
		try {
			reader = new BufferedReader(new FileReader (INSTRUCTIONS_FILE));
		    try {
		        try {
					while((line = reader.readLine()) != null) {
					    stringBuilder.append(line);
					    stringBuilder.append(lineSeparator);
					}
				} catch (IOException e) {
					System.out.println("Error found: " + e.getMessage());
				}

		        instructions = stringBuilder.toString(); // content of instructions file is stored on String instructions
		        fileOnFolder = true; // indicates that the file was found
		    } finally {
		        try {
					reader.close();
				} catch (IOException e) {
					System.out.println("UNABLE TO CLOSE FILE: " + e.getMessage());				
				}
		    }
		} catch (FileNotFoundException e) {
			System.out.println("FOLLOWING FILE NOT FOUND: " + e.getMessage());
		}
		
		// Conditional statement that will display the content of instructions file or will display a message on the screen indicating the file was not found
		if (fileOnFolder) {
			Scanner scanner = new Scanner(instructions);       
	        String lines;      
	        int distance = 50, initalY = 80; // indicates the Y distance between lines and the initial displacement, respec.

	        while (scanner.hasNext()){
	            lines = scanner.nextLine();
	            
	            // stores on each index of toks the content available between tabs
	            String[] toks=lines.split("\t");

	            // applies graphic effects to the number of the instruction
	            instNumber = new Text (toks[0]);
	            instNumber.setTranslateX(80);
	            instNumber.setTranslateY(initalY+distance);
	            instNumber.setFill(Color.BLACK);	// sets colour to Darkgray
	            instNumber.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));
	            
	            // applies graphic effects to the instruction
	            instContent = new Text (toks[1]);
	            instContent.setTranslateX(115);
	            instContent.setTranslateY(initalY+distance);
	            instContent.setFill(Color.DARKSLATEGRAY);	// sets colour to Darkgray
	            instContent.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));
	         
	            distance += 40; // increments distance between current and next instruction
	            root.getChildren().addAll(instNumber, instContent); // add instruction number and content on root
	        }
	        scanner.close();			
		} else {
			// applies graphic effects to FNF message
			fileNotFound = new Text("F I L E  N O T  F O U N D");
			fileNotFound.setTranslateX(286);
			fileNotFound.setTranslateY(300);
			fileNotFound.setFill(Color.DARKSLATEGRAY);
			fileNotFound.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 40));
			root.getChildren().add(fileNotFound);
		}
        
		// creates MenuBox. The back button will be passed when MenuBox is initiated
		GameApp.MenuBox buttonBox = new GameApp.MenuBox(backButton);
		
		// sets the position of the MenuBox
		buttonBox.setTranslateX(320);
		buttonBox.setTranslateY(530);
		// adds the title and the back button that was placed in a MenuBox 
		root.getChildren().addAll(instructionsTitle, buttonBox);
		
		return root;
	}
	
	/**
	 * InstructionsTitle Class - creates the background where the title will be displayed and place the title "Instructions" on its centre
	 * extends StackPane so that it can create a single object made of different components (Rectangle and Text) and allow it to be added to root
	 * @author Samu
	 *
	 */
	private static class InstructionsTitle extends StackPane {
		/**
		 * Constructor of InstructionsTitle that creates and adds together the title "Instructions" background and text 
		 * @param name - string that contains the title "Instructions"
		 */
		public InstructionsTitle(String name) {
			Rectangle background = new Rectangle(230,40);	// creates the rectangle where the name of the game will be displayed (background for the text)
			// the following lines define the rectangle characteristics: colour, width and filling, respectively 
			background.setStroke(Color.GRAY);
			background.setStrokeWidth(2);
			background.setFill(null); 	// sets filling colour to transparent
			
			// creates a text that will be the name of the game and defines its colour, font, style and size
			Text text = new Text(name);
			text.setFill(Color.GRAY);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,30));
			
			setAlignment(Pos.CENTER);	// sets the text to be centred at the rectangle
			getChildren().addAll(background, text);	// puts text and rectangle together 	
		}
	}
	
}
