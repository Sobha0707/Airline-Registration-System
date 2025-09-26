package com.pace.airline;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/updatePassenger")
public class ServletUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from request
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String departureDateStr = request.getParameter("departureDate");
        Date departureDate = Date.valueOf(departureDateStr); // Convert string to Date
        String departureTime = request.getParameter("departureTime");
        int age = Integer.parseInt(request.getParameter("age"));
        String returnDateStr = request.getParameter("returnDate");
        Date returnDate = Date.valueOf(returnDateStr); // Convert string to Date
        String message = request.getParameter("message");
        String phoneNumber = request.getParameter("phoneNumber");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Establish connection to the database
            conn = DbConnection.createConnection();
            
            // Prepare the SQL query for updating the passenger data
            String query = "UPDATE passengers SET name=?, email=?, `from`=?, `to`=?, departureDate=?, departureTime=?, age=?, returnDate=?, message=?, phoneNumber=? WHERE id=?";
            ps = conn.prepareStatement(query);
            
            // Set the parameters for the query
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, from);
            ps.setString(4, to);
            ps.setDate(5, departureDate);
            ps.setString(6, departureTime);
            ps.setInt(7, age);
            ps.setDate(8, returnDate);
            ps.setString(9, message);
            ps.setString(10, phoneNumber);
            ps.setInt(11, id);
            
            // Execute the query
            ps.executeUpdate();
            response.sendRedirect("listPassengers");
        } catch (Exception e) {
            e.printStackTrace();
            // Redirect to an error page
            response.sendRedirect("error.html");
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) DbConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
