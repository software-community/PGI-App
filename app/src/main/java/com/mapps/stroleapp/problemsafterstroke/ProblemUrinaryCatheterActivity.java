package com.mapps.stroleapp.problemsafterstroke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mapps.stroleapp.R;

public class ProblemUrinaryCatheterActivity extends AppCompatActivity {

    private Button pickTimeFtlc, submitFeedingTubeInfo, feedingTubeBack;
    private EditText feedingTubeDurationInDays;
    private TextView ftlcTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_urinary_catheter);
    }
}
