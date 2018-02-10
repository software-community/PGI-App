package com.mapps.stroleapp.careathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.R;

public class ActiveRangeExercisesActivity extends AppCompatActivity {

    private Button selfAssistedRangeUpperLimbs, activeRangeUpperLimbs, exerciseTrunkOrHips, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_range_exercises);

        selfAssistedRangeUpperLimbs = findViewById(R.id.self_assisted_range_upper_limbs);
        activeRangeUpperLimbs = findViewById(R.id.active_range_upper_limbs);
        exerciseTrunkOrHips = findViewById(R.id.exercise_trunk_or_hips);
        back = findViewById(R.id.back);

        selfAssistedRangeUpperLimbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActiveRangeExercisesActivity.this, SelfAssistedRangeUpperLimbsVideoActivity.class));
            }
        });

        activeRangeUpperLimbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActiveRangeExercisesActivity.this, ActiveRangeUpperLimbsVideoActivity.class));
            }
        });

        exerciseTrunkOrHips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActiveRangeExercisesActivity.this, ExerciseTrunkOrHipsVideoActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
