 


import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


/**
 * Player Class - represents the avatar the user moves around the game window. As player is moved by mouse motion instead of having 
 * its own speed and direction, no need to have a constructor for this purpose. Player class has methods that create the avatar 
 * and check whether it collides with an answer.
 * extends Node so that it can be converted to Node and added to Pane (displayed via JavaFx)
 * @author Samu
 *
 */
public class Player extends Node {
	
	Polygon avatar; // declares variable avatar of type Polygon

	/**
	 * Method that draws a polygon given the coordinates and other characteristics
	 * @return greenAvatar of type Polygon - green part of the whole avatar
	 */
	public Polygon createGreenAvatar() {
		
		Polygon greenAvatar = new Polygon();
		// draws polygon as per coordinates given
		greenAvatar.getPoints().addAll(new Double[]{
		    -30.0, 30.0,
		    0.0, 10.0,
		    30.0, 30.0,
		    0.0, 0.0});
		
		greenAvatar.setStroke(Color.LIGHTSEAGREEN); 
		greenAvatar.setStrokeWidth(1);
		greenAvatar.setFill(Color.LIGHTSEAGREEN); // sets filling colour of greenAvatar
		return greenAvatar;
	}
	
	/**
	 * Method that draws a polygon given the coordinates and other characteristics
	 * @return redAvatar of type Polygon - red part of the whole avatar
	 */
	public Polygon createRedAvatar() {
		
		Polygon redAvatar = new Polygon();
		redAvatar.getPoints().addAll(new Double[]{
		    -30.0, 30.0,
		    0.0, -5.0,
		    30.0, 30.0,
		    0.0, 0.0});
		
		redAvatar.setStroke(Color.RED);
		redAvatar.setStrokeWidth(1);
		redAvatar.setFill(Color.RED); 	
		return redAvatar;
	}

	/**
	 * Method that checks whether mouse coordinates is within answer's bounds
	 * @param answerNode - Node representation of answer
	 * @param xPosition - Mouse x coordinate 
	 * @param yPosition - Mouse y coordinate
	 * @return true if collides or false if it does not
	 */
	 public boolean isColliding(Node answerNode, double xPosition, double yPosition) {
		 // Node method getBoundsInParent() is used with getMax and getMin of both x and y so that the value of the bounds of answer
		 // in the Pane is known. Adjustments to y coordinate (+11 and -11) were made in order to bound text as accurately as possible
		 if ( (xPosition < answerNode.getBoundsInParent().getMaxX()) &
				 (xPosition > answerNode.getBoundsInParent().getMinX()) &
				 (yPosition > answerNode.getBoundsInParent().getMinY()+11) &
				 (yPosition < answerNode.getBoundsInParent().getMaxY()-11)
					) {
				return true;
				}

			else {
				return false;
			}
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
