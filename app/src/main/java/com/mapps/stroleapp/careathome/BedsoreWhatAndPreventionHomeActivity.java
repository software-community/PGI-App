package com.mapps.stroleapp.careathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.R;
import com.mapps.stroleapp.WhatIsBedsoreHindi;

public class BedsoreWhatAndPreventionHomeActivity extends AppCompatActivity {

    private Button backCare, degreeObliquePosition, bedsoreDressing,
            sterileArticlesBedsoreDressing, back,bedsore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedsore_what_and_prevention);

        backCare = findViewById(R.id.back_care);
        degreeObliquePosition = findViewById(R.id.degree_oblique_position);
        bedsoreDressing = findViewById(R.id.bedsore_dressing);
        sterileArticlesBedsoreDressing = findViewById(R.id.sterile_articles_bedsore_dressing);
        back = findViewById(R.id.back);
        bedsore= findViewById(R.id.what_is_bedsore);



        backCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BedsoreWhatAndPreventionHomeActivity.this, BackCareVideoActivity.class));
            }
        });

        degreeObliquePosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BedsoreWhatAndPreventionHomeActivity.this, DegreeObliquePositionVideoActivity.class));
            }
        });

        bedsoreDressing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BedsoreWhatAndPreventionHomeActivity.this, BedsoreDressingVideoActivity.class));
            }
        });

        sterileArticlesBedsoreDressing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BedsoreWhatAndPreventionHomeActivity.this, SterileArticlesBedsoreDressingVideoActivity.class));
            }
        });
        bedsore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BedsoreWhatAndPreventionHomeActivity.this, WhatIsBedsoreHindi.class));
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
