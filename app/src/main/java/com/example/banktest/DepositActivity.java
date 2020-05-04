package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DepositActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        final Spinner accountSpinner = (Spinner)findViewById(R.id.ad_accountsSpinner);

        final TextView accountBalance = (TextView)findViewById(R.id.da_accountBalance);

        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String accID = parent.getItemAtPosition(position).toString();
                Account account = AccountUtility.getAccount(accID);
                accountBalance.setText(account.getBalance() + " €" );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                accountBalance.setText("Please select an account");
            }
        });

        String[] arraySpinner = AccountUtility.getAccountIDs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.ad_depositBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amount = (EditText)findViewById(R.id.ad_amount);
                String accountID = accountSpinner.getSelectedItem().toString();
                Account account = AccountUtility.getAccount(accountID);
                try {
                    double amountDouble = Double.parseDouble(amount.getText().toString());
                    account.deposit(amountDouble, DepositActivity.this);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(DepositActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });
    }
}
