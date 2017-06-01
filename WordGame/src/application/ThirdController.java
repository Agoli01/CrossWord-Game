package application; // Level-3 controller

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

public class ThirdController implements Initializable {
	
	// Text fields for grids
	@FXML
	private TextField tf00, tf01, tf02, tf03, tf04, tf05, tf06;
	@FXML
	private TextField tf10, tf11, tf12, tf13, tf14, tf15, tf16;
	@FXML
	private TextField tf20, tf21, tf22, tf23, tf24, tf25, tf26;
	@FXML
	private TextField tf30, tf31, tf32, tf33, tf34, tf35, tf36;
	@FXML
	private TextField tf40, tf41, tf42, tf43, tf44, tf45, tf46;
	@FXML
	private TextField tf50, tf51, tf52, tf53, tf54, tf55, tf56;
	@FXML
	private TextField tf60, tf61, tf62, tf63, tf64, tf65, tf66;
	
	// Labels
	@FXML
	private Label score, hintLabel;
	
	private String sample = new String();
	
	@FXML 
	private javafx.scene.control.Button thirdLevelExit;
	
	// For text field objects
	private TextField entry;
	
	// Score counter
	private int countAns = 0;
	
	// Time counter
	private int timeShow = 0;
	
	// Sets objects
	String [] setQue = {"SODA","THIRSTY","DRINK","SPRITE","COLD","TEA","POP","COKE","DEW","FLAVOR"};
	String [] setHint = {"Any of various forms of sodium carbonate.", 
			             "Desiring to drink.",
			             "To take into the mouth and swallow (a liquid).", 
			             "A small or elusive supernatural being; an elf or pixie.", 
			             "Having a low temperature.",
			             "An eastern Asian evergreen shrub having fragrant, nodding and cup shaped white flowers.",
			             "To make a short, sharp, explosive sound.",
			             "It is used as a fuel and in making steel.",
			             "Illegally distilled corn liquor.",
			             "Distinctive taste."};
	
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
		
		String w1, w2, w3, w4, w5, w6, w7, w8, w9, w10;
        byte userscore[] = new byte[10];
        
