package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ReadProducts
{
	public static void readProducts() throws SQLException
	{
		final String DB_URL = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=ProductsDB;integratedSecurity=true";
		final String QUERY = "SELECT * FROM Products";
		
		Connection connection = DriverManager.getConnection(DB_URL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(QUERY);
		
		System.out.printf("%-3s%-40s%-15s%s%n",
				"ID", "Name", "Price", "Description");
//		System.out.println("ID\tName\t\tPrice\tDesc");
		while(resultSet.next())
		{
			int id = resultSet.getInt("ID");
			String name = resultSet.getString("Name");
			double price = resultSet.getDouble("Price");
			String desc = resultSet.getString("Description");
			
			System.out.printf("%-3d%-40s%-15.2f%s%n",
					id, name, price, desc);
		}
		System.out.println("\nNo more rows");
	}
	
	public static void main(String[] args)
	{
		try
		{
			readProducts();
		}
		catch (SQLException e)
		{
			System.err.println(
					"An error occurred while reading products: " + e);
		}
	}
}
