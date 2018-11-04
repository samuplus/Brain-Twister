 

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;

/*
 * Brain Twister Menu with JavaFx
 * 
 * @date 26/03/2017
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

// JavaFx is a platform that allows you to create modern-looking programs and animations.
// It is intended to replace Swing as the standard GUI library for Java SE, as the latter has an out-dated looking

/**
 * GameApp Class - launches the game via menu and sets the different scenes
 * extends Application so that it runs JavaFx
 * @author Samu
 *
 */
public class GameApp extends Application {

	// variable declarations
	protected double xPosition, yPosition;
	private ArrayList<Answer> answers;
	private ArrayList<Node> answersNode;
	private int ySpacing = 0, xSpacing = 0, score=0, lives = 3, levelSpeed = 2, levelCount=0, scoreMultiplier = 10;
	final int NUMBER_OF_ANSWERS = 500; 
	private Integer setScore=0;
	private Text currentAnswer, questionText;
	private AnimationTimer timer;
	HighScoreManager highScoreManager = new HighScoreManager();
	private String userName, highScore; 
	
	/**
	 * Main - launches application
	 * @param args
	 */
	public static void main(String[] args){
		launch(args); //launches application
	}
	
	/**
	 * start(Stage) - is launched by main 
	 */
	public void start(Stage stage) throws Exception {
		// adds ten default scores so that a scores file is created if not already existent and leaderboard is complete with default scores
		userName = "DEFAULT";
		for (int i=1; i<=10; i++) {
			highScoreManager.addScore("DEFAULT", score);	
		}
		// object of Sound calss is created. This object will concede access to the Sounds Class many methods
		Sounds sound = new Sounds();
		
		//------------ MENU SCENE -------------
		// get the sound that is played as soon as the game menu is displayed. Predefined play() method of MediaPlayer Class is used to play sound
		sound.getStartUpSound().play(); 
		
		MenuScene menuSc = new MenuScene(); // object of type MenuScene is created and assigned to variable menuSc
		
		Scene menuScene = new Scene(menuSc.createMenu()); //instantiate new Scene and create content for menu
		menuScene.setCursor(Cursor.HAND); //sets cursor type as HAND
		stage.setTitle("Brain Twister Game");	// sets title of window
		stage.setScene(menuScene); // sets scene for menu
		stage.setResizable(false); // does not enable the window to be resized
		
		// key event - quit program if esc is pressed
		menuScene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				// the sound that plays when the user clicks any button or returns to the game menu is obtained by calling getSelectSound() method of Sound Class
				// All of the sounds that are playable more than once during the application must be stopped before playing otherwise they sound only once
				sound.getSelectSound().stop(); 
				sound.getSelectSound().play();
				System.exit(0);
			}
		});
		
		//------------- GAME SCENE ------------
		
		MenuItem menuPlayButton = new MenuItem("PLAY"); // a MenuItem object is created and represents a button.
		menuPlayButton = MenuScene.playButton; // the behaviour of the playButton of MenuScene is assigned to the MenuItem object created on line 92 
		// launches game if play button is pressed
		menuPlayButton.setOnMouseClicked(event -> {
			
			sound.getStartSound().play(); // play MediaPlayer object obtained from getStartSound() method
		
			Pane root = new Pane(); // creates new Pane and assigns it to variable root. This is where all elements will be placed
			root.setPrefSize(840, 580); // sets the window size
			Player playerGreen = new Player();
			Node playerGreenNode = playerGreen.createGreenAvatar(); // green part of avatar is created and assigned to Node playerGreenNode
			Player playerRed = new Player();
			Node playerRedNode = playerRed.createRedAvatar(); // red part of avatar is created and assigned to Node playerGreenNode

			ArrayList<Life> life = new ArrayList<Life>(); // An Array List of type Life is created and assigned to var life
			ArrayList<Node> lifeNode = new ArrayList<Node>(); // An Array List of type Node is created and assigend to var lifeNode
	         
			// loop creates 3 objects of type Life (heart image) and adds them to the ArrayList
			for (int i=1; i<=3; i++) {
				Life lf = new Life(xSpacing);
				life.add(lf);
				xSpacing+=27; // sets a spacing of 27 pixels between each heart image
				
				Node lfNode = lf.createLife(); // obtains the heart image and assigns it to var lfNode
				lifeNode.add(lfNode); // lfNode is added to the Array List lifeNode
			}						
			
			Random random = new Random(); 
			answers = new ArrayList<Answer>(); // An Array List of type Answer is created and assigned to var answers
			answersNode = new ArrayList<Node>(); // An Array List of type Node is created and assigned to var asnwersNode
			
			// loop creates a number of answers defined by constant NUMBER_OF_ANSWERS and adds them to the two above Array Lists
			for (int i=1; i<=NUMBER_OF_ANSWERS; i++){
				// Creates new answer and send as parameters: 
				// int -> colour name - from 0 to 2 (3 options that will be translated into the colour name set as text)
				// int -> colour - from 0 to 2 (3 options that will be translated into the colour the text will be painted)
				// int -> xPosition - from 10 to 640 - will define the x coordinate of answer
				// int -> yPosition - from 50 to 200 - together with ySpacing, will define the y coordinate of answer
				// int -> ySpacing - sets the minimum distance from the first answer 
				Answer answer = new Answer(random.nextInt(3), random.nextInt(3), random.nextInt(630)+10, random.nextInt(50)+150, ySpacing);
				ySpacing += 100; // increments 100 at every answer created, so that the Array List maps a number of answers at different distances
				answers.add(answer);

				Node answerNode=answer.createAnswer(); // creates an answer of type Node so that it can be displayed on the root
				answersNode.add(answerNode);
			}
			
			Question question = new Question (random.nextInt(3)); // instantiate var question of type Question and send as param 3 options (from 0 to 2)
			Node questionNode = question.createQuestion(); // question is created and assigned to var questionNode of type Node
			questionText = (Text) (questionNode); // questionNode is assigned to var questionText of type Text through a cast
			
			// Score board that contains text "SCORE" and the score itself is created and assigned to var scoreBoard
			ScoreBoard scoreBoard = new ScoreBoard(setScore.toString());
			scoreBoard.setTranslateX(740);
			scoreBoard.setTranslateY(30);
			
			// var gameOver of type GameOver is initiated
			GameOver gameOver = new GameOver();
			
			// AnimationTimer is created and assigned of var timer
			timer = new AnimationTimer() {
	            // Method handle is ran 60 times per second as long as timer is started and until it is stopped
				@Override
	            public void handle(long now) {
	            	int i = 0; // var i represents the number of iterations. It is used to get the index of answersNode
	            	// for each loop that iterates over the answersNode ArrayList
	            	for (Node answerNode : answersNode) {
	            		// sets the speed of each answer according to the speed of each level
	            		answerNode.setTranslateY(answerNode.getTranslateY()+levelSpeed);
	            		// assign to var isColliding the return of the method isColliding, which verifies whether mouse coordinates are within answer`s bounds
	            		boolean isColliding = (playerRed.isColliding(answerNode, xPosition, yPosition));
	            		currentAnswer = (Text) (answersNode.get(i)); // the answer is obtained from the ArrayList answersNode, casted into Text 
	            		// assign to var correctAnswer the return of the comparison between current answer and question in terms of text (name of colour) and fill (colour) 
	            		boolean correctAnswer = ((currentAnswer.getText()) == questionText.getText()) || ((currentAnswer.getFill()) == questionText.getFill());
	            		// statement that checks whether there is a collision
	            		if (isColliding) {
	            			// compares if the answers where collision happened has the same colour or name as the question	          
	            			if (correctAnswer) {	
	            				// plays MenuPlayer object obtained from getCorrectSound() method, that regards to the sound played when the user gets the answer correct
	            				sound.getCorrectSound().stop();
	            				sound.getCorrectSound().play();
	            				
	            				score+=scoreMultiplier;	 // increases score according with the level multiplier           				
	            				levelCount++; // increases the level count by one
	            				//System.out.println(levelCount);
	            				checkLevel(); // checks whether it is time to increase level
	            				scoreBoard.updateScore(Integer.toString(score)); // updates value of graphic score    				
	            				root.getChildren().remove(answerNode); // remove answer from root (window)
	            				answersNode.remove(answerNode); // remove answer from ArrayList
	            				// restart scoreBoard
	            				root.getChildren().remove(scoreBoard); 
	            				root.getChildren().add(scoreBoard);
	            				break; // breaks so that the iteration can start again with the updated ArrayList
	            			}
	            			else {
	            				// plays the sound that represents a wrong choice from the user
	            				sound.getWrongSound().stop();
	            				sound.getWrongSound().play();
	            				score-=scoreMultiplier; // reduces score according with the level multiplier   
	            				scoreBoard.updateScore(Integer.toString(score)); // updates value of graphic score  
		                		lives--; // reduces the number of lives by one
	            				root.getChildren().remove(answerNode); // remove answer from root (window)
	            				answersNode.remove(answerNode); // remove answer from ArrayList
	            				
	            				// removes heart image from root
	            				for (Node lfNode:lifeNode) {
	            					root.getChildren().remove(lfNode);
	            				}
	            				// remove one heart image from ArrayList and add it again to root
	            				lifeNode.remove(lives);
	            				for (Node lfNode:lifeNode) {
	            					root.getChildren().add(lfNode);
	            				}
	            				// restart scoreBoard
	            				root.getChildren().remove(scoreBoard);
	            				root.getChildren().add(scoreBoard);
	            				// this statement will play the sound that indicates the user has only one life left
	            				if (lives == 1) {
	            					sound.getLastLifeSound().play();
	            				}
		                		// statement that verifies whether number of lives is equal to zero
	            				if (lives<=0) {
	            					// Interrupts any sound that may be playing and plays game over sound 
	            					sound.getWrongSound().stop();
	            					sound.getLastLifeSound().stop();
	            					sound.getGameOverSound().play();
		                			isDead(); // calls method that stops timer
		                			root.setOpacity(0.7); // applies opacity to window
		                			// sets coordinates of GAME OVER banner
		                			gameOver.setTranslateX(45); 
		                			gameOver.setTranslateY(160);
		                			root.getChildren().add(gameOver); // adds GAME OVER banner to root
		                			recordScore(root);
		                		}
	            				
		                		break;
	            			}	            				            			
	            		}
	            		// checks whether current answer has gone beyond window bounds
	            		if (currentAnswer.getTranslateY() > 625) {
	            			// if correct answer has gone beyond window bounds, user loses a life
	            			if (correctAnswer) {
	            				// sound that indicates an incorrect answer is played
	            				sound.getWrongSound().stop();
	            				sound.getWrongSound().play();
	            				score-=scoreMultiplier; // reduces score according with the level multiplier 
	            				scoreBoard.updateScore(Integer.toString(score)); // updates value of graphic score
		            			lives--; 
		            			// remove one heart image from root 
		            			for (Node lfNode:lifeNode) {
	            					root.getChildren().remove(lfNode);
	            				}
		            			// remove one heart image from ArrayList and adds them back to root
		            			lifeNode.remove(lives);
	            				for (Node lfNode:lifeNode) {
	            					root.getChildren().add(lfNode);
	            				}
	            				// this statement will play the sound that indicates the user has only one life left
	            				if (lives == 1) {
	            					sound.getLastLifeSound().play();
	            				}
	            				// checks whether number of lives is equal to 0
		                		if (lives<=0) {
		                			// Interrupts any sound that may be playing and plays game over sound 
		                			sound.getLastLifeSound().stop();
		                			sound.getWrongSound().stop();
		                			sound.getGameOverSound().play();
		                			
		                			isDead(); // calls method that stops timer
		                			root.setOpacity(0.7); // applies opacity to root
		                			// sets coordinates of GAME OVER banner
		                			gameOver.setTranslateX(45);
		                			gameOver.setTranslateY(160);
		                			root.getChildren().add(gameOver); // adds GAME OVER banner to root		                	 
		                			recordScore(root);
		                		}	                		
	            			}
	            			// removes answerNode from root and ArrayList
		            		root.getChildren().remove(answerNode);
	                		answersNode.remove(answerNode);
	                		break;	
	            		}	
	            		i++; // increments iterator
	            	}	        
	            }
			};
			
	        timer.start(); // starts timer
	        
	        // adds all answers to root
	        for (Node answerNode:answersNode) {
	        	root.getChildren().add(answerNode);
	        }
	        
	        // adds heart images to root
	        for (Node lfNode:lifeNode) {
	        	root.getChildren().add(lfNode);
	        }      
	        
	        // adds avatars, question and score board to root
	        root.getChildren().addAll(playerRedNode,playerGreenNode, questionNode, scoreBoard);	        
			Scene gameScene = new Scene (root, 840, 580, Color.WHITESMOKE); // creates new scene 
			gameScene.setCursor(Cursor.NONE); // hides mouse cursor
			stage.setScene(gameScene); // sets gameScene to stage (window)
			stage.setResizable(false); // unables window resizing

			// sets the coordinates of avatars and variables to the same of mouse coordinates
			gameScene.setOnMouseMoved(e -> { 				
				playerRedNode.setLayoutX(e.getX());
				playerRedNode.setLayoutY(e.getY());
				playerGreenNode.setLayoutX(e.getX());
				playerGreenNode.setLayoutY(e.getY());
				xPosition = e.getX();
				yPosition = e.getY();
			});
			
			// if ESC is pressed, game is terminated and program goes back to game menu with active functionalities
			gameScene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ESCAPE) {
					// Interrupts any sound that may be playing and plays selection sound 
					sound.getStartSound().stop();
					sound.getGameOverSound().stop();
					sound.getSelectSound().stop();
					sound.getSelectSound().play();

					isDead(); // calls stops timer
					resetGame(); // calls method that resets variables required for game to be playable again
					menuScene.setCursor(Cursor.HAND); // set mouse cursor to HAND
					stage.setTitle("Brain Twister Game");	// sets title of window
					stage.setScene(menuScene); // sets menuScene to stage (window)
					stage.setResizable(false); // does not enable the window to be resized		
				}
			});
		
		});	

		//------------ LEADERBOARD SCENE -------------- 
		
		MenuItem menuLeaderButton = new MenuItem("LEADERBOARDS");
		menuLeaderButton = MenuScene.leaderboardsButton;
		
		menuLeaderButton.setOnMouseClicked(event -> {
			// plays selection sound 
			sound.getSelectSound().stop();
			sound.getSelectSound().play();

			highScore = highScoreManager.getHighscoreString(); 
			LeaderBoardScene leaderBoardSc = new LeaderBoardScene(highScore);
			Scene leaderBoardScene = new Scene(leaderBoardSc.createInstructions());
			leaderBoardScene.setCursor(Cursor.HAND);
			stage.setTitle("Brain Twister Game - Leaderboard");
			stage.setScene(leaderBoardScene);
			stage.setResizable(false);
			
			leaderBoardScene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.BACK_SPACE) {
					// plays selection sound 
					sound.getSelectSound().stop();
					sound.getSelectSound().play();

					stage.setTitle("Brain Twister Game");	// sets title of window
					stage.setScene(menuScene);
					stage.setResizable(false); // does not enable the window to be resized
				}
			});			
		});
		
		MenuItem leaderBackButton = new MenuItem("BACK");
		leaderBackButton = LeaderBoardScene.backButton;
		
		leaderBackButton.setOnMouseClicked(event -> {
			// plays selection sound 
			sound.getSelectSound().stop();
			sound.getSelectSound().play();

			stage.setTitle("Brain Twister Game");	// sets title of window
			stage.setScene(menuScene);
			stage.setResizable(false); // does not enable the window to be resized
		});
		
		
		//------------- INSTRUCTIONS SCENE -------
		
		MenuItem menuInstructionsButton = new MenuItem("INSTRUCTIONS");
		menuInstructionsButton = MenuScene.instructionsButton;

		menuInstructionsButton.setOnMouseClicked(event -> {
			// plays selection sound 
			sound.getSelectSound().stop();
			sound.getSelectSound().play();

			InstructionsScene instructionsSc = new InstructionsScene();
			Scene instructionsScene = new Scene(instructionsSc.createInstructions());
			instructionsScene.setCursor(Cursor.HAND);
			stage.setTitle("Brain Twister Game - Instructions");
			stage.setScene(instructionsScene);
			stage.setResizable(false);
			
			instructionsScene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.BACK_SPACE) {
					// plays selection sound 
					sound.getSelectSound().stop();
					sound.getSelectSound().play();

					stage.setTitle("Brain Twister Game");	// sets title of window
					stage.setScene(menuScene);
					stage.setResizable(false); // does not enable the window to be resized
				}
			});			
		});
		
		MenuItem instBackButton = new MenuItem("BACK");
		instBackButton = InstructionsScene.backButton;
		
		instBackButton.setOnMouseClicked(event -> {
			// plays selection sound 
			sound.getSelectSound().stop();
			sound.getSelectSound().play();

			stage.setTitle("Brain Twister Game");	// sets title of window
			stage.setScene(menuScene);
			stage.setResizable(false); // does not enable the window to be resized
		});
		
		MenuItem menuQuitButton = new MenuItem("QUIT");
		menuQuitButton = MenuScene.quitButton;
		
		menuQuitButton.setOnMouseClicked(event -> {
			// plays selection sound 
			sound.getSelectSound().stop();
			sound.getSelectSound().play();

			System.exit(0);		// exits the program if button is clicked
		});
		
		stage.show(); // show the stage (window)
	}
	
	/**
	 * Method that prompts a text "Type your name", a textfield for the user to input his/her name and a SEND button that register the user's name and score
	 * @param root - elements must be added to root
	 */
	protected void recordScore(Pane root) {
		// creates a background rectangle that will be placed behind the other items in order to avoid visual conflict 
		// with the answers that may be on the screen when the game is over
		Rectangle background = new Rectangle(240, 150);
		background.setFill(Color.GHOSTWHITE);
		background.setTranslateX(300);
		background.setTranslateY(380);

		// creates the "Type your name" text
		Text typeYourName = new Text("TYPE YOUR NAME");
		typeYourName.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,40));
		typeYourName.setFill(Color.BLACK);
		typeYourName.setTranslateX(320);
		typeYourName.setTranslateY(420);
		
		// creates the textfield the user will input his/her name
		TextField userNameField = new TextField();
		userNameField.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,25.6));
		userNameField.setPromptText("YOUR NAME");
		userNameField.setTranslateX(320);
		userNameField.setTranslateY(430);
		
		// creates a MenuItem object that will work as button and will be used to register the user's name and score
		MenuItem sendButton = new MenuItem("SEND");
		sendButton.setTranslateX(320);
		sendButton.setTranslateY(480);
		
		root.getChildren().addAll(background, typeYourName, userNameField, sendButton); // all items are added to the root
		// if the user presses ENTER, the content of the textfield is saved as username and the score is registered under this username
		root.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				userName = userNameField.getText();
				if (userName.isEmpty()) { // username is defined as UNKNOWN if user saves score but does not type anything on textfield
					userName = "UNKNOWN";
				}
				highScoreManager.addScore(userName, score);	// username and score is added to highScoreManager
				root.getChildren().removeAll(background, typeYourName, userNameField, sendButton); // elements are removed from root
			}
		});
		// if the user clicks on SEND button, the content of the textfield is saved as username and the score is registered under this username
		sendButton.setOnMouseClicked(e -> {
			userName = userNameField.getText(); 
			if (userName.isEmpty()) { // username is defined as UNKNOWN if user saves score but does not type anything on textfield
				userName = "UNKNOWN";
			}
			highScoreManager.addScore(userName, score);	// username and score is added to highScoreManager
			root.getChildren().removeAll(background, typeYourName, userNameField, sendButton); // elements are removed from root
		});
	}

	/**
	 * Method that resets variables required for game to be playable again once program goes back to menu
	 */
	private void resetGame() {
		xPosition = 0;
		yPosition = 0;
		answers.clear();
		answersNode.clear();
		ySpacing = 0;
		xSpacing = 0;
		score = 0;
		lives = 3;
		levelSpeed = 2;
		levelCount = 0;
		scoreMultiplier = 10;
		setScore = 0;		
	}

	/**
	 * Method that checks whether number of correct answers is enough to increase level of game
	 */
	protected void checkLevel() {
		
		if (levelCount>=5 & levelCount<15) {
			levelSpeed = 3;
			scoreMultiplier = 15;
		}
		else if (levelCount>=15 & levelCount<30) {
			levelSpeed = 4;
			scoreMultiplier = 20;
		}
		else if (levelCount>=30 & levelCount<60) {
			levelSpeed = 5;
			scoreMultiplier = 30;
		}
		else if (levelCount>=60 & levelCount<100) {
			levelSpeed = 6;
			scoreMultiplier = 50;
		}	
		else if (levelCount>=100) {
			levelSpeed = 7;
			scoreMultiplier = 100;
		}	
	}

	/**
	 * Method that stops timer
	 */
	protected void isDead() {
		timer.stop();
	}


	//---------- CLASSES DESIGNATED TO DESIGN MENUITEM AND MENUBOX -------------------

	
	//-------------- MENUBOX ------------------
	
	/**
	 * MenuBox Class - dedicated to creating layout of buttons
	 * extends VBox, responsible for setting layout of items vertically
	 * @author Samu
	 *
	 */
	public static class MenuBox extends VBox{ // class MenuBox extends VBox: vertical layout for items
		/**
		 * MenuBox Constructor - adds a sepatator between each button
		 * @param items
		 */
		public MenuBox(MenuItem...items){ // when creating MenuBox instance, items is passed as parameter list
		// Ellipses (...) means that we can pass from 0 to as many items as we wish. Useful as sometimes we may not wish to pass any items
			getChildren().add(createSeparator()); // calls method that creates the first line that sits at the edge of the rectangles	
		
			for (MenuItem item : items){	// now that the first line was created, new separators are created according to the number of items
				getChildren().addAll(item, createSeparator());
			}
		}
	}
	
	//-------------- LINE SEPARATOR ------------
	
	/**
	 * Method that creates a separator
	 * @return separtor - line that will be placed between each button
	 */
	private static Line createSeparator(){
		Line separator = new Line();	// creates instance of a line
		separator.setEndX(200);		// sets length of separator as the same as the rectangle
		separator.setStroke(Color.WHITE);	// sets colour of the line
		return separator;		// retuns line back to be added to the menu
	}
	
	//------------- MENUITEM -------------------
	
	/**
	 * MenuItem Class - Creates the graphics of each button and sets its mouse events
	 * @author Samu
	 *
	 */
	public static class MenuItem extends StackPane{	// creates the menu structure
		
		/**
		 * MenuItem Constructor - Designs the button by placing a text with the name of the button inside a rectangle
		 * @param name - name of the button
		 */
		public MenuItem(String name){
			// defines set of colours of gradient
			LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{
				new Stop(0, Color.DARKVIOLET),	
				new Stop(0.1, Color.LIGHTGRAY),	
				new Stop(0.9, Color.LIGHTGRAY),	
				new Stop(1, Color.DARKVIOLET),	
			});
		
			Rectangle backgroundPane = new Rectangle(200,30); // creates a rectangle (background for the text) with dimensions 200x30
			backgroundPane.setFill(Color.LIGHTGRAY);
			backgroundPane.setOpacity(0.3); 		// sets opacity 0.3. Rectangle will be slightly transparent
			
			Text text = new Text(name);		// creates new text
			text.setFill(Color.GRAY);	// sets colour to Darkgray
			text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));
			// sets font, style and size
			
			setAlignment(Pos.CENTER);	// text is centred on stack pane
			getChildren().addAll(backgroundPane, text);	// text appears on background
			
			setOnMouseEntered(event -> {  	// event for when mouse hover over rectangle
				backgroundPane.setFill(gradient);	// sets colour of variable gradient to backgroundPane 
				text.setFill(Color.BLACK); 	// sets colour of text to white
			});
			
			setOnMouseExited(event -> {		// event for when mouse leaves the rectangle area
				backgroundPane.setFill(Color.LIGHTGRAY);
				text.setFill(Color.GRAY);
			});
			
			setOnMousePressed(event -> {
				backgroundPane.setFill(Color.DARKVIOLET); 	// sets the background`s colour to dark violet when rectangle is pressed
				//backgroundPane.setEffect(new GaussianBlur(3.5));
				backgroundPane.setOpacity(0.8); 
				text.setFill(Color.WHITE);
			});
			
			setOnMouseReleased(event -> {		// when click is released, background assumes colour defined for gradient
				backgroundPane.setFill(gradient);
				backgroundPane.setOpacity(0.3); 
				text.setFill(Color.BLACK);
			});		
		}
	}
}