package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountDataEditor extends AppCompatActivity {

    private EditText name;
    private EditText address;
    private EditText phoneNumber;
    private Button saveBtn;


    private static AccountData accountData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_data_editor);

        if (accountData == null) {
            Log.e("1.", "virhe");
            JSONObject obj =  JsonFileUtility.loadFile("accountData", AccountDataEditor.this);
            Log.e("2.", "virhe");
            if (obj == null){
                accountData = new AccountData();
            } else {
                try {
                    accountData = new AccountData(obj.getString("name"), obj.getString("address"), obj.getString("phoneNumber"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        name = (EditText)findViewById(R.id.nameEditText);
        address = (EditText)findViewById(R.id.addressEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        saveBtn = (Button)findViewById(R.id.saveBtn);

        name.setText(accountData.getName());
        address.setText(accountData.getAddress());
        phoneNumber.setText(accountData.getPhoneNumber());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountData.setName(name.getText().toString());
                accountData.setAddress(address.getText().toString());
                accountData.setPhoneNumber(phoneNumber.getText().toString());

                JSONObject obj = accountData.makeJSONObject();
                JsonFileUtility.saveFile(obj, "accountData", AccountDataEditor.this);

                Intent intent = new Intent(AccountDataEditor.this, SecondActivity.class);
                startActivity(intent);


            }
        });

    }
}
