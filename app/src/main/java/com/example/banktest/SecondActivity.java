package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Tähän .csv-tiedosto joka on luotu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        Button editAccountBtn = (Button)findViewById(R.id.editAccountBtn);
        editAccountBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(SecondActivity.this, ProfileDataEditorActivity.class);
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

        Button transferBtn = (Button)findViewById(R.id.sa_transferBtn);
        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, TransferActivity.class);
                startActivity(intent);
            }
        });

        Button withDrawBtn = (Button)findViewById(R.id.sa_withDrawBtn);
        withDrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, WithDrawActivity.class);
                startActivity(intent);
            }
        });

        Button historyBtn = (Button)findViewById(R.id.sa_eventsHistoryBtn);
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, AccountHistoryActivity.class);
                startActivity(intent);
            }
        });

        Button addCardBtn = (Button)findViewById(R.id.sa_addCardBtn);
        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, InsertCardActivity.class);
                startActivity(intent);
            }
        });



    }
}
