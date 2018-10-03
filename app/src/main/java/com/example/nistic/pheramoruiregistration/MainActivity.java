package com.example.nistic.pheramoruiregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    String finalPassword;
    String finalName;
    String finalGender;
    String finalGenderPref;
    String finalDOB;
    Integer finalAgeMin;
    Integer finalAgeMax;
    Integer finalZipCode;
    String finalHeight;
    String finalHeightUnit;
    String finalRace;
    String finalReligion;
    String allthedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
