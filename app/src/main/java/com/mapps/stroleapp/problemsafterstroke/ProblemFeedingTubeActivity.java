package com.mapps.stroleapp.problemsafterstroke;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class ProblemFeedingTubeActivity extends AppCompatActivity {


    private Button  submit, back,pick_date;
    private DatabaseReference databaseFeedingTube ;
    private String userEmail ;
    private EditText feedingTubeDurationInDays;
    private int mYear, mMonth, mDay;
    private int[] date;
    private boolean d;
    private Button temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_feeding_tube);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);
        pick_date = findViewById(R.id.pick_date);
        feedingTubeDurationInDays = findViewById(R.id.feeding_tube_duration_in_days);
        databaseFeedingTube = FirebaseDatabase.getInstance().getReference("feedingtube");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemFeedingTubeActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(feedingTubeDurationInDays.getText().toString().trim()) && !TextUtils.isEmpty(pick_date.getText().toString().trim()) ){
                    submitInfo();
                    startActivity(new Intent(ProblemFeedingTubeActivity.this, ProblemsAfterStrokeActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext() , R.string.fill_details, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void submitInfo(){
        int duration = Integer.parseInt(feedingTubeDurationInDays.getText().toString().trim());
        String date = pick_date.getText().toString().trim() ;

        String id = databaseFeedingTube.push().getKey();
        ProblemFeedingTubeModel entry = new ProblemFeedingTubeModel(id,userEmail,duration,date) ;
        databaseFeedingTube.child(id).setValue(entry);

        Toast.makeText(this , "Feeding Tube entry added" , Toast.LENGTH_LONG).show();



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
