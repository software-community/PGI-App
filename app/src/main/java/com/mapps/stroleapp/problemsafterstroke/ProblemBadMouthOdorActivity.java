package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.R;
import com.mapps.stroleapp.careathome.BedsoreWhatAndPreventionHomeActivity;
import com.mapps.stroleapp.careathome.TakingCareHomeActivity;

public class ProblemBadMouthOdorActivity extends AppCompatActivity {
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_bad_mouth_odor);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemBadMouthOdorActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
    }

}
