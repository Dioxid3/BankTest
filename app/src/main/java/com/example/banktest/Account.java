package com.example.banktest;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Account implements Serializable {




    public enum AccountType {SAVINGS_ACCOUNT, CURRENT_ACCOUNT};

    private AccountType accountType = AccountType.CURRENT_ACCOUNT;
    private String accountID = "FI12 9000 3939 5999 94";
    private String accountName = "Toukon tili";
    private double balance = 0.0;
    private double usageLimit = 100;
    private boolean canPay = true;
    private Card card;

    /**
     * Constructs an account object
     * @param accountType
     * @param accountID
     * @param accountName
     * @param canPay
     */
    public Account(AccountType accountType, String accountID, String accountName, boolean canPay, Card card, double balance) {
        this.accountType = accountType;
        this.accountID = accountID;
        this.accountName = accountName;
        this.canPay = canPay;
        this.card = card;
        this.balance = balance;
    }

    /**
     * Constructs an account object from Json object
     * @param obj
     */
    public Account(JSONObject obj) {

        try {
            this.accountType = AccountType.valueOf(obj.getString("accountType"));
            this.accountID = obj.getString("accountID");
            this.accountName = obj.getString("accountName");
            this.balance = obj.getDouble("balance");
            this.usageLimit = obj.getDouble("usageLimit");
            this.canPay = obj.getBoolean("canPay");
            this.card = Card.makeCard(obj.getJSONObject("card"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public boolean canAfford(double doubleAmount) {
        double minimumLimit = 0;
        if (card != null){
            if (card instanceof CreditCard){
                CreditCard creditCard = (CreditCard)card;
                minimumLimit = -creditCard.getCreditLimit();
            }
        }
        if ((balance - doubleAmount) < minimumLimit){
            return false;
        }else{
            return true;
        }
    }


    /**
     * Adds money to account
     * @param amount
     */
    public void deposit(double amount, Context context) {
        if (amount < 0){
            return;
        }
        balance = balance + amount;
        JsonFileUtility.saveFile(this.makeJSONObject(), "accounts", this.getAccountID(), context);


    }

    /**
     * Withdraws money from account
     * @param amount
     */
    public void withDraw(double amount, Context context) {
        if (amount < 0){
            return;
        }
        if(!canAfford(amount)){
            return;
        }
        balance = balance - amount;
        JsonFileUtility.saveFile(this.makeJSONObject(), "accounts", this.getAccountID(), context);
    }

    /**
     * Checks if account has card
     * @return boolean, true = has card
     */
    public boolean hasCard(){
        return card != null;
    }

    public double getBalance(){
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }




    public String getAccountName() {
        return accountName;
    }



    public boolean isCanPay() {
        return canPay;
    }



    public String getAccountID() {
        return accountID;
    }


    /**
     * Makes Json representation of an account
     * @return JSONObject with account details
     */
    public JSONObject makeJSONObject () {

        JSONObject obj = new JSONObject() ;

        try {
            obj.put("accountType", accountType);
            obj.put("accountID", accountID);
            obj.put("accountName", accountName);
            obj.put("balance", balance);
            obj.put("usageLimit", usageLimit);
            obj.put("canPay", canPay);

            if (card != null){
                obj.put("card", card.makeJSONObject());
            }else{
                obj.put("card", JSONObject.NULL);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
