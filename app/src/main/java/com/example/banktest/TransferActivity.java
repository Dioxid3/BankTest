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

public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        final Spinner fromAccount = (Spinner)findViewById(R.id.ta_fromAccountSpinner);
        final Spinner toAccount = (Spinner)findViewById(R.id.ta_toAccountSpinner);
        final EditText amount = (EditText)findViewById(R.id.ta_amount);
        Button transfer = (Button)findViewById(R.id.ta_transferBtn);
        final TextView fromAccountBalance = (TextView)findViewById(R.id.ta_fromAccountBalance);
        final TextView toAccountBalance = (TextView)findViewById(R.id.ta_toAccountBalance);

        fromAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String accID = parent.getItemAtPosition(position).toString();
                Account account = AccountUtility.getAccount(accID);
                fromAccountBalance.setText(account.getBalance() + " €" );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fromAccountBalance.setText("Please select an account");
            }
        });

        toAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String accID = parent.getItemAtPosition(position).toString();
                Account account = AccountUtility.getAccount(accID);
                toAccountBalance.setText(account.getBalance() + " €");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                toAccountBalance.setText("Please select an account");
            }
        });

        String[] arraySpinner = AccountUtility.getAccountIDs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromAccount.setAdapter(adapter);
        toAccount.setAdapter(adapter);



        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountID1 = fromAccount.getSelectedItem().toString();
                Account account1 = AccountUtility.getAccount(accountID1);
                String accountID2 = toAccount.getSelectedItem().toString();
                Account account2 = AccountUtility.getAccount(accountID2);
                try {
                    double amountDouble = Double.parseDouble(amount.getText().toString());
                    if (amountDouble <= account1.getBalance()){
                        account1.withDraw(amountDouble, TransferActivity.this);
                        account2.deposit(amountDouble, TransferActivity.this);
                        Transaction transaction = new Transaction(accountID1, accountID2, amountDouble);
                        JsonFileUtility.saveFile(transaction.makeJSONObject(), "history/" + accountID1, transaction.getTime(), TransferActivity.this);
                        JsonFileUtility.saveFile(transaction.makeJSONObject(), "history/" + accountID2, transaction.getTime(), TransferActivity.this);
                        Intent intent = new Intent(TransferActivity.this, SecondActivity.class);
                        startActivity(intent);
                    } else{
                        TextView errorMsg = (TextView)findViewById(R.id.ta_errorMessage);
                        errorMsg.setText("Not enough balance");

                    }

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });




    }
}
