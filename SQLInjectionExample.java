package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionExample
{
	public static void main(String[] args) throws SQLException
	{
		String username = "bmv";
		String pw = "' or 1=1--";
		
		final String DB_URL = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=ProductsDB;integratedSecurity=true";
		final String QUERY = 
				"SELECT * FROM users where name = '" + username +
				"' and password = '" + pw + "'";
		
		Connection connection = DriverManager.getConnection(DB_URL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(QUERY);
		
		if(resultSet.next())
		{
			System.out.println("logged in");
		}
		else
		{
			System.out.println("incorrect username / password");
		}
	}
}
