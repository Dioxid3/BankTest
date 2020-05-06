package com.example.banktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileDataEditorActivity extends AppCompatActivity {

    private EditText name;
    private EditText address;
    private EditText phoneNumber;
    private Button saveBtn;


    private static ProfileData profileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_editor);

        if (profileData == null) {

            JSONObject obj =  JsonFileUtility.loadFile("profile","accountData", ProfileDataEditorActivity.this);

            if (obj == null){ // If there is no file, uses default info
                profileData = new ProfileData();
            } else {
                try {
                    profileData = new ProfileData(obj.getString("name"), obj.getString("address"), obj.getString("phoneNumber"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        name = (EditText)findViewById(R.id.nameEditText);
        address = (EditText)findViewById(R.id.addressEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        saveBtn = (Button)findViewById(R.id.ae_saveBtn);

        name.setText(profileData.getName());
        address.setText(profileData.getAddress());
        phoneNumber.setText(profileData.getPhoneNumber());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileData.setName(name.getText().toString());
                profileData.setAddress(address.getText().toString());
                profileData.setPhoneNumber(phoneNumber.getText().toString());

                JSONObject obj = profileData.makeJSONObject();
                JsonFileUtility.saveFile(obj,"profile", "accountData", ProfileDataEditorActivity.this);

                Intent intent = new Intent(ProfileDataEditorActivity.this, SecondActivity.class);
                startActivity(intent);


            }
        });

    }
}
