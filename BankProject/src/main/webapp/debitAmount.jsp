<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Debit Amount</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            width: 100%;
        }
        h2 {
            text-align: center;
            color: #007bff;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-group input[type="number"] {
            -moz-appearance: textfield; /* Hide spinner in Firefox */
        }
        .form-group input[type="number"]::-webkit-inner-spin-button,
        .form-group input[type="number"]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        .form-group input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
            width: 100%;
        }
        .form-group input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .form-group a {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #007bff;
            text-decoration: none;
            font-size: 16px;
        }
        .form-group a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Debit Amount</h2>
        <form action="debitAmounts" method="post">
            <div class="form-group">
                <label for="accountNumber">Account Number:</label>
                <input type="text" id="accountNumber" name="accountNumber" required />
            </div>
            <div class="form-group">
                <label for="pin">PIN:</label>
                <input type="password" id="pin" name="pin" required />
            </div>
            <div class="form-group">
                <label for="amountToDebit">Amount to Debit:</label>
                <input type="number" id="amountToDebit" name="amountToDebit" step="0.01" required />
            </div>
            <div class="form-group">
                <input type="submit" value="Debit Amount" />
            </div>
            <div class="form-group">
                <a href="showAllAccounts.jsp">Show All Accounts</a>
            </div>
        </form>
    </div>
</body>
</html>
