package application; // Level-1 controller

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FirstController implements Initializable {
	
	// Text fields for grids
	@FXML
	private TextField tf00, tf01, tf02, tf03, tf04;
	@FXML
	private TextField tf10, tf11, tf12, tf13, tf14;
	@FXML
	private TextField tf20, tf21, tf22, tf23, tf24;
	@FXML
	private TextField tf30, tf31, tf32, tf33, tf34;
	@FXML
	private TextField tf40, tf41, tf42, tf43, tf44;
	
	// Labels
	@FXML
	private Label score, hintLabel;
	
	private String sample = new String();
	
	@FXML 
	private javafx.scene.control.Button firstLevelExit;
	
	// For text field objects
	private TextField entry;
	
	// Score counter
	private int countAns = 0;
	
	// Time counter
	private int timeShow = 0;
	
	// Sets objects
	String [] setQue = {"FREE","SLEEP","RELAX","PEN","GOAL","EXAM"};
	String [] setHint = {"You might get a ____ toy with purchase.", 
			             "You should get about 8 hrs of this a night.", 
			             "If you get everything done with time to spare, you can kick back and ____ .", 
			             "Hint not written.", 
			             "We set these for ourselves.", 
			             "How was it?"};
	
    // Timer (Count down)
	@FXML
	private Label timer;
	private Integer seconds = 300;
	
	public void startTimer() throws Exception {
		
		Timeline time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		if(time!=null) {
			time.stop();
		}
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				seconds--;
				timer.setText(seconds.toString());
				timeShow = seconds;
				if(seconds == 0) {
					time.stop();
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle(null);
					alert.setHeaderText("Game Over ! Your time is over.");
					alert.show();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);
				}
			}
		});	
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}
	
	// Submit button function
	public void submit(ActionEvent sevent) {
		
		String w1, w2, w3, w4, w5, w6;
        byte userscore[] = new byte[6];
        
		// Combining meaningful letters
		w1 = (tf10.getText() + tf20.getText() + tf30.getText() + tf30.getText()).toUpperCase();
		w2 = (tf01.getText() + tf11.getText() + tf21.getText() + tf31.getText() + tf41.getText()).toUpperCase();
		w3 = (tf20.getText() + tf21.getText() + tf22.getText() + tf23.getText() + tf24.getText()).toUpperCase();
		w4 = (tf41.getText() + tf42.getText() + tf43.getText()).toUpperCase();
        w5 = (tf03.getText() + tf13.getText() + tf23.getText() + tf33.getText()).toUpperCase();
        w6 = (tf14.getText() + tf24.getText() + tf34.getText() + tf44.getText()).toUpperCase();
        
		// Compare words with users input
		if(w1.equals(setQue[0]))
			userscore[0]=10;
		if(w2.equals(setQue[1]))
			userscore[1]=10;
		if(w3.equals(setQue[2]))
			userscore[2]=10;
		if(w4.equals(setQue[3]))
			userscore[3]=10;
		if(w5.equals(setQue[4]))
			userscore[4]=10;
		if(w6.equals(setQue[5]))
			userscore[5]=10;
		countAns = (userscore[0]+userscore[1]+userscore[2]+userscore[3]+userscore[4]+userscore[5]);
		score.setText("SCORE : "+countAns);
	}
	
	// Action function to be made :P
	public void text(ActionEvent event) {}
	
	// For adding the points of user in database
	@FXML
	public void updatePoints(ActionEvent event) throws IOException {
		
		submit(new ActionEvent());
		UpdateScores.update(countAns, timeShow);
		
		Stage stage = (Stage) firstLevelExit.getScene().getWindow();
	    stage.close();
	}
	
	// Function for entry
	public void keyText(KeyEvent event) {

	 sample = ((TextField)event.getSource()).getText();
		if(sample!=null && sample.length()!=0)
			((TextField)event.getSource()).setEditable(false);
	}
	
	// Function for mouse click action
	public void hint(MouseEvent event) {
		
		sample = ((TextField)event.getSource()).getText();
		if(sample!=null)
			((TextField)event.getSource()).setEditable(true);
	}
	
	// Filling color when hover on grids
	public void colour(TextField entry, String color) {
		 color = "-fx-background-color: "+color;
		
		 if(entry == tf10 || entry == tf30 || entry == tf40)
		 { 
			 hintLabel.setText(setHint[0]);
			 tf10.setStyle(color);
			 tf20.setStyle(color);
			 tf30.setStyle(color);
			 tf40.setStyle(color);
		 }
		 if(entry == tf01 || entry == tf11 || entry == tf31)
		 {
			 hintLabel.setText(setHint[1]);
			 tf01.setStyle(color);
			 tf11.setStyle(color);
			 tf21.setStyle(color);
			 tf31.setStyle(color);
			 tf41.setStyle(color);
		 }			 
		 if(entry == tf20 || entry == tf21 || entry == tf22 || entry == tf23 || entry == tf24)
		 {
			 hintLabel.setText(setHint[2]);
			 tf20.setStyle(color);
			 tf21.setStyle(color);
			 tf22.setStyle(color);
			 tf23.setStyle(color);
			 tf24.setStyle(color);
		 }						 
		 if(entry == tf41 || entry == tf42 || entry == tf43)
		 {
			 hintLabel.setText(setHint[3]);
			 tf41.setStyle(color);
			 tf42.setStyle(color);
			 tf43.setStyle(color);
		 }
		 if(entry == tf03 || entry == tf13 || entry == tf33)
		 {
			 hintLabel.setText(setHint[4]);
			 tf03.setStyle(color);
			 tf13.setStyle(color);
			 tf23.setStyle(color);
			 tf33.setStyle(color);
		 }			
		 if(entry == tf14 || entry == tf34 || entry == tf44)
		 {
			 hintLabel.setText(setHint[5]);
			 tf14.setStyle(color);
			 tf24.setStyle(color);
			 tf34.setStyle(color);
			 tf44.setStyle(color);
		 }			
	}
	
	// Setting color to green
	public void setColor(MouseEvent mevent) {
		entry = (TextField)mevent.getSource();
		this.colour(entry,"#56ff5f;");
	}
	
	// Setting color to white
	public void resetColor(MouseEvent mevent) {
		entry = (TextField)mevent.getSource();
		this.colour(entry, "white;");
	}
	
	@FXML // Showing leader-board
	public void leaderBoardLoad(ActionEvent leader_event) {
		
		Stage primaryStage = new Stage();
		try {
			Parent home = FXMLLoader.load(getClass().getResource("/fxml/LeaderBoard.fxml"));
			Scene scene = new Scene(home);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//Function to initialize 
@Override
public void initialize(URL location, ResourceBundle resources) {
	
	try { startTimer(); } catch (Exception e) { // Starting timer
		e.printStackTrace();
	}
	
	// Initializing text fields to null
	tf00.setText(null); tf01.setText(null); tf02.setText(null); tf03.setText(null); tf04.setText(null);
	tf10.setText(null); tf11.setText(null); tf12.setText(null); tf13.setText(null); tf14.setText(null); 
	tf20.setText(null); tf21.setText(null); tf22.setText(null); tf23.setText(null); tf24.setText(null); 
	tf30.setText(null); tf31.setText(null); tf32.setText(null); tf33.setText(null); tf34.setText(null); 
	tf40.setText(null); tf41.setText(null); tf42.setText(null); tf43.setText(null); tf44.setText(null); 
    
	// Non editable text fields, black colored
	tf00.setEditable(false);
	tf00.setStyle("-fx-background-color: black;");
	tf02.setEditable(false);
	tf02.setStyle("-fx-background-color: black;");
	tf04.setEditable(false);
	tf04.setStyle("-fx-background-color: black;");
	tf12.setEditable(false);
	tf12.setStyle("-fx-background-color: black;");
	tf32.setEditable(false);
	tf32.setStyle("-fx-background-color: black;");
}
}