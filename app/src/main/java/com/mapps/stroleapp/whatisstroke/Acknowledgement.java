package com.mapps.stroleapp.whatisstroke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.mapps.stroleapp.R;

public class Acknowledgement extends AppCompatActivity {

    TextView t2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgement);

        t2 = findViewById(R.id.references);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
