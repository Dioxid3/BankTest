package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userNumber;
    private EditText userPasscode;
    private TextView Info;
    private Button Login;
    private int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNumber = (EditText)findViewById(R.id.etUserNumber);
        userPasscode = (EditText)findViewById(R.id.etUserPasscode);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        Info.setText("No of attempts remaining: " + String.valueOf(counter));

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(userNumber.getText().toString(), userPasscode.getText().toString());
            }
        });


    }

    private void validate(String userNumber, String userPasscode){
        if((userNumber.equals("1234")) && (userPasscode.equals("0000"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else {
            counter--;
            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if (counter <= 0){
                Login.setEnabled(false);
            }
        }
    }
}
