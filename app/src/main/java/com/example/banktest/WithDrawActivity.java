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

public class WithDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw);

        final Spinner accountSpinner = (Spinner)findViewById(R.id.wa_accountSpinner);
        final TextView balance = (TextView)findViewById(R.id.wa_balance);
        final EditText amount = (EditText)findViewById(R.id.wa_amount);
        final TextView errorMsg = (TextView)findViewById(R.id.wa_errorMessage);
        Button withDrawBtn = (Button)findViewById(R.id.wa_withDrawBtn);

        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String accID = parent.getItemAtPosition(position).toString();
                Account account = AccountUtility.getAccount(accID);
                balance.setText(account.getBalance() + " €");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                balance.setText("Please select an account");
            }
        });

        String[] arraySpinner = AccountUtility.getAccountIDs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);

        withDrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountID = accountSpinner.getSelectedItem().toString();
                Account account = AccountUtility.getAccount(accountID);
                try {
                    double amountDouble = Double.parseDouble(amount.getText().toString());
                    if (amountDouble<= account.getBalance()) {
                        account.withDraw(amountDouble, WithDrawActivity.this);
                        Transaction transaction = new Transaction(accountID, amountDouble, false);
                        JsonFileUtility.saveFile(transaction.makeJSONObject(), "history/" + accountID, transaction.getTime(), WithDrawActivity.this);
                        Intent intent = new Intent(WithDrawActivity.this, SecondActivity.class);
                        startActivity(intent);
                    } else {
                        errorMsg.setText("Not enough balance");
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
