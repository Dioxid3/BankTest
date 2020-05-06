package com.example.banktest;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileData {

    private String name = "Matti Meikäläinen";
    private String address = "Skinnarila";
    private String phoneNumber = "+3584001234567";

    public ProfileData(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public ProfileData(){
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Makes JSON object from ProfileData class
     * @return JSON object
     */
    public JSONObject makeJSONObject () {

        JSONObject obj = new JSONObject() ;

        try {
            obj.put("name", name);
            obj.put("address", address);
            obj.put("phoneNumber", phoneNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
