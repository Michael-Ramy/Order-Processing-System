package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	// for test only :-)
	
	private Connection connection;
	Statement statement = null;
	
	public DBConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false","root","root");
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void Execute(String statement) throws SQLException {
		this.statement = connection.createStatement();
		this.statement.executeUpdate(statement);
		this.statement.close();
	}
}
