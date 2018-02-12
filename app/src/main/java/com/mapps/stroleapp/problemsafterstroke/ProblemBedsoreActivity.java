package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mapps.stroleapp.R;

public class ProblemBedsoreActivity extends AppCompatActivity {

    private Button pickImageBedsorePatient, submitBedsoreInfo, bedsoreBack;
    private EditText bedsoreDurationInDays;
    private ImageView imageBedsorePatient;

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



    }
}
