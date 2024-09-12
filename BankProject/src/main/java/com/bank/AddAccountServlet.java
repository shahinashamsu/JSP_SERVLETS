package com.bank;

import com.bank.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addAccounts")
public class AddAccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("accountName");
        String accountNumber = request.getParameter("accountNumber");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String pin = request.getParameter("pin");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO accounts (accountName, accountNumber, balance, pin) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, accountName);
            stmt.setString(2, accountNumber);
            stmt.setDouble(3, initialBalance);
            stmt.setString(4, pin);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("showAllAccounts");

    }
}

