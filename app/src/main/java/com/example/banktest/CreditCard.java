package com.example.banktest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class CreditCard extends Card {

    private double creditLimit;
    public static String CardType = "Credit Card";

    public CreditCard(String cardID, Date expirationDate, double creditLimit) {
        super(cardID, expirationDate, CardType);
        this.creditLimit = creditLimit;
    }

    public CreditCard(JSONObject obj) {
        super(obj);
        try {
            this.creditLimit = obj.getDouble("creditLimit");
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public JSONObject makeJSONObject() {
        try {
            JSONObject obj = super.makeJSONObject();
            obj.put("creditLimit", creditLimit);
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
