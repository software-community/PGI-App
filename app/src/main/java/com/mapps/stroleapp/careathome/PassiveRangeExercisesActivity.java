package com.mapps.stroleapp.careathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.R;

public class PassiveRangeExercisesActivity extends AppCompatActivity {

    private Button passiveRangeUpperLimbs, passiveRangeLowerLimbs, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passive_range_exercises);

        passiveRangeUpperLimbs = findViewById(R.id.passive_range_upper_limbs);
        passiveRangeLowerLimbs = findViewById(R.id.passive_range_lower_limbs);
        back = findViewById(R.id.back);

        passiveRangeUpperLimbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassiveRangeExercisesActivity.this, PassiveRangeUpperLimbsVideoActivity.class));
            }
        });

        passiveRangeLowerLimbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassiveRangeExercisesActivity.this, PassiveRangeLowerLimbsVideoActivity.class));
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
