package com.example.banktest;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class PayActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

        private TextView dateText;
        private DatePickerDialog.OnDateSetListener mDateSetListener;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pay);
            dateText = findViewById(R.id.tvShowDate);
            Button payButton = findViewById(R.id.pa_payBtn);

            findViewById(R.id.btnChooseDate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                showDatePickerDialog();
                }
            });


            final Spinner accountSpinner = (Spinner)findViewById(R.id.pa_accountSpinner);
            final TextView balance = (TextView)findViewById(R.id.pa_balance);
            final EditText amount = (EditText)findViewById(R.id.pa_amount);


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

            payButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    String accountID = accountSpinner.getSelectedItem().toString();
                    Account account = AccountUtility.getAccount(accountID);

                    try {
                        double doubleAmount  = Double.parseDouble(amount.getText().toString());
                        if (doubleAmount<= account.getBalance()){
                            account.withDraw(doubleAmount, PayActivity.this);
                            Intent intent = new Intent(PayActivity.this, SecondActivity.class);
                            startActivity(intent);
                        } else {
                            TextView errorMsg = findViewById(R.id.pa_errorMsg);
                            errorMsg.setText("Not enough balance");
                        }

                    } catch (Exception e){
                        e.printStackTrace();

                    }
                }
            });


        }

        private void showDatePickerDialog(){
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }

        @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Date of payment: " + dayOfMonth + "/" + (month + 1) + "/" + year;
                dateText.setText(date);
    }
}
