package com.bank;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showAllAccount")
public class ShowAllAccountsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        List<Account> accounts = new ArrayList<>();

        try {
            // Load the database driver class
            Class.forName("com.mysql.cj.jdbc.Driver");  // Replace with your database driver

            try (Connection conn = DBConnection.getConnection()) {
                if (conn != null) {
                    System.out.println("Database connection established.");
                } else {
                    System.out.println("Failed to establish database connection.");
                }

                try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts");
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        Account account = new Account();
                        account.setId(rs.getInt("id"));
                        account.setAccountName(rs.getString("accountName"));
                        account.setAccountNumber(rs.getString("accountNumber"));
                        account.setBalance(rs.getDouble("balance"));
                        account.setPin(rs.getString("pin"));
                        accounts.add(account);
                    }

                    if (accounts.isEmpty()) {
                        System.out.println("No accounts found.");
                    } else {
                        System.out.println("Accounts loaded: " + accounts.size());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            // This exception is thrown if the driver class is not found
            System.out.println("Database driver class not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }


        request.setAttribute("accounts", accounts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showAllAccounts.jsp");
        dispatcher.forward(request, response);
    }
}
