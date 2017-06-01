package application;

import java.io.IOException;
import javafx.scene.control.Alert;

public class UpdateScores {
	
	private static int totalScore;
	private static int totalTime = 0;

	public static final void update(int countAns, int timeShow) throws IOException {
		
		if(SceneController.level == 1) { totalScore = 0; }
		else if(SceneController.level == 2) { totalScore = 60; }
		else if(SceneController.level == 3) { totalScore = 130; }
		
        totalScore = totalScore + countAns;
        totalTime = totalTime + (300-timeShow);
		LBhandler updateUser = new LBhandler();
		SceneController ob = new SceneController();
		totalScore = totalScore>SceneController.userScore?totalScore:SceneController.userScore;
		
		if(totalScore >= 130 && totalScore < 230 && SceneController.level == 1) {
			SceneController.level = 2;
		}
		
		if(totalScore >= 60 && totalScore < 130 && SceneController.level == 1) {
			SceneController.level = 2;
			ob.startGame(); }
				
		else if(totalScore >= 130 && totalScore < 230 && SceneController.level == 2) {
			SceneController.level = 3;
			ob.startGame(); }
		
		else if(totalScore == 230) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle(null);
			alert.setHeaderText("You Won ! And you got "+totalScore+" score points in "+totalTime+" seconds.");
			alert.showAndWait(); 
		}
		else {
			Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
			alert1.setTitle(null);
			alert1.setHeaderText("You got "+totalScore+" score points in "+totalTime+" seconds.");
			alert1.showAndWait(); }
		
		String qu = "UPDATE Leader SET points ="+ totalScore + ",time ="+totalTime + " WHERE user ='"+ SceneController.user +"' ";
		updateUser.execAction(qu);
		updateUser.execAction("DELETE from Leader WHERE points = 0");
	}
}