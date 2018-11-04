 

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/** 
 * Question Class - represents the word the user will need to collide with
 * extends Node so that it can be converted to Node and added to Pane (displayed via JavaFx)
 * @author Samu
 *
 */
public class Question extends Node{
	
	
	protected int selector; // declares variable selector, which is used to select between the three colour options
	private Text question; 
	
	/**]
	 * Constructor of Question Class 
	 * @param selector - used to select between the three colour options
	 */
	public Question(int selector){
		
		this.selector = selector;
	}
	
	/**
	 * Method used to define characteristics of question
	 * @return text 
	 */
	public Text createQuestion(){
		
		// sets text and text colour of question
		switch (selector) {
			
		case 0:
			question = new Text("RED");
			question.setFill(Color.RED);
			break;
		case 1:
			question = new Text("GREEN");
			question.setFill(Color.GREEN);
			break;
		case 2:
			question = new Text("BLUE");
			question.setFill(Color.BLUE);
			break;
		}
		
		question.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,40)); // sets font type and font size of question
		question.setTranslateX(20); // sets x position of question
		question.setTranslateY(45); // sets y position of question

		return question; 
	}
	
	

	// Predefined implemented methods of Node
	@Override
	protected boolean impl_computeContains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BaseBounds impl_computeGeomBounds(BaseBounds arg0, BaseTransform arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected NGNode impl_createPeer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object impl_processMXNode(MXNodeAlgorithm arg0, MXNodeAlgorithmContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
