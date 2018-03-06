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

public class ProblemBadMouthOdorActivity extends AppCompatActivity {
    private Button back;
    private Button submit ;
    private EditText days_mouth_odor ;
    private String userEmail ;

    private DatabaseReference databaseBadMouthOdor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_bad_mouth_odor);

        back = findViewById(R.id.back);
        days_mouth_odor = findViewById(R.id.mouth_odor_duration_in_days);
        submit = findViewById(R.id.submit);
        databaseBadMouthOdor = FirebaseDatabase.getInstance().getReference("badmouthodor");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();
        
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemBadMouthOdorActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(days_mouth_odor.getText().toString().trim())){
                    submitInfo();
                    startActivity(new Intent(ProblemBadMouthOdorActivity.this, ProblemsAfterStrokeActivity.class));}
                else {
                    Toast.makeText(getApplicationContext() , R.string.fill_mouth_odour_day, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void submitInfo(){
        int mouthOdorDays = Integer.parseInt(days_mouth_odor.getText().toString().trim());

        if (!TextUtils.isEmpty(days_mouth_odor.getText().toString().trim())){
            String id = databaseBadMouthOdor.push().getKey();
            ProblemBadMouthOdorModel entry = new ProblemBadMouthOdorModel(id,userEmail,mouthOdorDays) ;
            databaseBadMouthOdor.child(id).setValue(entry);

            Toast.makeText(this , "Mouth Odor added" , Toast.LENGTH_LONG).show();
        }
        else {
            if (TextUtils.isEmpty(days_mouth_odor.getText().toString().trim())){
                Toast.makeText(this , R.string.fill_mouth_odour_day , Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this , "Unexpected Error happen" , Toast.LENGTH_LONG).show();
            }

        }
    }

}
