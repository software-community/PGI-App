package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.HomeActivity;
import com.mapps.stroleapp.R;

public class ProblemsAfterStrokeActivity extends AppCompatActivity {

    private Button problemBedsore, problemFeedingTube, problemUrinaryCatheter, problemBadMouthOdor,
            problemShoulderPain, problemLinkWeekness, problemLimbSpasticity, problemFever, problemOther, problemBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems_after_stroke);

        problemBedsore = findViewById(R.id.problem_bedsore);
        problemFeedingTube = findViewById(R.id.problem_feeding_tube);
        problemUrinaryCatheter = findViewById(R.id.problem_urinary_catheter);
        problemBadMouthOdor = findViewById(R.id.problem_bad_mouth_odor);
        problemShoulderPain = findViewById(R.id.problem_shoulder_pain);
        problemLinkWeekness = findViewById(R.id.problem_link_weekness);
        problemLimbSpasticity = findViewById(R.id.problem_limb_spasticity);
        problemFever = findViewById(R.id.problem_fever);
        problemOther = findViewById(R.id.problem_other);
        problemBack = findViewById(R.id.problem_back);

        problemBedsore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemBedsoreActivity.class));
            }
        });

        problemFeedingTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemFeedingTubeActivity.class));
            }
        });

        problemUrinaryCatheter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemUrinaryCatheterActivity.class));
            }
        });

        problemBadMouthOdor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemBadMouthOdorActivity.class));
            }
        });

        problemShoulderPain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemShoulderPainActivity.class));
            }
        });

        problemLinkWeekness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemLinkWeeknessActivity.class));
            }
        });

        problemLimbSpasticity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemLimbSpasticityActivity.class));
            }
        });

        problemFever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemFeverActivity.class));
            }
        });

        problemOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, ProblemHomeActivity.class));
            }
        });

        problemBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemsAfterStrokeActivity.this, HomeActivity.class));
            }
        });


    }
}
