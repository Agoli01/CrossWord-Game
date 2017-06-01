package application;

import javafx.beans.property.*;

public class LBreturn {

	private final SimpleIntegerProperty rank;
	private final SimpleStringProperty uname;
	private final SimpleIntegerProperty points;
	private final SimpleIntegerProperty time;
	
	public LBreturn(Integer rank, String uname, Integer points, Integer time) {
		super();
		this.rank = new SimpleIntegerProperty(rank);
		this.uname = new SimpleStringProperty(uname);
		this.points = new SimpleIntegerProperty(points);
		this.time = new SimpleIntegerProperty(time);
	}
	
	public Integer getRank() {
		if(rank!=null)
			return rank.get();
		return 0;
	}
	
	public String getUname() {
		return uname.get();
	}
	
	public Integer getPoints() {
		if(points!=null)
			return points.get();
		return 0;
	}
	
	public Integer getTime() {
		return time.get();
	}
}