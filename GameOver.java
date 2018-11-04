 

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * GameOver Class - creates the background where the game over sign and exit route will be displayed 
 * extends StackPane so that it can create a single object made of different components (Rectangle and Text) and allow it to be added to root
 * @author Samu
 *
 */
public class GameOver extends StackPane {
	
	/**
	 * Constructor of GameOver that creates and adds together the game over sign, the exit route and their background
	 */
	public GameOver() {
		getChildren().addAll(backRectangle(), exitRoute(), gameOver());	// puts text and rectangle together 	
	}
	
	/**
	 * Method that creates the background for the game over sign and exit route
	 * @return backRetangle - Rectangle in Node form that will serve as background for game over sign and exit route
	 */
	protected Node backRectangle() {
		Rectangle backRectangle = new Rectangle(750,200);
		backRectangle.setFill(Color.VIOLET);
		backRectangle.setStrokeWidth(1);
		backRectangle.setStroke(Color.WHITE);
		setAlignment(Pos.CENTER);
		return backRectangle;
	}

	/**
	 * Method that creates a text which shows the user how to quit the game
	 * @return exitRoute - Text in Node form
	 */
	protected Node exitRoute() {
		Text exitRoute = new Text("P R E S S   E S C A P E   T O   Q U I T");
		exitRoute.setFill(Color.DIMGRAY);
		exitRoute.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));
		exitRoute.setTranslateY(72);
		return exitRoute;
	}

	/**
	 * Method that creates text which displays the sign GAME OVER
	 * @return gameOver - Text in Node form
	 */
	protected Node gameOver() {
		Text gameOver = new Text("G A M E   O V E R");
		gameOver.setFill(Color.BLACK);
		gameOver.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 150));
		setAlignment(Pos.CENTER);
		return gameOver;
	}
}