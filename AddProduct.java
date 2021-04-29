package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProduct
{
	public static void addProduct(String name, double price,
			String desc) throws SQLException
	{
		final String DB_URL = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=ProductsDB;integratedSecurity=true";
		final String QUERY = "INSERT INTO Products(Name, Price, Description) "
				+ "VALUES(?, ?, ?)";
		
		Connection connection = DriverManager.getConnection(DB_URL);
		PreparedStatement preparedStatement = 
				connection.prepareStatement(QUERY);
		preparedStatement.setString(1, name);
		preparedStatement.setDouble(2, price);
		preparedStatement.setString(3, desc);
		
		preparedStatement.executeUpdate();
	}
	
	public static void main(String[] args)
	{
		try
		{
			addProduct("HP Wireless Mouse", 3000, "Wireless mouse device");
			
			System.out.println("Product added");
		}
		catch (SQLException e)
		{
			System.err.println(
					"An error occurred while adding a product: " + e);
		}
	}
}
