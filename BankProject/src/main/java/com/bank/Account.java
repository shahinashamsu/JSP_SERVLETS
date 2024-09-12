package com.bank;

public class Account {
    private int id;
    private String accountName;
    private String accountNumber;
    private double balance;
    private String pin;

    // Constructor
    public Account() {
    }

    // Getters and Setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    // Optional: You can add a toString() method for easier debugging
    @Override
    public String toString() {
        return "Account [id=" + id + ", accountName=" + accountName + ", accountNumber=" + accountNumber + ", balance=" + balance + ", pin=" + pin + "]";
    }
}
