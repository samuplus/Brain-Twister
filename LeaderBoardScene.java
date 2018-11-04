 

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
 * LeaderBoardScene Class - responsible for creating the graphic interface of the leaderboard scene and calling the method that displays 
 * scores from a file
 * @author Samu
 *
 */
public class LeaderBoardScene {
	// creates static button of type MenuItem so that they can be accessed from GameApp
	static GameApp.MenuItem backButton = new GameApp.MenuItem("BACK");
	private String highScores;
	private Text rank, userName, score;
	
	/**
	 * Constructor of LeaderBoardScene - assigns the highSCores sent on parameter to the local one
	 * @param highScores - String that contains the content of the scores file
	 */
	public LeaderBoardScene (String highScores) {
		this.highScores = highScores;
	}
	
	/**
	 * Method that creates a new Pane and adds to it an image background, the ranking with username and score, and a button 
	 * @return root
	 */
	public Parent createInstructions() {
		Pane root = new Pane();
		root.setPrefSize(840, 580);
		
		// Try catch exception handler that will notify user if file is not found
		try (InputStream inputStream = Files.newInputStream(Paths.get("res/images/BTmenu.jpg"))) { // Input Stream gives a presentation of the file (loads image).
			// Files.newInputStream retrieves the inputstream of that file and sizes it to the variable inputStream
			ImageView image = new ImageView(new Image(inputStream));	 // pass InputStream to image and image will be created from input stream
			// makes image fit on window with parameters defined inside ()
			image.setFitWidth(860);
			image.setFitHeight(600);
			image.setOpacity(0.5);
			image.setEffect(new GaussianBlur(8)); // set effect that blurs image according to the parameter inside ()
			root.getChildren().add(image); // adds image to root 
		}
		catch (IOException e) {
			System.out.println("It was not possible to load image");
		}
		
		// creates an object of type LeaderBoardTitle and send as parameter the title as it should be displayed
		LeaderBoardTitle leaderBoardTitle = new LeaderBoardTitle("L E A D E R B O A R D");
		// set the position of the text which contains the name of the game at the window
		leaderBoardTitle.setTranslateX(305);
		leaderBoardTitle.setTranslateY(30);

		// instantiate the object of type Scanner with the content of String highScores
        Scanner scanner = new Scanner(highScores);
        
        String line;
         
        int distance = 50, initalY = 80;
        
        // will display on screen the ten highest scores, enumerated and followed by the respective username
        while (scanner.hasNext()){
            line = scanner.nextLine();
 
            String[] toks=line.split("\t");

            // applies graphic effects to the ranking
            rank = new Text (toks[0]);
            rank.setTranslateX(305);
            rank.setTranslateY(initalY+distance);
            rank.setFill(Color.BLACK);	// sets colour to Darkgray
            rank.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));
            
            // applies graphic effects to the username
            userName = new Text (toks[1]);
            userName.setTranslateX(360);
            userName.setTranslateY(initalY+distance);
            userName.setFill(Color.DARKSLATEGRAY);	// sets colour to Darkgray
            userName.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));
            
            // applies graphic effects to the score
            score = new Text (toks[2]);
            score.setTranslateX(505);
            score.setTranslateY(initalY+distance);
            score.setFill(Color.GRAY);	// sets colour to Darkgray
            score.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));
                        
            distance += 40;
            root.getChildren().addAll(rank, userName, score); // add elements to root
        }
        scanner.close();
							
		// creates MenuBox. The back button will be passed when MenuBox is initiated
		GameApp.MenuBox buttonBox = new GameApp.MenuBox(backButton);
		
		// sets the position of the MenuBox
		buttonBox.setTranslateX(320);
		buttonBox.setTranslateY(530);
		// adds the title and the back button that was placed in a MenuBox 
		root.getChildren().addAll(leaderBoardTitle, buttonBox);
		
		return root;
	}
	
	/**
	 * InstructionsTitle Class - creates the background where the title will be displayed and place the title "Instructions" on its centre
	 * extends StackPane so that it can create a single object made of different components (Rectangle and Text) and allow it to be added to root
	 * @author Samu
	 *
	 */
	private static class LeaderBoardTitle extends StackPane {
		/**
		 * Constructor of InstructionsTitle that creates and adds together the title "Instructions" background and text 
		 * @param name - string that contains the title "Instructions"
		 */
		public LeaderBoardTitle(String name) {
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