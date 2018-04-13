package com.mapps.stroleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapps.stroleapp.problemsafterstroke.ProblemBadMouthOdorModel;
import com.mapps.stroleapp.whatisstroke.WhatIsStrokeVideoActivity;

public class ProfileActivity extends AppCompatActivity {
    EditText age,contact,date,address,occupation,education,name,role_with_patient;
    Button back,submit,reset;
    TextView textView_insurance;
    int title=-1;
    int sex=-1;
    int insurance=-1;
    int debugging=0;//set this field to 0 in the final app
    String previous;
    String role;
    private DatabaseReference databaseProfile ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        int ret = settings.getInt("done", 0);
        if(ret==1)
            startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
        role = settings.getString("role", "patient");
        if(role.equals("patient"))
            setContentView(R.layout.activity_profile);
        else
        {
            setContentView(R.layout.activity_profile_care);
            role_with_patient=findViewById(R.id.profile_relation);

        }
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
        databaseProfile = FirebaseDatabase.getInstance().getReference("Profile");



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, LanguageActivity.class));
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
                if(((insurance==-1 && role.equals("patient")) || sex==-1 || title==-1 ||
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
                    submitInfo();
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
                if (checked && role.equals("care")){
                    previous="yes";
                }
                else if(checked) {
                    insurance = 1;
                }
                    break;
            case R.id.profile_insurance_no:
                if (checked && role.equals("care")){
                    previous="no";
                }
                else if(checked) {
                    insurance = 2;
                }
                    break;
        }
    }

    public void submitInfo(){
        String gender1="";
        if(sex==1)
            gender1="Male";
        else
            gender1="Female";
        String contact1;
        contact1=contact.getText().toString().trim();
        String date1=date.getText().toString().trim();
        String address1=address.getText().toString().trim();
        String occupation1=occupation.getText().toString().trim();
        String education1=education.getText().toString().trim();
        String name1=name.getText().toString().trim();
        String relation="";
        if(role.equals("care"))
        relation=role_with_patient.getText().toString();
        String insurance1="";
        if(insurance==1)
            insurance1="yes";
        else
            insurance1="no";
        String id = databaseProfile.push().getKey();
        ProfileModelClass entry;
        if(role.equals("care"))
            entry = new ProfileModelClass(gender1,contact1,date1,address1,occupation1,education1,name1,insurance1,relation,role);
        else
            entry = new ProfileModelClass(gender1,contact1,date1,address1,occupation1,education1,name1,insurance1,relation,role,previous);
        databaseProfile.child(id).setValue(entry);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("done", 1);
        editor.commit();

    }
}
