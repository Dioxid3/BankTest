package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        Button button = (Button)findViewById(R.id.newAccountBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountsActivity.this, AccountCreationActivity.class);
                startActivity(intent);
            }
        });

        loadAccounts();
    }

    /**
     * Loads accounts from files
     */
    private void loadAccounts(){
        Account[] accounts = AccountUtility.getAccounts();
        for (int i = 0; i<accounts.length; i++) {
            addAccountToView(accounts[i]);
        }

    }

    /**
     * Adds account to view
     * @param account
     */
    private void addAccountToView(final Account account) {
        TextView textView1 = new TextView(this);
        textView1.setText(account.getAccountID() + " " + account.getAccountName() + " " + account.getBalance() + " €");
        Button button = new Button(this);
        button.setText("Edit");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountsActivity.this, AccountEditorActivity.class);
                intent.putExtra("Account", account);
                startActivity(intent);
            }
        });
        LinearLayout ll = new LinearLayout(this);
        ll.addView(textView1);
        ll.addView(button);
        LinearLayout accountsLayout = (LinearLayout)findViewById(R.id.accountsLayout);
        accountsLayout.addView(ll);

    }
}
