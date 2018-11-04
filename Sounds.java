 

import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Sounds Class - responsible for initiating the sounds and creating MediaPlayer objects that will be played on GameApp class
 * @author Samu
 *
 */
public class Sounds {
	// assigns sounds paths to final variables 
	private final String 
	SELECT_SOUND = "res/Sounds/Dry_Fire_gun.mp3",
	CORRECT_SOUND = "res/Sounds/Blop.mp3",
	WRONG_SOUND = "res/Sounds/Cat.mp3",
	START_SOUND = "res/Sounds/Electrical_Sweep.mp3",
	LAST_LIFE_SOUND = "res/Sounds/Hearbeat.mp3",
	GAME_OVER_SOUND = "res/Sounds/Laugh.mp3",
	START_UP_SOUND = "res/Sounds/StartUp.mp3";

	// MediaPlayer variable declaration
	private MediaPlayer selectPlayer, correctPlayer, wrongPlayer, startPlayer, lastLifePlayer, gameOverPlayer, startUpPlayer;
	
	/**
	 * Constructor that creates new Media objects according to the sounds and apply them to the MediaPlayer variable
	 */
	public Sounds () {
		
		Media selectSound = new Media(Paths.get(SELECT_SOUND).toUri().toString());
		selectPlayer = new MediaPlayer(selectSound);
		
		Media correctSound = new Media(Paths.get(CORRECT_SOUND).toUri().toString());
		correctPlayer = new MediaPlayer(correctSound);
		
		Media wrongSound = new Media(Paths.get(WRONG_SOUND).toUri().toString());
		wrongPlayer = new MediaPlayer(wrongSound);
		
		Media startSound = new Media(Paths.get(START_SOUND).toUri().toString());
		startPlayer = new MediaPlayer(startSound);
		
		Media lastLifeSound = new Media(Paths.get(LAST_LIFE_SOUND).toUri().toString());
		lastLifePlayer = new MediaPlayer(lastLifeSound);
		
		Media gameOverSound = new Media(Paths.get(GAME_OVER_SOUND).toUri().toString());
		gameOverPlayer = new MediaPlayer(gameOverSound);
		
		Media startUpSound = new Media(Paths.get(START_UP_SOUND).toUri().toString());
		startUpPlayer = new MediaPlayer(startUpSound);
	}
	
	/**
	 * Method that gets the sound played when user makes a selection
	 * @return selectPlayer
	 */
	public MediaPlayer getSelectSound() {
		return selectPlayer;
	}
	/**
	 * Method that gets the sound played when user chooses a correct answer
	 * @return correctPlayer
	 */
	public MediaPlayer getCorrectSound() {
		return correctPlayer;
	}
	/**
	 * Method that gets the sound played when user chooses a wrong answer
	 * @return wrongPlayer
	 */
	public MediaPlayer getWrongSound() {
		return wrongPlayer;
	}
	/**
	 * Method that gets the sound played when the game starts
	 * @return startPlayer
	 */
	public MediaPlayer getStartSound() {
		return startPlayer;
	}
	/**
	 * Method that gets the sound played when the user has only one life remaining
	 * @return lastLifePlayer
	 */
	public MediaPlayer getLastLifeSound() {
		return lastLifePlayer;
	}
	/**
	 * Method that gets the sound played when the game is over
	 * @return gameOverPlayer
	 */
	public MediaPlayer getGameOverSound() {
		return gameOverPlayer;
	}
	/**
	 * Method that gets the sound played when the program starts
	 * @return startUpPlayer
	 */
	public MediaPlayer getStartUpSound() {
		return startUpPlayer;
	}
}
