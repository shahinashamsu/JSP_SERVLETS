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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/debitAmounts")
public class DebitAmountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String pin = request.getParameter("pin");
        double amountToDebit = Double.parseDouble(request.getParameter("amountToDebit"));

        try (Connection conn = DBConnection.getConnection()) {
            String selectQuery = "SELECT balance, pin FROM accounts WHERE accountNumber = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            selectStmt.setString(1, accountNumber);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                String accountPin = rs.getString("pin");

                if (accountPin.equals(pin) && balance >= amountToDebit) {
                    String updateQuery = "UPDATE accounts SET balance = balance - ? WHERE accountNumber = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setDouble(1, amountToDebit);
                    updateStmt.setString(2, accountNumber);
                    updateStmt.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("showAllAccounts");
    }
}
