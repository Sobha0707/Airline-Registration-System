package com.pace.airline;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/addPassenger")
public class ServletInsert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String departureDate = request.getParameter("departureDate");
        String departureTime = request.getParameter("departureTime");
        int age = Integer.parseInt(request.getParameter("age"));
        String returnDate = request.getParameter("returnDate");
        String message = request.getParameter("message");
        String phoneNumber = request.getParameter("phoneNumber");

        System.out.println("Received form data:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Departure Date: " + departureDate);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Age: " + age);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Message: " + message);
        System.out.println("Phone Number: " + phoneNumber);

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbConnection.createConnection();
            String sql = "INSERT INTO passengers (name, email, `from`, `to`, departureDate, departureTime, age, returnDate, message, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, from);
            ps.setString(4, to);
            ps.setString(5, departureDate);
            ps.setString(6, departureTime);
            ps.setInt(7, age);

            if (returnDate == null || returnDate.isEmpty()) {
                ps.setNull(8, java.sql.Types.DATE);
            } else {
                ps.setString(8, returnDate);
            }

            ps.setString(9, message);
            ps.setString(10, phoneNumber);

            ps.executeUpdate();
            System.out.println("Insert successful");

            response.sendRedirect("success.html");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.html").forward(request, response);
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
