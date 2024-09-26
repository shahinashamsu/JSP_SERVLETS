<!DOCTYPE html>
<html>
<head>
    <title>Bank Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f0f0f0;
            margin-top: 100px;
        }
        h1 {
            font-size: 36px;
            margin-bottom: 30px;
        }
        .button-container {
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        .button-container a {
            text-decoration: none;
        }
        .button {
            padding: 20px 40px;
            font-size: 18px;
            color: #fff;
            background-color: #007bff; /* Same color for all buttons */
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #0056b3; /* Darker shade on hover */
        }
    </style>
</head>
<body>
    <h1>Welcome to the Bank Management System</h1>

    <!-- Container for buttons -->
    <div class="button-container">
        <a href="addAccount.jsp"><button class="button">Add Account</button></a>
        <a href="creditAmount.jsp"><button class="button">Credit Amount</button></a>
        <a href="debitAmount.jsp"><button class="button">Debit Amount</button></a>
        <a href="showAllAccount"><button class="button">Show All Accounts</button></a>
    </div>
</body>
</html>
