package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LeaderBoardController implements Initializable {

	@FXML 
	private TableView<LBreturn> table;
	@FXML 
	private TableColumn<LBreturn,Integer> rank;
	@FXML 
	private TableColumn<LBreturn,String> uname;
	@FXML 
	private TableColumn<LBreturn,Integer> points;
	@FXML 
	private TableColumn<LBreturn,Integer> time;
	
	public ObservableList <LBreturn> list = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		rank.setCellValueFactory(new PropertyValueFactory<LBreturn,Integer>("rank"));
		uname.setCellValueFactory(new PropertyValueFactory<LBreturn,String>("uname"));
		points.setCellValueFactory(new PropertyValueFactory<LBreturn,Integer>("points"));
		time.setCellValueFactory(new PropertyValueFactory<LBreturn,Integer>("time"));
		
		LBhandler lb = new LBhandler();
		String qu = "SELECT * FROM Leader ORDER BY POINTS DESC";
		ResultSet rs = lb.execQuery(qu);
		try {
			int g = 1;
			while(rs.next() && g<=10) {
				list.add(new LBreturn(g,rs.getString("name"),rs.getInt("points"),rs.getInt("time")));
				g++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setItems(list);
	}
}