package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapps.stroleapp.R;

public class ProblemFeverActivity extends AppCompatActivity {

    private RadioButton yes;
    private EditText fever_medicine_name;
    private EditText fever_duration_in_days;
    private EditText patient_temperature;
    private RadioGroup radioGroup ;
    private String userEmail ;
    private Button back;
    private Button submit ;
    private DatabaseReference databaseFever ;
    private String medicineName = null ;
    private boolean showMedicine = false ;
    private String isMedicineChecked = null ;
    private RadioButton radioButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_fever);
        fever_medicine_name = findViewById(R.id.fever_medicine_name);
        fever_duration_in_days = findViewById(R.id.fever_duration_in_days);
        patient_temperature = findViewById(R.id.patient_temperature);
        yes = findViewById(R.id.fmg_yes);
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);
        databaseFever = FirebaseDatabase.getInstance().getReference("fever");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();

        radioGroup =  findViewById(R.id.fever_medicine);
        showMedicine = false ;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
                isMedicineChecked = radioButton.getText().toString().trim() ;
                switch (checkedId) {
                    case R.id.fmg_yes:
                        fever_medicine_name.setVisibility(View.VISIBLE);
                        showMedicine = true ;
                        break;
                    case R.id.fmg_not:
                        fever_medicine_name.setVisibility(View.GONE);
                        break;
                }
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemFeverActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(fever_duration_in_days.getText().toString().trim()) && !TextUtils.isEmpty(patient_temperature.getText().toString().trim()) && isMedicineChecked != null )

                    if (showMedicine && !TextUtils.isEmpty(fever_medicine_name.getText().toString().trim()) ) {
                        submitInfo();
                    }
                    else if (!showMedicine) {
                        submitInfo() ;
                    }
                    else {
                        Toast.makeText(getApplicationContext() , "Please fill the details completely" , Toast.LENGTH_LONG).show();
                    }

                else {
                    Toast.makeText(getApplicationContext() , "Please fill the details completely" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void submitInfo(){
        int feverDays = Integer.parseInt(fever_duration_in_days.getText().toString().trim());
        double temperature = Double.parseDouble(patient_temperature.getText().toString().trim());
        String medicine = null ;
        if (showMedicine) {
            medicine = fever_medicine_name.getText().toString().trim() ;
        }
        String id = databaseFever.push().getKey();
        ProblemFeverModel entry = new ProblemFeverModel(id,userEmail,feverDays,temperature,medicine) ;
        databaseFever.child(id).setValue(entry);

        Toast.makeText(this , "Limb Spasticity entry added" , Toast.LENGTH_LONG).show();



    }
}


