package com.pace.airline;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet("/Passenger data")

public class PassengersListServlet extends HttpServlet{
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	       try {
	            // Establish connection to the database
	          try {
				conn = DbConnection.createConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //  if(conn==null) {
	        	//  out.println("<html><body><h3>Unable to connect to the database.</h3></body></html>");
	             //   return;
	          //}

	            // Prepare the SQL query to retrieve passenger data
	            String query = "SELECT * FROM passengers";
	            ps = conn.prepareStatement(query);

	            // Execute the query
	            rs = ps.executeQuery();

	            // Print HTML table header
	            out.println("<html><head><title>Passenger List</title></head><body>");
	            out.println("<h2>Passenger List</h2>");
	            out.println("<table border='1'><tr><th>id</th><th>name</th><th>email</th><th>from</th><th>to</th><th>departureDate</th><th>departureTime</th><th>age</th><th>returnDate</th><th>Message</th><th>PhoneNumber</th><th>Edit</th><th>Remove</th></tr>");

	            // Iterate over the result set and print each row as a table row
	            while (rs.next()) {
	                out.println("<tr>");
	                out.println("<td>" + rs.getInt("id") + "</td>");
	                out.println("<td>" + rs.getString("name") + "</td>");
	                out.println("<td>" + rs.getString("email") + "</td>");
	                out.println("<td>" + rs.getString("from") + "</td>");
	                out.println("<td>" + rs.getString("to") + "</td>");
	                out.println("<td>" + rs.getString("departureDate") + "</td>");
	                out.println("<td>" + rs.getString("departureTime") + "</td>");
	                out.println("<td>" + rs.getString("age") + "</td>");
	                out.println("<td>" + rs.getString("returnDate") + "</td>");
	                out.println("<td>" + rs.getString("message") + "</td>");
	                out.println("<td>" + rs.getString("phoneNumber") + "</td>");
	                out.println("<td><a href='updatePassengerForm?id=" + rs.getInt("id") + "'>Edit</a></td>");
	                out.println("<td><a href='deletePassenger?id=" + rs.getInt("id") + "'>Remove</a></td>");
	                out.println("</tr>");
	            }
	          
	            // Close the HTML table
	            out.println("</table></body></html>");
	        } 
	       catch (SQLException e) {
	            e.printStackTrace();
	            // Redirect to an error page if an exception occurs
	            response.sendRedirect("error.html");
	        } finally {
	            // Close resources
	            try {
	                if (rs != null) rs.close();
	                if (ps != null) ps.close();
	                if (conn != null) DbConnection.closeConnection(conn);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
