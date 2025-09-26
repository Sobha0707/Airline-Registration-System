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

@WebServlet("/updatePassengerForm")
public class UpdatePassengerFormServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            try {
				conn = DbConnection.createConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String query = "SELECT * FROM passengers WHERE id=?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<html><head><title>Update Passenger</title></head><body>");
                out.println("<h2>Update Passenger Details</h2>");
                out.println("<form action='updatePassenger' method='post'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("Name: <input type='text' name='name' value='" + rs.getString("name") + "' required><br>");
                out.println("Email: <input type='email' name='email' value='" + rs.getString("email") + "' required><br>");
                out.println("From: <input type='text' name='from' value='" + rs.getString("from") + "' required><br>");
                out.println("To: <input type='text' name='to' value='" + rs.getString("to") + "' required><br>");
                out.println("Departure Date: <input type='date' name='departureDate' value='" + rs.getString("departureDate") + "' required><br>");
                out.println("Departure Time: <input type='time' name='departureTime' value='" + rs.getString("departureTime") + "' required><br>");
                out.println("Age: <input type='text' name='age' value='" + rs.getString("age") + "' required><br>");
                out.println("Return Date: <input type='date' name='returnDate' value='" + rs.getString("returnDate") + "'><br>");
                out.println("Message: <input type='text' name='message' value='" + rs.getString("message") + "'><br>");
                out.println("Phone Number: <input type='text' name='phoneNumber' value='" + rs.getString("phoneNumber") + "' required><br>");
                out.println("<input type='submit' value='Update Passenger'>");
                out.println("</form>");
                out.println("</body></html>");
            } else {
                out.println("No passenger found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
