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

@WebServlet("/creditAmounts")
public class CreditAmountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Direct reference to SessionFactory
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        // Initialize Hibernate SessionFactory
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        double amountToCredit = Double.parseDouble(request.getParameter("amountToCredit"));

        Session session = null;
        Transaction transaction = null;

        try {
            // Open a Hibernate session
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Fetch the account by account number
            Account account = session.createQuery("FROM Account WHERE accountNumber = :accountNumber", Account.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();

            if (account != null) {
                // Update the account balance
                account.setBalance(account.getBalance() + amountToCredit);
                session.update(account);  // Save the updated account
                transaction.commit();     // Commit the transaction
            } else {
                throw new ServletException("Account with account number " + accountNumber + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback transaction on error
            }
            e.printStackTrace();
            throw new ServletException(e);
        } finally {
            if (session != null) {
                session.close();  // Always close the session
            }
        }

        // Redirect to show all accounts after processing
        response.sendRedirect("showAllAccount");
    }

    @Override
    public void destroy() {
        // Close the SessionFactory when the servlet is destroyed
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
