 

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Life Class - graphic representation of the lives the user has. Loads heart image and sets its graphic properties 
 * extends Node so that it can be converted to Node and added to Pane (displayed via JavaFx) 
 * @author Samu
 *
 */
public class Life extends Node {
	
	private int xSpacing;

	/**
	 * Life Constructor - defines the spacing between each heart image
	 * @param xSpacing - distance between each heart image
	 */
	public Life(int xSpacing) {
		this.xSpacing = xSpacing;
	}
	
	/**
	 * Method that loads the heart image and defines its graphic properties
	 * @return ImageView - heart image with its assigned properties
	 */
	public ImageView createLife() {
		// Try catch exception handler that will notify user if file is not found
		try (InputStream inputStream = Files.newInputStream(Paths.get("res/images/LifeHeart.png"))) {  // Input Stream gives a presentation of the file (loads image).
			// Files.newInputStream retrieves the inputstream of that file and sizes it to the variable inputStream
			Image image = new Image(inputStream);	// pass InputStream to image and image will be created from input stream
			
			// adding image properties inside try catch avoids further exceptions regarding image if file is not found
			
			ImageView imageView = new ImageView(); // create and initiate variable imageView of type ImageView
			imageView.setImage(image); // sets content of image (heart image) to imageView 
			// sets imageView size
			imageView.setFitWidth(23); 
			imageView.setFitHeight(23);
			imageView.setSmooth(true); // increase quality by using filtering method
			imageView.setCache(true); // improve performance
			imageView.setEffect(new GaussianBlur(2)); // sets GaussianBlur effect so that bounds of image have a light blur effect. Image is not too sharp
			// sets image position on considering the spacing between each other
			imageView.setTranslateX(740 + xSpacing); 
			imageView.setTranslateY(15);
			return imageView;
		}
		catch (IOException e) {
			System.out.println("It was not possible to load image");
		}
		return null;
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
