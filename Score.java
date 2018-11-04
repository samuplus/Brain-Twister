 

import java.io.Serializable;

/**
 * Score Class - responsible for storing the username and its score
 * implements Serializable 
 * @author Samu
 *
 */
public class Score  implements Serializable {

	private static final long serialVersionUID = 1L; // default serial version ID
	private int score;
    private String userName;

    /**
     * Method used to obtain score value
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Method used to obtain username
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Constructor that assigns received values to Score class
     * @param userName
     * @param score
     */
    public Score(String userName, int score) {
        this.score = score;
        this.userName = userName;
    }
}