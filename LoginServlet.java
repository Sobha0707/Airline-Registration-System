package com.pace.airline;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/airline";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "swathi@123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                response.sendRedirect("home.html");
            } else {
                out.println("Invalid username or password.");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Database connection problem: " + e.getMessage());
        }
    }
}
