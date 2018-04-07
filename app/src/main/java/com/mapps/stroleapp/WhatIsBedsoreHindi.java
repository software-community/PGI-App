package com.mapps.stroleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapps.stroleapp.R;


public class WhatIsBedsoreHindi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_bedsore_hindi);

        TextView txt1 = findViewById(R.id.Bedsore_txt1);
        TextView txt2 = findViewById(R.id.Bedsore_txt2);
        TextView txt3 = findViewById(R.id.Bedsore_txt3);
        TextView txt4 = findViewById(R.id.Bedsore_txt4);

        ImageView img1 = findViewById(R.id.Bedsore_img1);
        ImageView img2 = findViewById(R.id.Bedsore_img2);

        txt1.setText(R.string.hinditext1);

       img1.setImageResource(R.drawable.bedsore_1);

        txt2.setText(R.string.hinditext2);
        txt3.setText(R.string.hinditext3);

        img2.setImageResource(R.drawable.bedsore_2);

        txt4.setText(R.string.hinditext4);

    }
}
