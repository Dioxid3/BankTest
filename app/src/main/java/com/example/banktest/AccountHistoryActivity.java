package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;

public class AccountHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_history);

        Spinner accountSpinner = (Spinner)findViewById(R.id.aha_accountSpinner);


        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String accID = parent.getItemAtPosition(position).toString();
                Account account = AccountUtility.getAccount(accID);
                loadHistory(account);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LinearLayout historyEvents = (LinearLayout)findViewById(R.id.aha_historyEvents);
                historyEvents.removeAllViews();
            }
        });

        String[] arraySpinner = AccountUtility.getAccountIDs();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);


    }
    private void loadHistory(Account account){
        LinearLayout historyEvents = (LinearLayout)findViewById(R.id.aha_historyEvents);
        historyEvents.removeAllViews();
        File folder = new File(AccountHistoryActivity.this.getFilesDir() + "/history/" + account.getAccountID());
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            return;
        }
        for (int i = listOfFiles.length - 1; i>= 0; i--) {
            File file = listOfFiles[i];
            if (file.isFile()) {
                try {
                    JSONObject obj = JsonFileUtility.loadFile(file);
                    Transaction transaction = new Transaction(obj);
                    addTransactionToView(transaction);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void addTransactionToView(Transaction transaction){
        TextView textView1 = new TextView(this);
        textView1.setText(transaction.getUIText());


        LinearLayout ll = new LinearLayout(this);
        ll.addView(textView1);

        LinearLayout historyEvents = (LinearLayout)findViewById(R.id.aha_historyEvents);
        historyEvents.addView(ll);

    }
}
