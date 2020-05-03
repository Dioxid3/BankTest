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
import android.widget.TextView;

import org.json.JSONObject;

public class AccountEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_editor);

        final Account account = (Account)getIntent().getSerializableExtra("Account");

        final TextView accountID = (TextView)findViewById(R.id.ae_accountID);
        final EditText accountName = (EditText)findViewById(R.id.ae_editName);
        final Spinner spinner = (Spinner)findViewById(R.id.ae_spinner2);
        final CheckBox canPay = (CheckBox)findViewById(R.id.ae_checkBox2);
        Button button = (Button)findViewById(R.id.ae_saveBtn);

        accountID.setText(account.getAccountID());
        accountName.setText(account.getAccountName());

        Account.AccountType[] typeArray = Account.AccountType.values();
        String[] arraySpinner = new String[typeArray.length];

        for (int i=0; i<arraySpinner.length;i++) {
            arraySpinner[i] = typeArray[i].name();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (account.getAccountType().name() != null) {
            int spinnerPosition = adapter.getPosition(account.getAccountType().name());
            spinner.setSelection(spinnerPosition);
        }

        canPay.setChecked(account.isCanPay());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String newAccountID = account.getAccountID();
                String newAccountName = accountName.getText().toString();
                String newAccountType = spinner.getSelectedItem().toString();
                boolean newCanPay = canPay.isChecked();

                Account newAccount = new Account(Account.AccountType.valueOf(newAccountType), newAccountID, newAccountName, newCanPay);
                AccountUtility.editAccount(newAccount, AccountEditor.this);

                Intent intent = new Intent(AccountEditor.this, AccountsActivity.class);
                startActivity(intent);
            }
        });
    }


}
