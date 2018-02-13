package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapps.stroleapp.R;

public class ProblemHomeActivity extends AppCompatActivity {
    private EditText other_problems_no_of_days , ohp_one , ohp_two , ohp_three ;
    private Button back , submit ;
    private DatabaseReference databaseOtherProblems ;
    private String problem1 , problem2 , problem3 ;
    private boolean fillDetails = false ;
    private String userEmail ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_other);
        other_problems_no_of_days = findViewById(R.id.other_problems_no_of_days);
        ohp_one = findViewById(R.id.ohp_one);
        ohp_two = findViewById(R.id.ohp_two);
        ohp_three = findViewById(R.id.ohp_three);
        databaseOtherProblems = FirebaseDatabase.getInstance().getReference("otherproblems");
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemHomeActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(ohp_one.getText().toString().trim()) && !TextUtils.isEmpty(other_problems_no_of_days.getText().toString().trim()) ){
                    problem1 = ohp_one.getText().toString().trim() ;
                    fillDetails = true ;
                }
                if (!TextUtils.isEmpty(ohp_two.getText().toString().trim())&& !TextUtils.isEmpty(other_problems_no_of_days.getText().toString().trim()) ){
                    problem2 = ohp_two.getText().toString().trim() ;
                    fillDetails = true ;
                }
                if (!TextUtils.isEmpty(ohp_three.getText().toString().trim())&& !TextUtils.isEmpty(other_problems_no_of_days.getText().toString().trim()) ){
                    problem3 = ohp_three.getText().toString().trim() ;
                    fillDetails = true ;
                }

                if (fillDetails){
                    submitInfo();
                }
                else {
                    Toast.makeText(getApplicationContext() , "Please fill the details completely" , Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void submitInfo(){
        int duration = Integer.parseInt(other_problems_no_of_days.getText().toString().trim());
      
        
        String id = databaseOtherProblems.push().getKey();
        ProblemOtherProblemsModel entry = new ProblemOtherProblemsModel(id,userEmail,duration,problem1,problem2,problem3) ;
        databaseOtherProblems.child(id).setValue(entry);

        Toast.makeText(this , "Limb Spasticity entry added" , Toast.LENGTH_LONG).show();



    }
}
