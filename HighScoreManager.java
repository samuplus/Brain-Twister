 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * HighScoreManager is the class responsible for creating, loading and updating the scores file 
 * @author Samu
 *
 */
public class HighScoreManager {
    // An arraylist of the type "score" we will use to work with the scores inside the class
    private ArrayList<Score> scores;

    // The name of the file where the highscores will be saved
    private static final String HIGHSCORE_FILE = "scores.dat";

    //Initialising an in and outputStream for working with the file
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    /**
     * Constructor does not take any parameters. It just creates a new ArrayList of type Score and assignes it to variable scores
     */
    public HighScoreManager() {
        //initialising the scores-arraylist
        scores = new ArrayList<Score>();
    }
    
    /**
     * Method responsible for calling methods that load and sort the scores
     * @return scores - will return the ArrayList scores sorted
     */
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }
    
    /**
     * This method is not used on the program but it was created for extensibility, ie. a reset can be added to the program, where only the administrator
     * can access it through the use of a password. In this case, the button action would be linked to this method
     */
    public void reset() {
    	scores = new ArrayList<Score>();
    	updateScoreFile();
    }
    
    /**
     * Mehotd that creates a ScoreComparator object and makes use of the predefined method of Collections to sort the scores using the comparator
     */
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }
    
    /**
     * Method that loads the scores file, adds a new username and its score to the ArrayList score and updates the file
     * @param name - username
     * @param score - user' score
     */
    public void addScore(String name, int score) {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }
    
    /**
     * Method that loads the scores file 
     */
    @SuppressWarnings("unchecked")
	public void loadScoreFile() {
    	// the file loading is enclosed with try and catch satements
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE)); // creates a new ObjectInputStream object from the scores file
            scores = (ArrayList<Score>) inputStream.readObject(); // read the object obtained on line 81 and assigns it to scores
        } catch (FileNotFoundException e) {
            System.out.println("FNF Error: " + e.getMessage() +" - A new file has been created with default values");
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Method that update the scores file by writing any relevant score
     */
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE)); // creates a new ObjectInputStream object from the scores file
            outputStream.writeObject(scores); // writes the content of the ArrayList scores into the object created on line 106
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Method that runs through the ArrayList scores and return each element individually
     * @return highScoreString - value that each time will contain a different element of the ArrayList scores
     */
    public String getHighscoreString() {
        String highScoreString = "";
        int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highScoreString += (i + 1) + "\t" + scores.get(i).getUserName() + "\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highScoreString;
    }
}
