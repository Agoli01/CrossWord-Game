package application; // Level-2 controller

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

public class SecondController implements Initializable {
	
	// Text fields for grids
	@FXML
	private TextField tf00, tf01, tf02, tf03, tf04, tf05;
	@FXML
	private TextField tf10, tf11, tf12, tf13, tf14, tf15;
	@FXML
	private TextField tf20, tf21, tf22, tf23, tf24, tf25;
	@FXML
	private TextField tf30, tf31, tf32, tf33, tf34, tf35;
	@FXML
	private TextField tf40, tf41, tf42, tf43, tf44, tf45;
	@FXML
	private TextField tf50, tf51, tf52, tf53, tf54, tf55;
	
	// Labels
	@FXML
	private Label score, hintLabel;
	
	private String sample = new String();
	
	@FXML 
	private javafx.scene.control.Button secondLevelExit;
	
	// For text field objects
	private TextField entry;
	
	// Score counter
	private int countAns = 0;
	
	// Time counter
	private int timeShow = 0;
	
	// Sets objects
	String [] setQue = {"LIGHTS","ELF","GIFTS","TREE","SANTA","JESUS","FAMILY"};
	String [] setHint = {"The lungs, especially the lungs of an animal slaughtered for food.", 
			             "A small, often mischievous creature considered to have magic powers.",
			             "Something that is bestowed voluntarily and without compensation.", 
			             "A perennial woody plant having a main trunk and usually a distinct crown.", 
			             "Santa Claus.",
			             "A teacher and prosphet whose life and teachings form the basis of christian.",
			             "Typically consisting parents and their children."};
	
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
		
		String w1, w2, w3, w4, w5, w6, w7;
        byte userscore[] = new byte[7];
        
		// Combining meaningful letters
		w1 = (tf00.getText() + tf01.getText() + tf02.getText() + tf03.getText() + tf04.getText() + tf05.getText()).toUpperCase();
		w2 = (tf20.getText() + tf21.getText() + tf22.getText()).toUpperCase();
		w3 = (tf02.getText() + tf12.getText() + tf22.getText() + tf32.getText() + tf42.getText()).toUpperCase();
		w4 = (tf04.getText() + tf14.getText() + tf24.getText() + tf34.getText()).toUpperCase();
        w5 = (tf05.getText() + tf15.getText() + tf25.getText() + tf35.getText() + tf45.getText()).toUpperCase();
        w6 = (tf40.getText() + tf41.getText() + tf42.getText() + tf43.getText() + tf44.getText()).toUpperCase();
        w7 = (tf50.getText() + tf51.getText() + tf52.getText() + tf53.getText() + tf54.getText() + tf55.getText()).toUpperCase();
        
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
		if(w7.equals(setQue[6]))
			userscore[6]=10;
		countAns = (userscore[0]+userscore[1]+userscore[2]+userscore[3]+userscore[4]+userscore[5]+userscore[6]);
		score.setText("SCORE : "+countAns);
	}
	
	// Action function to be made :P
	public void text(ActionEvent event) {}
	
	// For adding the points of user in database
	@FXML
	public void updatePoints(ActionEvent event) throws IOException {
		
		submit(new ActionEvent());
		UpdateScores.update(countAns, timeShow);
		
		Stage stage = (Stage) secondLevelExit.getScene().getWindow();
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
		
		 if(entry == tf00 || entry == tf01 || entry == tf03)
		 { 
			 hintLabel.setText(setHint[0]);
			 tf00.setStyle(color);
			 tf01.setStyle(color);
			 tf02.setStyle(color);
			 tf03.setStyle(color);
			 tf04.setStyle(color);
			 tf05.setStyle(color);
		 }
		 if(entry == tf20 || entry == tf21)
		 {
			 hintLabel.setText(setHint[1]);
			 tf20.setStyle(color);
			 tf21.setStyle(color);
			 tf22.setStyle(color);
		 }			 
		 if(entry == tf02 || entry == tf12 || entry == tf22 || entry == tf32)
		 {
			 hintLabel.setText(setHint[2]);
			 tf02.setStyle(color);
			 tf12.setStyle(color);
			 tf22.setStyle(color);
			 tf32.setStyle(color);
			 tf42.setStyle(color);
		 }						 
		 if(entry == tf04 || entry == tf14 || entry == tf24 || entry == tf34)
		 {
			 hintLabel.setText(setHint[3]);
			 tf04.setStyle(color);
			 tf14.setStyle(color);
			 tf24.setStyle(color);
			 tf34.setStyle(color);
		 }
		 if(entry == tf05 || entry == tf15 || entry == tf25 || entry == tf35 || entry == tf45)
		 {
			 hintLabel.setText(setHint[4]);
			 tf05.setStyle(color);
			 tf15.setStyle(color);
			 tf25.setStyle(color);
			 tf35.setStyle(color);
			 tf45.setStyle(color);
		 }			
		 if(entry == tf40 || entry == tf41 || entry == tf42 || entry == tf43 || entry == tf44)
		 {
			 hintLabel.setText(setHint[5]);
			 tf40.setStyle(color);
			 tf41.setStyle(color);
			 tf42.setStyle(color);
			 tf43.setStyle(color);
			 tf44.setStyle(color);
		 }			
		 if(entry == tf50 || entry == tf51 || entry == tf52 || entry == tf53 || entry == tf54 || entry == tf55)
		 {
			 hintLabel.setText(setHint[6]);
			 tf50.setStyle(color);
			 tf51.setStyle(color);
			 tf52.setStyle(color);
			 tf53.setStyle(color);
			 tf54.setStyle(color);
			 tf55.setStyle(color);
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
	tf00.setText(null); tf01.setText(null); tf02.setText(null); tf03.setText(null); tf04.setText(null); tf05.setText(null);
	tf10.setText(null); tf11.setText(null); tf12.setText(null); tf13.setText(null); tf14.setText(null); tf15.setText(null);
	tf20.setText(null); tf21.setText(null); tf22.setText(null); tf23.setText(null); tf24.setText(null); tf25.setText(null);
	tf30.setText(null); tf31.setText(null); tf32.setText(null); tf33.setText(null); tf34.setText(null); tf35.setText(null);
	tf40.setText(null); tf41.setText(null); tf42.setText(null); tf43.setText(null); tf44.setText(null); tf45.setText(null);
	tf50.setText(null); tf51.setText(null); tf52.setText(null); tf53.setText(null); tf54.setText(null); tf55.setText(null);
    
	// Non editable text fields, black colored
	tf10.setEditable(false);
	tf10.setStyle("-fx-background-color: black;");
	tf11.setEditable(false);
	tf11.setStyle("-fx-background-color: black;");
	tf30.setEditable(false);
	tf30.setStyle("-fx-background-color: black;");
	tf31.setEditable(false);
	tf31.setStyle("-fx-background-color: black;");
	tf13.setEditable(false);
	tf13.setStyle("-fx-background-color: black;");
	tf23.setEditable(false);
	tf23.setStyle("-fx-background-color: black;");
	tf33.setEditable(false);
	tf33.setStyle("-fx-background-color: black;");
}
}