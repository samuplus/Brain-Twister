 

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;



import javafx.geometry.Pos;
import javafx.scene.Parent;
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
 * MenuScene Class - responsible for creating graphic interface of menu
 * @author Samu
 *
 */
public class MenuScene {
	
	// creates static buttons of type MenuItem so that they can be accessed from GameApp
	static GameApp.MenuItem playButton = new GameApp.MenuItem("PLAY");
	static GameApp.MenuItem instructionsButton = new GameApp.MenuItem("INSTRUCTIONS");
	static GameApp.MenuItem leaderboardsButton = new GameApp.MenuItem("LEADERBOARD");
	static GameApp.MenuItem quitButton = new GameApp.MenuItem("QUIT");

	/**
	 * Method that creates a new Pane and adds the following graphic components to it: background image, game name and menu buttons 
	 * @return root 
	 */
	public Parent createMenu() {
		// Creates new Pane and assigns it to variable root
		Pane root = new Pane();
		root.setPrefSize(840, 580);
	
		// Try catch exception handler that will notify user if file is not found
		try (InputStream inputStream = Files.newInputStream(Paths.get("res/images/BTmenu.jpg"))) { // Input Stream gives a presentation of the file (loads image). 
			// Files.newInputStream retrieves the inputstream of that file and sizes it to the variable inputStream
			ImageView image = new ImageView(new Image(inputStream));	 // pass InputStream to image and image will be created from input stream
			// makes image fit on window with parameters defined inside ()
			image.setFitWidth(860);
			image.setFitHeight(600);
			root.getChildren().add(image); // adds image to root 
		}
		catch (IOException e) {
			System.out.println("It was not possible to load image");
		}
		
		// creates an object of type GameName and send as parameter the name of the game as it should be displayed
		GameName gameName = new GameName("B R A I N    T W I S T E R");
		// set the position of the text which contains the name of the game at the window
		gameName.setTranslateX(240);
		gameName.setTranslateY(175);
		
		// creates MenuBox. The following items will be passed when MenuBox is initiated, which are the menu buttons
		GameApp.MenuBox menuBox = new GameApp.MenuBox(playButton, instructionsButton, leaderboardsButton, quitButton);
		
		// sets the position of the MenuBox
		menuBox.setTranslateX(440);
		menuBox.setTranslateY(330);
		// adds the name of the game object and the buttons that were placed in a MenuBox 
		root.getChildren().addAll(gameName, menuBox);
		
		return root; 
	}
	
	/**
	 * GameName Class - creates the background where the name of the game will be displayed and place the name of the game on its centre
	 * extends StackPane so that it can create a single object made of different components (Rectangle and Text) and allow it to be added to root
	 * @author Samu
	 *
	 */
	private static class GameName extends StackPane {
		
		/**
		 * Constructor of GameName that creates and adds together the game name background and text 
		 * @param name - string that contains the name of the game
		 */
		public GameName(String name) {
			Rectangle background = new Rectangle(400,60);	// creates the rectangle where the name of the game will be displayed (background for the text)
			// the following lines define the rectangle characteristics: colour, width and filling, respectively 
			background.setStroke(Color.WHITE);
			background.setStrokeWidth(2);
			background.setFill(null); 	// sets filling colour to transparent
			
			// creates a text that will be the name of the game and defines its colour, font type, style and size
			Text text = new Text(name);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,50));
			
			setAlignment(Pos.CENTER);	// sets the text to be centred at the rectangle
			getChildren().addAll(background, text);	// puts text and rectangle together 	
		}
	}
	
}

	
