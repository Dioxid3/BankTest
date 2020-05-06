package com.example.banktest;

import org.json.JSONObject;

import java.util.Date;

public class DebitCard extends Card {

    public static String CardType = "Debit Card";

    public DebitCard(String cardID, Date expirationDate) {
        super(cardID, expirationDate, CardType);
    }

    public DebitCard(JSONObject obj){
        super(obj);
    }

    @Override
    public JSONObject makeJSONObject() {
        return super.makeJSONObject();
    }
}
