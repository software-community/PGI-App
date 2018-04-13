package com.mapps.stroleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs" ;

    private SharedPreferences languagePref;
    private SharedPreferences.Editor editor;
    public static final String language = null;

    Locale mylocale;
    Button langHindi, langEnglish , acknowledgement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        langHindi = findViewById(R.id.lang_hindi);
        langEnglish = findViewById(R.id.lang_english);
        acknowledgement = findViewById(R.id.acknowledgement);

        langHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("hi");
                startActivity(new Intent(LanguageActivity.this, RoleActivity.class));
            }
        });

        langEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
                startActivity(new Intent(LanguageActivity.this, RoleActivity.class));
            }
        });

        acknowledgement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanguageActivity.this, Acknowledgements.class));
            }
        });

    }

    protected void setLanguage(String language){
        mylocale=new Locale(language);
        Resources resources=getResources();
        DisplayMetrics dm=resources.getDisplayMetrics();
        Configuration conf= resources.getConfiguration();
        conf.locale=mylocale;
        resources.updateConfiguration(conf,dm);
        Intent refreshIntent=new Intent(LanguageActivity.this,LanguageActivity.class);
        finish();
        startActivity(refreshIntent);
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
