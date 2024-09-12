<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bank.Account" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Accounts</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f0f0f0;
            margin-top: 20px;
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>All Accounts</h1>

    <table>
        <tr>
            <th>Account Number</th>
            <th>Account Name</th>
            <th>Balance</th>
            <th>Pin</th>
        </tr>

        <%
            List<Account> accounts = (List<Account>) request.getAttribute("accounts");

            if (accounts != null && !accounts.isEmpty()) {
                for (Account account : accounts) {
        %>
                    <tr>
                        <td><%= account.getAccountNumber() %></td>
                        <td><%= account.getAccountName() %></td>
                        <td><%= account.getBalance() %></td>
                        <td><%= account.getPin() %></td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="4">No accounts found.</td>
                </tr>
        <%
            }
        %>
    </table>

    <br><br>
    <a href="index.jsp"><button>Back to Home</button></a>
</body>
</html>
