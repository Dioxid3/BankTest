package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class InsertCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_card);

        final Spinner accountSpinner = (Spinner)findViewById(R.id.ic_accountSpinner);
        final Spinner cardSpinner = (Spinner)findViewById(R.id.ic_cardSpinner);
        final TextView creditLimitTextView = (TextView)findViewById(R.id.ic_creditLimitTV);
        final EditText creditLimit = (EditText)findViewById(R.id.ic_creditLimit);
        final TextView euroSign = (TextView)findViewById(R.id.ic_euroSign);
        Button addCardButton = (Button)findViewById(R.id.ic_addCardBtn);

        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String accID = parent.getItemAtPosition(position).toString();
                Account account = AccountUtility.getAccount(accID);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] accountIDs = AccountUtility.getAccountIDs();
        ArrayList<String> accountsWithOutCard = new ArrayList<>();
        for (int i = 0; i<accountIDs.length; i++){
            Account account = AccountUtility.getAccount(accountIDs[i]);
            if (!account.hasCard()){
                accountsWithOutCard.add(account.getAccountID());
            }
        }
        String[] arraySpinner = accountsWithOutCard.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);

        String[] cardTypes = {"Credit Card", "Debit Card"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cardTypes);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardSpinner.setAdapter(adapter2);

        cardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cardType = parent.getItemAtPosition(position).toString();
                if ("Credit Card".equals(cardType)){
                    creditLimitTextView.setVisibility(View.VISIBLE);
                    creditLimit.setVisibility(View.VISIBLE);
                    euroSign.setVisibility(View.VISIBLE);
                } else if ("Debit Card".equals(cardType)){
                    creditLimitTextView.setVisibility(View.INVISIBLE);
                    creditLimit.setVisibility(View.INVISIBLE);
                    euroSign.setVisibility(View.INVISIBLE);
                } else{
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                creditLimitTextView.setVisibility(View.INVISIBLE);
                creditLimit.setVisibility(View.INVISIBLE);
                euroSign.setVisibility(View.INVISIBLE);
            }
        });


        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object item = accountSpinner.getSelectedItem();
                if (item == null){
                    return;
                }
                String accountID = accountSpinner.getSelectedItem().toString();
                Account account = AccountUtility.getAccount(accountID);

                Object item2 = cardSpinner.getSelectedItem();
                if (item2 == null){
                    return;
                }
                String cardType = cardSpinner.getSelectedItem().toString();

                Card card = null;
                if ("Credit Card".equals(cardType)){
                    double creditLimitDouble = Double.parseDouble(creditLimit.getText().toString());
                    Date expirationDate = new Date(System.currentTimeMillis() + 2 * 365 * 24 * 60 * 60 * 1000); // Expiration date is two years from card creation time
                    card = new CreditCard(account.hashCode() + "", expirationDate, creditLimitDouble);
                } else if ("Debit Card".equals(cardType)){
                    Date expirationDate = new Date(System.currentTimeMillis() + 2 * 365 * 24 * 60 * 60 * 1000); // Expiration date is two years from card creation time
                    card = new DebitCard(account.hashCode() + "", expirationDate);
                } else{
                    return;
                }
                AccountUtility.addCardToAccount(card, account, InsertCardActivity.this);




                Intent intent = new Intent(InsertCardActivity.this, SecondActivity.class);
                startActivity(intent);


            }
        });

    }
}
