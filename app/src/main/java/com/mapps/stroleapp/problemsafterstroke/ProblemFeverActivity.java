package com.mapps.stroleapp.problemsafterstroke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mapps.stroleapp.R;

public class ProblemFeverActivity extends AppCompatActivity {

    private RadioButton yes;
    private EditText medicineName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_fever);
        medicineName= findViewById(R.id.fever_medicine_name);
        yes= findViewById(R.id.fmg_yes);

        RadioGroup rb = (RadioGroup) findViewById(R.id.fever_medicine);
        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fmg_yes:
                        medicineName.setVisibility(View.VISIBLE);
                        break;
                    case R.id.fmg_not:
                        medicineName.setVisibility(View.GONE);
                        break;
                }
            }

        });
    }

    /*public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.fmg_yes:
                if (checked)
                    medicineName.setVisibility(View.VISIBLE);
                break;
            case R.id.fmg_not:
                if (checked)
                    medicineName.setVisibility(View.GONE);
                    break;
        }
    }*/

}
