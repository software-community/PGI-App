package com.mapps.stroleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mapps.stroleapp.careathome.TakingCareHomeActivity;
import com.mapps.stroleapp.feedback.FeedbackActivity;
import com.mapps.stroleapp.problemsafterstroke.ProblemsAfterStrokeActivity;
import com.mapps.stroleapp.registration.LoginActivity;
import com.mapps.stroleapp.schedule.FollowUpScheduleActivity;
import com.mapps.stroleapp.whatisstroke.WhatIsStrokeVideoActivity;

public class HomeActivity extends AppCompatActivity {

    Button whatIsStroke, problemsAfterStroke, takingCareHome, feedback, followUpSchedule, signOut,acknowledgement;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_home);

        signOut = (Button) findViewById(R.id.sign_out);


        whatIsStroke = findViewById(R.id.what_is_stroke);
        problemsAfterStroke = findViewById(R.id.problems_after_stroke);
        takingCareHome = findViewById(R.id.taking_care_home);
        feedback = findViewById(R.id.feedback);
        followUpSchedule = findViewById(R.id.follow_up_schedule);
        whatIsStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, WhatIsStrokeVideoActivity.class));
            }
        });

        problemsAfterStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });

        takingCareHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, TakingCareHomeActivity.class));
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FeedbackActivity.class));
            }
        });

        followUpSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FollowUpScheduleActivity.class));
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

    }

    public void signOut() {
        auth.signOut();
    }

}
