package com.mapps.stroleapp.careathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.HomeActivity;
import com.mapps.stroleapp.R;

public class TakingCareHomeActivity extends AppCompatActivity {

    private Button whatAndPrevention, howToFeed, mouthCare, urinaryCatheterCare,
            howToLimbExercises, psychologicalSupport, dosAndDonts, takingCareBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taking_care_home);

        howToFeed = findViewById(R.id.how_to_feed);
        mouthCare = findViewById(R.id.mouth_care);
        urinaryCatheterCare = findViewById(R.id.urinary_catheter_care);
        howToLimbExercises = findViewById(R.id.how_to_limb_exercises);
        psychologicalSupport = findViewById(R.id.psychological_support);
        dosAndDonts = findViewById(R.id.dos_and_donts);
        takingCareBack = findViewById(R.id.taking_care_back);

        howToFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakingCareHomeActivity.this, HowToFeedVideoActivity.class));
            }
        });
        mouthCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakingCareHomeActivity.this, MouthCareVideoActivity.class));
            }
        });
        urinaryCatheterCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakingCareHomeActivity.this, UrinaryCareVideoActivity.class));
            }
        });
        howToLimbExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakingCareHomeActivity.this, LimbExercisesActivity.class));
            }
        });
        psychologicalSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakingCareHomeActivity.this, PsychologicalSupportVideoActivity.class));
            }
        });
        dosAndDonts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakingCareHomeActivity.this, DosAndDontsActivity.class));
            }
        });
        takingCareBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
