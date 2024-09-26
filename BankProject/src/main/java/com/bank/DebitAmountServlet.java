package com.bank;

import com.bank.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/debitAmounts")
public class DebitAmountServlet extends HttpServlet {

    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String pin = request.getParameter("pin");
        double amountToDebit = Double.parseDouble(request.getParameter("amountToDebit"));

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Begin the transaction
            transaction = session.beginTransaction();

            // Fetch the account from the database using the account number
            Account account = session.createQuery("FROM Account WHERE accountNumber = :accountNumber", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();

            // Check if the account exists
            if (account != null) {
                // Validate the pin and check if the balance is sufficient
                if (account.getPin().equals(pin) && account.getBalance() >= amountToDebit) {
                    // Update the balance
                    account.setBalance(account.getBalance() - amountToDebit);
                    session.update(account);

                    // Commit the transaction to save the changes
                    transaction.commit();
                } else {
                    // Handle the case where pin doesn't match or insufficient funds
                    throw new ServletException("Invalid pin or insufficient balance.");
                }
            } else {
                throw new ServletException("Account not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback if any issue occurs
            }
            e.printStackTrace();
            throw new ServletException("Error during transaction.", e);
        }

        // Redirect after successful debit
        response.sendRedirect("showAllAccount");
    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
