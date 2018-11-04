 

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
 * Answer Class - coloured words that represents the possible answers 
 * extends Node so that it can be converted to Node and added to Pane (displayed via JavaFx) 
 * @author Samu
 *
 */
public class Answer extends Node{
	
	protected int xPosition, yPosition, colorName, color;
	private Text text;
	
	/**
	 * Constructor of Answer Class 
	 * @param colorName - name of the colour
	 * @param color - colour itself
	 * @param xPosition - x coordinate 
	 * @param yPosition - y coordinate
	 * @param ySpacing - minimum y distance between answers
	 */
	public Answer(int colorName, int color, int xPosition, int yPosition, int ySpacing){
		this.colorName = colorName;
		this.color = color;
		this.xPosition = xPosition;
		this.yPosition = ySpacing + yPosition;
	}
	
	/**
	 * Method that sets the text and colour of an answer
	 * @return text - variable of type Text with the text and colour defined at the constructor
	 */
	public Text createAnswer(){
		
		// sets text of text according to colorName
		switch (colorName) {
			
		case 0:
			text = new Text("RED");
			break;
		case 1:
			text = new Text("GREEN");
			break;
		case 2:
			text = new Text("BLUE");
			break;
		}
		
		// sets colour of text according to color
		switch (color) {
		
		case 0:
			text.setFill(Color.RED);
			break;
		case 1:
			text.setFill(Color.GREEN);
			break;
		case 2:
			text.setFill(Color.BLUE);
			break;
		}
		
		// sets text font, type, size and position
		text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,70));
		text.setTranslateX(xPosition);
		text.setTranslateY(-(yPosition)); // y position takes into account spacing between answers 

		return text;
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
