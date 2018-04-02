package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapps.stroleapp.R;

public class ProblemLinkWeeknessActivity extends AppCompatActivity {

    private Button back;
    private Button submit ;
    private EditText limbs_weekness_duration_in_days ;
    private String userEmail ;
    private RadioGroup radioGroup ;
    private int selectedRadioGroup = 0 ;
    private RadioButton radioButton ;
    private String weekness = null ;
    private DatabaseReference databaseLimbWeekness ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_limb_weekness);

        limbs_weekness_duration_in_days = findViewById(R.id.limbs_weekness_duration_in_days);
        radioGroup = findViewById(R.id.limb_weekness_side);
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);

        databaseLimbWeekness = FirebaseDatabase.getInstance().getReference("limbWeekness");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                radioButton = findViewById(checkedId);
                weekness = radioButton.getText().toString().trim() ;

            }
        });




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemLinkWeeknessActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(limbs_weekness_duration_in_days.getText().toString().trim()) && weekness != null){
                    submitInfo();
                    startActivity(new Intent(ProblemLinkWeeknessActivity.this, ProblemsAfterStrokeActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext() , R.string.fill_details , Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void submitInfo(){
        int limbWeeknessDays = Integer.parseInt(limbs_weekness_duration_in_days.getText().toString().trim());

        if (!TextUtils.isEmpty(limbs_weekness_duration_in_days.getText().toString().trim())){
            String id = databaseLimbWeekness.push().getKey();
            ProblemLinkWeeknessModel entry = new ProblemLinkWeeknessModel(id,userEmail,weekness,limbWeeknessDays) ;
            databaseLimbWeekness.child(id).setValue(entry);

            Toast.makeText(this , "entry added" , Toast.LENGTH_LONG).show();
        }
        else {
            if (TextUtils.isEmpty(limbs_weekness_duration_in_days.getText().toString().trim())){
                Toast.makeText(this , R.string.fill_details , Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this , "Unexpected Error happen" , Toast.LENGTH_LONG).show();
            }

        }
    }
}
