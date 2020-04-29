package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        Button button = (Button)findViewById(R.id.newAccountBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountsActivity.this, AccountCreation.class);
                startActivity(intent);
            }
        });

        loadAccounts();
    }

    /**
     * Loads accounts from files
     */
    private void loadAccounts(){
        File folder = new File(AccountsActivity.this.getFilesDir() + "/accounts");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                addAccountToView(file);
            }
        }
    }

    /**
     * Adds account to view
     * @param file
     */
    private void addAccountToView(File file) {
        JSONObject obj = JsonFileUtility.loadFile(file);
        Account account = new Account(obj);
        TextView textView1 = new TextView(this);
        textView1.setText("ID: " + account.getAccountID() + " ");
        TextView textView2 = new TextView(this);
        textView2.setText("Name: " + account.getAccountName());
        Button button = new Button(this);
        button.setText("Edit");
        LinearLayout ll = new LinearLayout(this);
        ll.addView(textView1);
        ll.addView(textView2);
        ll.addView(button);
        LinearLayout accountsLayout = (LinearLayout)findViewById(R.id.accountsLayout);
        accountsLayout.addView(ll);

    }
}
