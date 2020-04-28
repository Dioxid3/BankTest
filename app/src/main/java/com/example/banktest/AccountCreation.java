package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
    }


}
