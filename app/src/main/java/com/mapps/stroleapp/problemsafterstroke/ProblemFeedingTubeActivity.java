package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mapps.stroleapp.R;

public class ProblemFeedingTubeActivity extends AppCompatActivity {

    private Button pickTimeFtlc, submitFeedingTubeInfo, feedingTubeBack;
    private EditText feedingTubeDurationInDays;
    private TextView ftlcTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_feeding_tube);

        pickTimeFtlc = findViewById(R.id.pick_time_ftlc);
        submitFeedingTubeInfo = findViewById(R.id.submit);
        feedingTubeBack = findViewById(R.id.feeding_tube_back);
        feedingTubeDurationInDays = findViewById(R.id.feeding_tube_duration_in_days);
        feedingTubeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemFeedingTubeActivity.this, ProblemsAfterStrokeActivity.class));
            }
        }
        );
    }
}