		// Combining meaningful letters
		w1 = (tf01.getText() + tf02.getText() + tf03.getText() + tf04.getText()).toUpperCase();
		w2 = (tf10.getText() + tf11.getText() + tf12.getText() + tf13.getText() + tf14.getText() + tf15.getText() + tf16.getText()).toUpperCase();
		w3 = (tf03.getText() + tf13.getText() + tf23.getText() + tf33.getText() + tf43.getText()).toUpperCase();
		w4 = (tf20.getText() + tf21.getText() + tf22.getText() + tf23.getText() + tf24.getText() + tf25.getText()).toUpperCase();
        w5 = (tf26.getText() + tf36.getText() + tf46.getText() + tf56.getText()).toUpperCase();
        w6 = (tf15.getText() + tf25.getText() + tf35.getText()).toUpperCase();
        w7 = (tf32.getText() + tf42.getText() + tf52.getText()).toUpperCase();
        w8 = (tf41.getText() + tf42.getText() + tf43.getText() + tf44.getText()).toUpperCase();
        w9 = (tf40.getText() + tf50.getText() + tf60.getText()).toUpperCase();
        w10 = (tf61.getText() + tf62.getText() + tf63.getText() + tf64.getText() + tf65.getText() + tf66.getText()).toUpperCase();
        
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
		if(w8.equals(setQue[7]))
			userscore[7]=10;
		if(w9.equals(setQue[8]))
			userscore[8]=10;
		if(w10.equals(setQue[9]))
			userscore[9]=10;
		countAns = (userscore[0]+userscore[1]+userscore[2]+userscore[3]+userscore[4]+userscore[5]+userscore[6]+userscore[7]+userscore[8]+userscore[9]);
		score.setText("SCORE : "+countAns);
	}
	
	// Action function to be made :P
	public void text(ActionEvent event) {}
	
	// For adding the points of user in database
	@FXML
	public void updatePoints(ActionEvent event) throws IOException {
		
		submit(new ActionEvent());
		UpdateScores.update(countAns, timeShow);
		
		Stage stage = (Stage) thirdLevelExit.getScene().getWindow();
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
		
		 if(entry == tf01 || entry == tf02 || entry == tf04)
		 { 
			 hintLabel.setText(setHint[0]);
			 tf01.setStyle(color);
			 tf02.setStyle(color);
			 tf03.setStyle(color);
			 tf04.setStyle(color);
		 }
		 if(entry == tf10 || entry == tf11 || entry == tf12 || entry == tf14 || entry == tf16)
		 {
			 hintLabel.setText(setHint[1]);
			 tf10.setStyle(color);
			 tf11.setStyle(color);
			 tf12.setStyle(color);
			 tf13.setStyle(color);
			 tf14.setStyle(color);
			 tf15.setStyle(color);
			 tf16.setStyle(color);
		 }			 
		 if(entry == tf03 || entry == tf13 || entry == tf33)
		 {
			 hintLabel.setText(setHint[2]);
			 tf03.setStyle(color);
			 tf13.setStyle(color);
			 tf23.setStyle(color);
			 tf33.setStyle(color);
			 tf43.setStyle(color);
		 }						 
		 if(entry == tf20 || entry == tf21 || entry == tf22 || entry == tf23 || entry == tf24 || entry == tf25)
		 {
			 hintLabel.setText(setHint[3]);
			 tf20.setStyle(color);
			 tf21.setStyle(color);
			 tf22.setStyle(color);
			 tf23.setStyle(color);
			 tf24.setStyle(color);
			 tf25.setStyle(color);
		 }
		 if(entry == tf26 || entry == tf36 || entry == tf46 || entry == tf56)
		 {
			 hintLabel.setText(setHint[4]);
			 tf26.setStyle(color);
			 tf36.setStyle(color);
			 tf46.setStyle(color);
			 tf56.setStyle(color);
		 }			
		 if(entry == tf15 || entry == tf35)
		 {
			 hintLabel.setText(setHint[5]);
			 tf15.setStyle(color);
			 tf25.setStyle(color);
			 tf35.setStyle(color);
		 }
		 if(entry == tf32 || entry == tf52)
		 {
			 hintLabel.setText(setHint[6]);
			 tf32.setStyle(color);
			 tf42.setStyle(color);
			 tf52.setStyle(color);
		 }		
		 if(entry == tf41 || entry == tf42 || entry == tf43 || entry == tf44)
		 {
			 hintLabel.setText(setHint[7]);
			 tf41.setStyle(color);
			 tf42.setStyle(color);
			 tf43.setStyle(color);
			 tf44.setStyle(color);
		 }
		 if(entry == tf40 || entry == tf50 || entry == tf60)
		 {
			 hintLabel.setText(setHint[8]);
			 tf40.setStyle(color);
			 tf50.setStyle(color);
			 tf60.setStyle(color);
		 }
		 if(entry == tf61 || entry == tf62 || entry == tf63 || entry == tf64 || entry == tf65 || entry == tf66)
		 {
			 hintLabel.setText(setHint[9]);
			 tf61.setStyle(color);
			 tf62.setStyle(color);
			 tf63.setStyle(color);
			 tf64.setStyle(color);
			 tf65.setStyle(color);
			 tf66.setStyle(color);
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
	tf00.setText(null); tf01.setText(null); tf02.setText(null); tf03.setText(null); tf04.setText(null); tf05.setText(null); tf06.setText(null);
	tf10.setText(null); tf11.setText(null); tf12.setText(null); tf13.setText(null); tf14.setText(null); tf15.setText(null); tf16.setText(null);
	tf20.setText(null); tf21.setText(null); tf22.setText(null); tf23.setText(null); tf24.setText(null); tf25.setText(null); tf26.setText(null);
	tf30.setText(null); tf31.setText(null); tf32.setText(null); tf33.setText(null); tf34.setText(null); tf35.setText(null); tf36.setText(null);
	tf40.setText(null); tf41.setText(null); tf42.setText(null); tf43.setText(null); tf44.setText(null); tf45.setText(null); tf46.setText(null);
	tf50.setText(null); tf51.setText(null); tf52.setText(null); tf53.setText(null); tf54.setText(null); tf55.setText(null); tf56.setText(null);
	tf60.setText(null); tf61.setText(null); tf62.setText(null); tf63.setText(null); tf64.setText(null); tf65.setText(null); tf66.setText(null);
    
	// Non editable text fields, black colored
	tf00.setEditable(false);
	tf00.setStyle("-fx-background-color: black;");
	tf05.setEditable(false);
	tf05.setStyle("-fx-background-color: black;");
	tf06.setEditable(false);
	tf06.setStyle("-fx-background-color: black;");
	tf30.setEditable(false);
	tf30.setStyle("-fx-background-color: black;");
	tf31.setEditable(false);
	tf31.setStyle("-fx-background-color: black;");
	tf34.setEditable(false);
	tf34.setStyle("-fx-background-color: black;");
	tf45.setEditable(false);
	tf45.setStyle("-fx-background-color: black;");
	tf51.setEditable(false);
	tf51.setStyle("-fx-background-color: black;");
	tf53.setEditable(false);
	tf53.setStyle("-fx-background-color: black;");
	tf54.setEditable(false);
	tf54.setStyle("-fx-background-color: black;");
	tf55.setEditable(false);
	tf55.setStyle("-fx-background-color: black;");
}
}