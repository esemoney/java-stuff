package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class GetProduct
{
	public static void getProduct(int id) throws SQLException
	{
		final String DB_URL = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=ProductsDB;integratedSecurity=true";
		final String QUERY = "SELECT * FROM Products WHERE ID = ?";
		
		Connection connection = DriverManager.getConnection(DB_URL);
		PreparedStatement preparedStatement = 
				connection.prepareStatement(QUERY);
		preparedStatement.setInt(1, id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		if(resultSet.next())
		{
			System.out.println("Product " + id);
			System.out.println(metaData.getColumnName(2) + ": " 
					+ resultSet.getString("Name"));
			
			System.out.println(metaData.getColumnName(3) + ": " 
					+ resultSet.getDouble("Price"));
			
			System.out.println(metaData.getColumnName(4) + ": " 
					+ resultSet.getString("Description"));
		}
		else
			System.out.printf("No product with id %d found%n", id);
	}
	
	public static void main(String[] args)
	{
		try
		{
			getProduct(2);
		}
		catch (SQLException e)
		{
			System.err.println(
					"An error occurred while getting product: " + e);
		}
	}
	
}
