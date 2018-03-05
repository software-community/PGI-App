package com.mapps.stroleapp.careathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapps.stroleapp.R;

public class DosAndDontsActivity extends AppCompatActivity {

    private Button hwSoapAndWater, hwHandRub, back ,medicationsAdvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos_and_donts);

        hwSoapAndWater = findViewById(R.id.hw_soap_and_water);
        hwHandRub = findViewById(R.id.hw_hand_rub);
        back = findViewById(R.id.back);
        medicationsAdvice = findViewById(R.id.medication_advice);

        hwSoapAndWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DosAndDontsActivity.this, HwSoapAndWaterVideoActivity.class));
            }
        });

        hwHandRub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DosAndDontsActivity.this, HwHandRubVideoActivity.class));
            }
        });

        medicationsAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DosAndDontsActivity.this, MedicationAdvice.class));
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
