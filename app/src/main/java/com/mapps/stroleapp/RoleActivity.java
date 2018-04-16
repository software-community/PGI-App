package com.mapps.stroleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.registration.RegisterActivity;

public class RoleActivity extends AppCompatActivity {

    private Button roleCaregiver, rolePatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        roleCaregiver = findViewById(R.id.role_care_giver);
        rolePatient = findViewById(R.id.role_patient);

        roleCaregiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("role", "care");
                editor.commit();

                startActivity(new Intent(RoleActivity.this, ProfileActivity.class));
            }
        });

        rolePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("role", "patient");
                editor.commit();

                startActivity(new Intent(RoleActivity.this, ProfileActivity.class));
            }
        });
    }
}
