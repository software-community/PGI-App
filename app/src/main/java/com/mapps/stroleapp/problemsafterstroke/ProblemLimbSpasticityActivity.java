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

public class ProblemLimbSpasticityActivity extends AppCompatActivity {

    private Button back;
    private Button submit ;
    private EditText limb_spasticity_duration_in_days ;
    private String userEmail ;
    private RadioGroup radioGroup ;
    private int selectedRadioGroup = 0 ;
    private RadioButton radioButton ;
    private String spasticity = null ;
    private DatabaseReference databaseLimbSpasticity ;
    private EditText limb_spasticity_body_name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_limb_spasticity);

        limb_spasticity_duration_in_days = findViewById(R.id.limb_spasticity_duration_in_days);
        radioGroup = findViewById(R.id.limb_spasticity_side);
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);
        limb_spasticity_body_name = findViewById(R.id.limb_spasticity_body_name);
        databaseLimbSpasticity = FirebaseDatabase.getInstance().getReference("limbspasticity");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                radioButton = findViewById(checkedId);
                spasticity = radioButton.getText().toString().trim() ;

            }
        });




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemLimbSpasticityActivity.this, ProblemsAfterStrokeActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(limb_spasticity_duration_in_days.getText().toString().trim()) && spasticity != null && !TextUtils.isEmpty(limb_spasticity_body_name.getText().toString().trim()))
                    submitInfo();
                else {
                    Toast.makeText(getApplicationContext() , "Please fill the details completely" , Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void submitInfo(){
        int limbSpasticityDays = Integer.parseInt(limb_spasticity_duration_in_days.getText().toString().trim());
        String bodyPartName = limb_spasticity_body_name.getText().toString().trim() ;

        String id = databaseLimbSpasticity.push().getKey();
        ProblemLimbSpasticityModel entry = new ProblemLimbSpasticityModel(id,userEmail,spasticity,limbSpasticityDays,bodyPartName) ;
        databaseLimbSpasticity.child(id).setValue(entry);

        Toast.makeText(this , "Limb Spasticity entry added" , Toast.LENGTH_LONG).show();



    }
}
