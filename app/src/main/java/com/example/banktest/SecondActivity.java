package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        Button editAccountBtn = (Button)findViewById(R.id.editAccountBtn);
        editAccountBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(SecondActivity.this, ProfileDataEditor.class);
             startActivity(intent);
         }
        });

        Button showAccountsBtn = (Button)findViewById(R.id.showAccountsBtn);
        showAccountsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, AccountsActivity.class);
                startActivity(intent);
            }
        });

        Button depositBtn = (Button)findViewById(R.id.sa_depositBtn);
        depositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, DepositActivity.class);
                startActivity(intent);
            }
        });

        Button payBtn = (Button)findViewById(R.id.payBtn);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });



    }
}
