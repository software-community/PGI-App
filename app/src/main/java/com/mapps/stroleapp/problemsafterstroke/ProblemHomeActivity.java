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
    private EditText ohp_one , ohp_two , ohp_three ,ohp_one_duration ,ohp_two_duration ,ohp_three_duration ;
    private Button back , submit ;
    private DatabaseReference databaseOtherProblems ;
    private String problem1 , problem2 , problem3 ;
    private int duration1 , duration2 , duration3 ;
    private boolean fillDetails1 = true ;
    private boolean fillDetails2 = true ;
    private boolean fillDetails3 = true ;
    private String userEmail ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_other);

        ohp_one = findViewById(R.id.ohp_one);
        ohp_two = findViewById(R.id.ohp_two);
        ohp_three = findViewById(R.id.ohp_three);
        ohp_one_duration = findViewById(R.id.ohp_one_duration);
        ohp_two_duration = findViewById(R.id.ohp_two_duration);
        ohp_three_duration = findViewById(R.id.ohp_three_duration);
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
                if ((TextUtils.isEmpty(ohp_one.getText().toString().trim()) && TextUtils.isEmpty(ohp_one_duration.getText().toString().trim()))){
                    fillDetails1 = true;
                }
                else if (!TextUtils.isEmpty(ohp_one.getText().toString().trim()) && !TextUtils.isEmpty(ohp_one_duration.getText().toString().trim())){
                    problem1 = ohp_one.getText().toString().trim() ;
                    duration1 = Integer.parseInt(ohp_one_duration.getText().toString().trim());
                    fillDetails1 = true ;
                    }

                else{
                       fillDetails1 = false;
                    }




                if ((TextUtils.isEmpty(ohp_two.getText().toString().trim()) && TextUtils.isEmpty(ohp_two_duration.getText().toString().trim()))){
                    fillDetails2 = true;
                }
                else if (!TextUtils.isEmpty(ohp_two.getText().toString().trim()) && !TextUtils.isEmpty(ohp_two_duration.getText().toString().trim())){
                    problem2 = ohp_two.getText().toString().trim() ;
                    duration2 = Integer.parseInt(ohp_two_duration.getText().toString().trim());
                    fillDetails2 = true ;
                }

                else{
                    fillDetails2 = false;
                }



                if ((TextUtils.isEmpty(ohp_three.getText().toString().trim()) && TextUtils.isEmpty(ohp_three_duration.getText().toString().trim()))){
                    fillDetails3 = true;
                }
                else if (!TextUtils.isEmpty(ohp_three.getText().toString().trim()) && !TextUtils.isEmpty(ohp_three_duration.getText().toString().trim())){
                    problem3 = ohp_three.getText().toString().trim() ;
                    duration3 = Integer.parseInt(ohp_three_duration.getText().toString().trim());
                    fillDetails3 = true ;
                }

                else{
                    fillDetails3 = false;
                }

                if (!fillDetails1 || !fillDetails2 || !fillDetails3){
                    Toast.makeText(getApplicationContext() ,R.string.specify_full_detail , Toast.LENGTH_LONG).show();
                   }
                else {
                    submitInfo();
                    startActivity(new Intent(ProblemHomeActivity.this, ProblemsAfterStrokeActivity.class));
                }
            }
        });

    }

    public void submitInfo(){

        
        String id = databaseOtherProblems.push().getKey();
        ProblemOtherProblemsModel entry = new ProblemOtherProblemsModel(id,userEmail,duration1 , duration2 , duration3,problem1,problem2,problem3) ;
        databaseOtherProblems.child(id).setValue(entry);

        Toast.makeText(this , "Problems added" , Toast.LENGTH_LONG).show();



    }
}
