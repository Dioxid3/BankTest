package com.example.banktest;

import org.json.JSONException;
import org.json.JSONObject;

public class Account {



    public enum AccountType {SAVINGS_ACCOUNT, CURRENT_ACCOUNT};

    private AccountType accountType = AccountType.CURRENT_ACCOUNT;
    private String accountID = "FI12 9000 3939 5999 94";
    private String accountName = "Toukon tili";
    private double balance = 0.0;
    private double usageLimit = 100;
    private boolean canPay = true;

    public Account(AccountType accountType, String accountID, String accountName, boolean canPay) {
        this.accountType = accountType;
        this.accountID = accountID;
        this.accountName = accountName;
        this.canPay = canPay;
    }

    public Account() {
    }

    public Account(JSONObject obj) {

        try {
            this.accountType = AccountType.valueOf(obj.getString("accountType"));
            this.accountID = obj.getString("accountID");
            this.accountName = obj.getString("accountName");
            this.balance = obj.getDouble("balance");
            this.usageLimit = obj.getDouble("usageLimit");
            this.canPay = obj.getBoolean("canPay");
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    public String getAccountID() {
        return accountID;
    }



    public JSONObject makeJSONObject () {

        JSONObject obj = new JSONObject() ;

        try {
            obj.put("accountType", accountType);
            obj.put("accountID", accountID);
            obj.put("accountName", accountName);
            obj.put("balance", balance);
            obj.put("usageLimit", usageLimit);
            obj.put("canPay", canPay);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
