package com.example.nistic.pheramoruiregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class GenderPrefAgeRange extends MainActivity {
    RadioButton male;
    RadioButton female;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genderpref_agerange);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        next = findViewById(R.id.button);

        //Minimum age spinner
        final Spinner ageMin = findViewById(R.id.ageMin);
        String[] minList = new String[]{"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
                "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63",
                "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88",
                "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111",
                "112", "113", "114", "115", "116", "117", "118", "119", "120"};
        ArrayAdapter<String> adapterAgeMin = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, minList);
        ageMin.setAdapter(adapterAgeMin);

        //Maximum age spinner
        final Spinner ageMax = findViewById(R.id.ageMax);
        String[] maxList = new String[]{"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
                "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63",
                "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88",
                "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111",
                "112", "113", "114", "115", "116", "117", "118", "119", "120"};
        ArrayAdapter<String> adapterAgeMax = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, maxList);
        ageMax.setAdapter(adapterAgeMax);
        ageMax.setSelection(42);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!male.isChecked() && !female.isChecked()) {
                    Toast.makeText(GenderPrefAgeRange.this, "Must choose at least one gender", Toast.LENGTH_SHORT).show();
                }
                else if (male.isChecked() || female.isChecked()) {
                    if (!male.isChecked()) {
                        finalGenderPref = "Females";
                    }
                    else if (!female.isChecked()) {
                        finalGenderPref = "Males";
                    }
                    else if (male.isChecked() && female.isChecked()) {
                        finalGenderPref = "Males and Females";
                    }
                    finalAgeMin = Integer.parseInt(ageMin.getSelectedItem().toString());
                    finalAgeMax = Integer.parseInt(ageMax.getSelectedItem().toString());
                    allthedata = getIntent().getStringExtra("allthedata");
                    allthedata += finalGenderPref + "#" + finalAgeMin + "#" + finalAgeMax + "#";
                    Intent intent = new Intent(GenderPrefAgeRange.this, RaceReligion.class);
                    intent.putExtra("allthedata", allthedata);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
    }

    public void clearSelections (View v) {
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        if (male.isChecked()) {
            male.setChecked(false);
        }
        if (female.isChecked()) {
            female.setChecked(false);
        }
    }
}
