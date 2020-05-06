package com.example.banktest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public abstract class Card implements Serializable {

    protected String cardID;
    protected Date expirationDate;
    protected String cardType;

    public Card(String cardID, Date expirationDate, String cardType){
        this.cardID = cardID;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
    }

    public Card(JSONObject obj) {
        try {
            this.cardID = obj.getString("cardID");
            this.expirationDate = new Date(obj.getLong("expirationDate"));
            this.cardType = obj.getString("cardType");
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }

    public static Card makeCard(JSONObject obj){
        try {
            if (CreditCard.CardType.equals(obj.getString("cardType"))){
                return new CreditCard(obj);
            } else if (DebitCard.CardType.equals(obj.getString("cardType"))){
                return new DebitCard(obj);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject makeJSONObject(){
        JSONObject obj = new JSONObject() ;

        try {
            obj.put("cardID", cardID);
            obj.put("expirationDate", expirationDate.getTime());
            obj.put("cardType", cardType);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }


}
