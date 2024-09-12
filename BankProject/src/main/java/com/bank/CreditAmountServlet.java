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

@WebServlet("/creditAmounts")
public class CreditAmountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        double amountToCredit = Double.parseDouble(request.getParameter("amountToCredit"));

        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE accounts SET balance = balance + ? WHERE accountNumber = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, amountToCredit);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("showAllAccounts");
    }
}
