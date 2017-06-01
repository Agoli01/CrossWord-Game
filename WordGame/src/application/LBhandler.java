package application;

import java.sql.*;

public class LBhandler {

	Connection connection;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public LBhandler() {
		
		connection = SqliteConnection.connector();
		if(connection == null ) System.exit(1);
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public boolean isInserted(String user, String name) throws SQLException {
		
		String query = "INSERT INTO Leader values(?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, user);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, null);
			
			preparedStatement.execute();
			return true;
			
		} catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {}
	}
	
	public ResultSet execQuery(String query) {

		ResultSet result = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			result = preparedStatement.executeQuery();
			return result;
		} catch(SQLException e) {
				e.printStackTrace();
				return null;
		} finally {}
	}
	
	public boolean execAction(String query) {
		try {
			 preparedStatement = connection.prepareStatement(query);
			 preparedStatement.execute();
			 return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {}
	}
	
	public void closeDatabase() {
		
		if(preparedStatement!=null)
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(resultSet!=null)
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(connection!=null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}