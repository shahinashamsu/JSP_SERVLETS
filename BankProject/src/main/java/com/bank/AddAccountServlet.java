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

@WebServlet("/addAccounts")
public class AddAccountServlet extends HttpServlet {

    private static SessionFactory sessionFactory;

    @Override
    public void init() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountName = request.getParameter("accountName");
        String accountNumber = request.getParameter("accountNumber");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String pin = request.getParameter("pin");

        // Create new Account object
        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountNumber(accountNumber);
        account.setBalance(initialBalance);
        account.setPin(pin);

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Begin transaction
            transaction = session.beginTransaction();

            // Save account to database
            session.save(account);

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        // Redirect to another servlet or page
        response.sendRedirect("showAllAccounts");
    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
