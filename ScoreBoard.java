 

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * ScoreBoard Class - creates the text "SCORE" and the score (value) itself 
* extends StackPane so that it can create a single object made of more than one component (2 Texts) and allow it to be added to root
* @author Samu
*/
public class ScoreBoard extends StackPane {

	private Text scoreValue;
	
	/**
	 * Constructor of GameOver that creates and adds together the text score and the score value
	 */
	public ScoreBoard(String setScore) {
		scoreValue = new Text(setScore);
		getChildren().addAll(scoreValueProp(), scoreTextProp());	// puts text and rectangle together 	
	}

	/**
	 * Method that sets text properties of scoreValue
	 * @return scoreValue - text that represents the value of the score
	 */
	private Node scoreValueProp() {
		scoreValue.setFill(Color.BLACK);
		scoreValue.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 50));
		setAlignment(Pos.BASELINE_LEFT);
		scoreValue.setTranslateY(40);
		return scoreValue;
	}

	/**
	 * Method that sets text properties of scoreText
	 * @return scoreText - text "SCORE"
	 */
	private Node scoreTextProp() {
		Text scoreText = new Text("SCORE");		
		scoreText.setFill(Color.BLACK);			
		scoreText.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 40));			
		return scoreText;
	}

	/**
	 * Method that update score by setting the value of score to the text of scoreValue
	 * @param score - integer that represents the score converted into String
	 */
	public void updateScore(String score) {
		scoreValue.setText(score);
	}
}
