package com.mapps.stroleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mapps.stroleapp.whatisstroke.WhatIsStrokeVideoActivity;

public class ProfileActivity extends AppCompatActivity {
    EditText age,contact,date,address,occupation,education,name;
    Button back,submit,reset;
    TextView textView_insurance;
    int title=-1;
    int sex=-1;
    int insurance=-1;
    int debugging=1;        //set this field to 0 in the final app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textView_insurance=findViewById(R.id.profile_textview_insurance);
        name=findViewById(R.id.profile_name);
        age=findViewById(R.id.profile_age);
        contact=findViewById(R.id.profile_contact);
        date=findViewById(R.id.profile_date);
        address=findViewById(R.id.profile_address);
        occupation=findViewById(R.id.profile_occupation);
        education=findViewById(R.id.profile_education);
        back=findViewById(R.id.profile_back);
        submit=findViewById(R.id.profile_submit);
        reset=findViewById(R.id.profile_reset);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, WhatIsStrokeVideoActivity.class));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=getIntent();
                finish();
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Add the red flags in this if
                if((insurance==-1 || sex==-1 || title==-1 ||
                        name.getText().toString().isEmpty() ||
                        contact.getText().toString().isEmpty() ||
                        age.getText().toString().isEmpty() ||
                        date.getText().toString().isEmpty() ||
                        address.getText().toString().isEmpty() ||
                        occupation.getText().toString().isEmpty() ||
                        education.getText().toString().isEmpty()) && debugging!=1
                        )
                {
                    Toast.makeText(getBaseContext(),"Fill all the entries",Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                }
            }
        });
    }
    public void onRadioButtonTitleClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.profile_mr:
                if (checked)
                    title=1;
                    break;
            case R.id.profile_mrs:
                if (checked)
                    title=2;
                    break;
            case R.id.profile_ms:
                if (checked)
                    title=3;
                    break;
            case R.id.profile_dr:
                if (checked)
                    title=4;
                    break;

        }
    }
    public void onRadioButtonSexClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radiosexbuttonmale:
                if (checked)
                    sex=1;
                    break;
            case R.id.radiosexbuttonfemale:
                if (checked)
                    sex=2;
                    break;
        }
    }
    public void onRadioButtonHealthClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.profile_insurance_yes:
                if (checked)
                    insurance=1;
                    break;
            case R.id.profile_insurance_no:
                if (checked)
                    insurance=2;
                    break;
        }
    }

}
