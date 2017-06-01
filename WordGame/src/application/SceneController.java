package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController { // Validate input fields and opens new stage
	
	@FXML
	private TextField name;
	@FXML
	private TextField username;
	@FXML
	private Label invalid_user;
	
	static String user = ""; 
	static int userScore = 0;
	
	static String path = null;
	static int level = 1;

	@FXML 
	private javafx.scene.control.Button startBtn;
	@FXML 
	private javafx.scene.control.Button exit;
	
	@FXML
	public void startGame(ActionEvent start_event) throws Exception {
		// Invalid input conditions
		if(name.getText() == null || name.getText().trim().isEmpty()) {
			invalid_user.setText("You must provide your name !");
		}
		else if(username.getText() == null || username.getText().trim().isEmpty()) {
			invalid_user.setText("You must provide your handle !");
		}
		else {
			LBhandler addUser = new LBhandler();
			String qu = "SELECT * FROM Leader WHERE user = '" +username.getText() +"' ";
			ResultSet rs = addUser.execQuery(qu);
			
			if(rs != null && rs.next()) {
				
				if(!rs.getString("name").equals(name.getText())) { 
					invalid_user.setText("Username already exist !"); }
				else {
					user = username.getText();
					invalid_user.setText("Welcome back "+username.getText());
					userScore = rs.getInt("points");
					addUser.closeDatabase();
					startGame();
				}
			}
		    else { 
			    addUser.closeDatabase();
			    //Inserting new user
			    user = username.getText();
			    userScore = 0;
			    LBhandler addNewUser = new LBhandler();
			    if(addNewUser .isInserted(user, name.getText())){
				    addNewUser.closeDatabase();
				    startGame(); }
		    }
	    }
    }
	
	public void startGame() throws IOException {
		
		Stage firstStage = new Stage();
		Parent root;
		
		if(level == 1) {
		  // Hiding previous stage before opening new stage
		  Stage stage = (Stage) startBtn.getScene().getWindow();
		  stage.close();
		  path = "/fxml/FirstLevel.fxml";
		}
		
		else if(level == 2) {
		  path = "/fxml/SecondLevel.fxml";
		}
		
		else if(level == 3) {
		  path = "/fxml/ThirdLevel.fxml";
		}
        
		try {
		   root = FXMLLoader.load(getClass().getResource(path));
		   Scene scene = new Scene(root);
		   firstStage.setScene(scene);
		   firstStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML // Exit the window when pressed exit button
	public void endGame(ActionEvent end_event) throws Exception {
		Stage stage = (Stage) exit.getScene().getWindow();
	    stage.close();
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
}