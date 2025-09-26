package com.pace.airline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	 private static Connection connection = null;

	    public static Connection createConnection() throws ClassNotFoundException, SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root",
	                    "swathi@123");
	            return connection;
	        } catch (SQLException e) {
	            // Handle exceptions
	            e.printStackTrace();
	            throw e;
	        }
	    }

	    // Closing connection
	    public static void closeConnection(Connection connection) {
	        try {
	            if (connection != null && !connection.isClosed()) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            // Handle exceptions
	            e.printStackTrace();
	        }

	    }

}
