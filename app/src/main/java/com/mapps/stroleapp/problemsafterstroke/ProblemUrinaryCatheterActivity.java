package com.mapps.stroleapp.problemsafterstroke;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.mapps.stroleapp.R;

public class ProblemUrinaryCatheterActivity extends AppCompatActivity {

    private Button pickTimeFtlc, submitFeedingTubeInfo, feedingTubeBack;
    private EditText feedingTubeDurationInDays;
    private TextView ftlcTime;
    private int mYear, mMonth, mDay;
    private int[] date;
    private boolean d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_urinary_catheter);
    }

    public void onClick(View v) {
        Button temp = (Button) v;
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        temp.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        date = new int[]{dayOfMonth, monthOfYear + 1, year};
                        d = true;


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}