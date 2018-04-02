package com.mapps.stroleapp.problemsafterstroke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapps.stroleapp.R;
import com.mapps.stroleapp.careathome.BedsoreWhatAndPreventionHomeActivity;
import com.mapps.stroleapp.careathome.TakingCareHomeActivity;

public class ProblemShoulderPainActivity extends AppCompatActivity {
    private Button back;
    private Button submit ;
    private EditText days_shoulder_pain ;
    private String userEmail ;

    private DatabaseReference databaseShoulderPain ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_shoulder_pain);

        back = findViewById(R.id.back);
        days_shoulder_pain = findViewById(R.id.shoulder_pain_duration_in_days);
        submit = findViewById(R.id.submit);
        databaseShoulderPain = FirebaseDatabase.getInstance().getReference("shoulderpain");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemShoulderPainActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(days_shoulder_pain.getText().toString().trim())){
                    submitInfo();
                    startActivity(new Intent(ProblemShoulderPainActivity.this, ProblemsAfterStrokeActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext() , R.string.fill_shoulder_pain , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void submitInfo(){
        int mouthOdorDays = Integer.parseInt(days_shoulder_pain.getText().toString().trim());

        if (!TextUtils.isEmpty(days_shoulder_pain.getText().toString().trim())){
            String id = databaseShoulderPain.push().getKey();
            ProblemShoulderPainModel entry = new ProblemShoulderPainModel(id,userEmail,mouthOdorDays) ;
            databaseShoulderPain.child(id).setValue(entry);

            Toast.makeText(this , "Shoulder Pain entry added" , Toast.LENGTH_LONG).show();
        }
        else {
            if (TextUtils.isEmpty(days_shoulder_pain.getText().toString().trim())){
                Toast.makeText(this , R.string.fill_shoulder_pain , Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this , "Unexpected Error happen" , Toast.LENGTH_LONG).show();
            }

        }
    }

}
