package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

public class AccountCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);


        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        Account.AccountType[] typeArray = Account.AccountType.values();
        String[] arraySpinner = new String[typeArray.length];

        for (int i=0; i<arraySpinner.length;i++) {
            arraySpinner[i] = typeArray[i].name();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button createButton = (Button)findViewById(R.id.newAccountBtn);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText accountIDText = (EditText)findViewById(R.id.accountIDText);
                EditText accountNameText = (EditText)findViewById(R.id.accountNameText);
                Spinner spinnerAccountType = (Spinner)findViewById(R.id.spinner);
                CheckBox canPayCheck = (CheckBox)findViewById(R.id.checkBox);

                String accountID = accountIDText.getText().toString();
                String accountName = accountNameText.getText().toString();
                String accountType = spinnerAccountType.getSelectedItem().toString();
                boolean canPay = canPayCheck.isChecked();

                Account newAccount = new Account(Account.AccountType.valueOf(accountType), accountID, accountName, canPay);
                JSONObject json = newAccount.makeJSONObject();
                JsonFileUtility.saveFile(json, "accounts", accountID, AccountCreation.this);

                Intent intent = new Intent(AccountCreation.this, AccountsActivity.class);
                startActivity(intent);
            }
        });
    }


}
