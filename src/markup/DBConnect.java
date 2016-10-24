package markup;
import java.sql.*;

public class DBConnect {
	private Connection connection;
	private Statement statement;
	public ResultSet result;
	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/redventure","root", ""); //TODO replace inner strings with database login and location:  "jdbc:mysql://database location", "Database Username", "password"
			statement = connection.createStatement();
		}catch(Exception e){
			System.out.println("Database connection Error");
		}
	}
	public void insert(String query){
		try{
			statement.executeUpdate(query);
		}catch(Exception e){
			System.out.println("Error running query: "+ query);
		}
	}
	
	public void runQuery(String query){
		try{
			result = statement.executeQuery(query);
		}catch(Exception e){
			System.out.print("Error running query: "+ query);
		}
	}
}
