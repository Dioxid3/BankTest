package com.example.banktest;

import android.app.DatePickerDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class PayActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

        private TextView dateText;
        private DatePickerDialog.OnDateSetListener mDateSetListener;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pay);
            dateText = findViewById(R.id.tvShowDate);

            findViewById(R.id.btnChooseDate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                showDatePickerDialog();
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
                String date = "Date of payment: " + dayOfMonth + "/" + month + "/" + year;
                dateText.setText(date);
    }
}
