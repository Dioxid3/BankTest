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
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class PayActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

        private TextView dateText;
        private DatePickerDialog.OnDateSetListener mDateSetListener;
        private Date chosenDate = null;

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
            final EditText recipient = (EditText)findViewById(R.id.etRecipient);
            final EditText reference = (EditText)findViewById(R.id.etReference);
            final EditText message = (EditText)findViewById(R.id.etMessage);


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
                    Object item = accountSpinner.getSelectedItem();
                    if (item == null){
                        return;
                    }
                    String accountID = accountSpinner.getSelectedItem().toString();
                    Account account = AccountUtility.getAccount(accountID);
                    if (!account.isCanPay()){
                        TextView errorMsg = findViewById(R.id.pa_errorMsg);
                        errorMsg.setText("You cannot pay from this account");
                        return;
                    }

                    try {
                        double doubleAmount  = Double.parseDouble(amount.getText().toString());
                        if (account.canAfford(doubleAmount)){
                            account.withDraw(doubleAmount, PayActivity.this);
                            Transaction transaction = new Transaction(accountID, recipient.getText().toString(), doubleAmount, reference.getText().toString(), message.getText().toString(), chosenDate);
                            JsonFileUtility.saveFile(transaction.makeJSONObject(), "history/" + accountID, transaction.getTime(), PayActivity.this);

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

    /**
     * Opens calendar view
     */
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
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            chosenDate = calendar.getTime();
        }
}
