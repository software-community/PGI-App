package com.mapps.stroleapp.careathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.R;

public class LimbExercisesActivity extends AppCompatActivity {

    private Button passiveRangeExercises, activeRangeExercises, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_limb_exercises);

        passiveRangeExercises = findViewById(R.id.passive_range_exercises);
        activeRangeExercises = findViewById(R.id.active_range_exercises);
        back = findViewById(R.id.back);

        passiveRangeExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LimbExercisesActivity.this, PassiveRangeExercisesActivity.class));
            }
        });

        activeRangeExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LimbExercisesActivity.this, ActiveRangeExercisesActivity.class));
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
