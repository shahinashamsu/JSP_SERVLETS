package com.bank;

import com.bank.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/showAllAccount")
public class ShowAllAccountsServlet extends HttpServlet {

    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        // Initialize Hibernate SessionFactory
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        List<Account> accounts = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            // Begin transaction
            transaction = session.beginTransaction();

            // Fetch all accounts from the database using Hibernate
            accounts = session.createQuery("FROM Account", Account.class).list();

            // Commit the transaction
            transaction.commit();

            if (accounts == null || accounts.isEmpty()) {
                System.out.println("No accounts found.");
            } else {
                System.out.println("Accounts loaded: " + accounts.size());
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        // Set the list of accounts as a request attribute
        request.setAttribute("accounts", accounts);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("showAllAccounts.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
