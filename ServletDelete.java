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

//@WebServlet("/deletePassenger")
public class ServletDelete extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbConnection.createConnection();
            String query = "DELETE FROM passengers WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            response.sendRedirect("listPassengers");
        } catch (Exception e) {
            e.printStackTrace();
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
