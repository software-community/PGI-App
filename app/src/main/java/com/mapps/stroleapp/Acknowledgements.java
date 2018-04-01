package com.mapps.stroleapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.mapps.stroleapp.R;


public class Acknowledgements extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgements);

        TextView tv = findViewById(R.id.references);
        tv.setText(R.string.acknowledgements);
    }
}
