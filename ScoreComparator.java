 

import java.util.Comparator;

/**
 * ScoreComparator Class - used for comparing scores and returning the outcome of this comparison
 * @author Samu
 *
 */
public class ScoreComparator implements Comparator<Score> {
	
	/**
	 * Method used to compare two scores
	 * @param score1 - first score to be compared
	 * @param score2 - second score to be compared
	 */
    public int compare(Score score1, Score score2) {

        int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return +1;
        }else{
            return 0;
        }
    }
}
