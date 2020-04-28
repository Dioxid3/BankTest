package com.example.banktest;

public class Account {

    public enum AccountType {SAVINGS_ACCOUNT, CURRENT_ACCOUNT};

    private AccountType accountType = AccountType.CURRENT_ACCOUNT;
    private String accountID = "FI12 9000 3939 5999 94";
    private String accountName = "Toukon tili";
    private double balance = 0.0;
    private double usageLimit = 100;
    private boolean canPay = true;

    public Account() {
    }

    /**
     * Adds money to account
     * @param amount
     */
    public void deposit(double amount) {
        if (amount < 0){
            return;
        }
        balance = balance + amount;

    }

    /**
     * Withdraws money from account
     * @param amount
     */
    public void withDraw(double amount) {
        if (amount < 0){
            return;
        }
        balance = balance - amount;
    }

    public double getBalance(){
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(double usageLimit) {
        this.usageLimit = usageLimit;
    }

    public boolean isCanPay() {
        return canPay;
    }

    public void setCanPay(boolean canPay) {
        this.canPay = canPay;
    }
}
