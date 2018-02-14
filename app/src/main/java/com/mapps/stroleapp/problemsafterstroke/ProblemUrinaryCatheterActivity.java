package com.mapps.stroleapp.problemsafterstroke;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapps.stroleapp.R;

public class ProblemUrinaryCatheterActivity extends AppCompatActivity {


    private Button submit , back , pick_date;
    private EditText urinary_catheter_duration_in_days ;
    private DatabaseReference databaseUrinary ;
    private String userEmail ;




    private int mYear, mMonth, mDay;
    private int[] date;
    private boolean d;
    private Button temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_urinary_catheter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);
        pick_date = findViewById(R.id.pick_date);
        urinary_catheter_duration_in_days = findViewById(R.id.urinary_catheter_duration_in_days);
        databaseUrinary = FirebaseDatabase.getInstance().getReference("urinary");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemUrinaryCatheterActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(urinary_catheter_duration_in_days.getText().toString().trim()) && !TextUtils.isEmpty(pick_date.getText().toString().trim()) ){
                    submitInfo();
                    startActivity(new Intent(ProblemUrinaryCatheterActivity.this, ProblemsAfterStrokeActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext() , "Please fill the details" , Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    public void submitInfo(){
        int duration = Integer.parseInt(urinary_catheter_duration_in_days.getText().toString().trim());
        String date = pick_date.getText().toString().trim() ;

        String id = databaseUrinary.push().getKey();
        ProblemUrinaryCatheterModel entry = new ProblemUrinaryCatheterModel(id,userEmail,duration,date) ;
        databaseUrinary.child(id).setValue(entry);

        Toast.makeText(this , "Urinary Catheter entry added" , Toast.LENGTH_LONG).show();



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View v) {
        temp = (Button) v;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.TimePickerTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        temp.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        date = new int[]{dayOfMonth, monthOfYear + 1, year};
                        d = true;


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}