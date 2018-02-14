package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapps.stroleapp.R;

public class ProblemUrinaryCatheterActivity extends AppCompatActivity {

    private Button submit , back;
    private EditText urinary_catheter_duration_in_days , pick_date;
    private DatabaseReference databaseUrinary ;
    private String userEmail ;

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
}
