package com.example.banktest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Transaction {

    public enum TransactionType {DEPOSIT, PAY, MY_TRANSFER, WITHDRAW};

    private String accountIDFrom;
    private String accountIDTo;
    private double amount;
    private TransactionType transactionType;
    private String reference;
    private String message;
    private Date date;

    /**
     * This is deposit or withdraw type transaction
     * @param accountIDTo
     * @param amount
     * @param isDepositOrWithdraw true = deposit, false = withdraw
     */
    public Transaction (String accountIDTo, double amount, boolean isDepositOrWithdraw){
        this.accountIDTo = accountIDTo;
        this.amount = amount;
        if (isDepositOrWithdraw) {
            transactionType = TransactionType.DEPOSIT;
        } else {
            transactionType = TransactionType.WITHDRAW;
        }
        date = new Date();
    }

    /**
     * This is pay type transaction
     * @param accountIDFrom
     * @param accountIDTo
     * @param amount
     * @param reference
     * @param message
     * @param date
     */
    public Transaction (String accountIDFrom, String accountIDTo, double amount, String reference, String message, Date date){

        this.accountIDFrom = accountIDFrom;
        this.accountIDTo = accountIDTo;
        this.amount = amount;
        this.reference = reference;
        this.message = message;
        if (date == null) {
            this.date = new Date();
        } else {
            this.date = date;
        }

        transactionType = TransactionType.PAY;
    }


    /**
     * This is my transfer type transaction
     * @param accountIDFrom
     * @param accountIDTo
     * @param amount
     */
    public Transaction (String accountIDFrom, String accountIDTo, double amount){
        this.accountIDFrom = accountIDFrom;
        this.accountIDTo = accountIDTo;
        this.amount = amount;
        date = new Date();
        transactionType = TransactionType.MY_TRANSFER;
    }

    /**
     * Makes transaction object from JSON object
     * @param obj
     */
    public Transaction(JSONObject obj) {

        try {
            this.accountIDFrom = obj.getString("accountIDFrom");
            this.accountIDTo = obj.getString("accountIDTo");
            this.amount = obj.getDouble("amount");
            this.transactionType = TransactionType.valueOf(obj.getString("transactionType"));
            this.reference = obj.getString("reference");
            this.message = obj.getString("message");
            this.date = new Date(obj.getLong("date"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /**
     * Makes JSONObject from this transaction
     * @return JSON object
     */
    public JSONObject makeJSONObject () {

        JSONObject obj = new JSONObject() ;

        try {
            obj.put("accountIDFrom", (accountIDFrom == null ? JSONObject.NULL : accountIDFrom));
            obj.put("accountIDTo", (accountIDTo == null ? JSONObject.NULL : accountIDTo));
            obj.put("amount", amount);
            obj.put("transactionType", (transactionType == null ? JSONObject.NULL : transactionType));
            obj.put("reference", (reference == null ? JSONObject.NULL : reference));
            obj.put("message", (message == null ? JSONObject.NULL : message));
            obj.put("date", (date == null ? JSONObject.NULL : date.getTime()));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }


    /**
     * Makes UI text to selected transaction type
     * @return UI text string
     */
    public String getUIText(){
        if(TransactionType.DEPOSIT.equals(transactionType)){
            return date + " Deposit " + accountIDTo + " " + amount + " €";
        } else if(TransactionType.PAY.equals(transactionType)) {
            return date + " Pay to " + accountIDTo + " " + amount + " €";
        } else if(TransactionType.MY_TRANSFER.equals(transactionType)) {
            return date + " My transfer from " + accountIDFrom + " to " + accountIDTo + " " + amount + " €";
        } else if(TransactionType.WITHDRAW.equals(transactionType)) {
            return date + " Withdraw " + accountIDTo + " " + amount + " €";

        } else {
            return "Error";
        }
    }

    /**
     * Gives time of transaction
     * @return Time String
     */
    public String getTime(){
        return date.getTime() + "";
    }

}
