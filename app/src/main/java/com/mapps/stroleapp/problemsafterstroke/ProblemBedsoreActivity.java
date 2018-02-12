package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mapps.stroleapp.R;
import com.mapps.stroleapp.registration.LoginActivity;

public class ProblemBedsoreActivity extends AppCompatActivity {

    private Button pickImageBedsorePatient, submitBedsoreInfo, bedsoreBack;
    private EditText bedsoreDurationInDays;
    private ImageView imageBedsorePatient;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_bedsore);

        pickImageBedsorePatient = findViewById(R.id.pick_image_bedsore_patient);
        submitBedsoreInfo = findViewById(R.id.submit);
        bedsoreBack = findViewById(R.id.bedsore_back);
        bedsoreDurationInDays = findViewById(R.id.bedsore_duration_in_days);
        bedsoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemBedsoreActivity.this, ProblemsAfterStrokeActivity.class));
            }
        }
        );

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        System.out.print(user.getEmail());
        Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();



    }
}
