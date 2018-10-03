package com.example.nistic.pheramoruiregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameZipHeight extends MainActivity {
    EditText fullname;
    EditText zipcode;
    EditText height;

    String nameText;
    String zipcodeText;
    String heightText;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullname_zip_height);

        fullname = findViewById(R.id.fullname);
        zipcode = findViewById(R.id.zipcode);
        height = findViewById(R.id.height);
        next = findViewById(R.id.button);

        final Spinner heightUnit = findViewById(R.id.heightUnit);
        String[] unitList = new String[]{"in", "ft", "mm", "cm", "m"};
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, unitList);
        heightUnit.setAdapter(unitAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringGetter();
                Pattern p = Pattern.compile("^[ A-Za-z]+$");
                Matcher m = p.matcher(nameText);
                boolean nameOnlyLetters = m.matches();
                finalHeightUnit = heightUnit.getSelectedItem().toString();
                if (!nameText.contains(" ") || !nameOnlyLetters) {
                    Toast.makeText(FullNameZipHeight.this, "Must enter a first and last name, with just letters and spaces, no numbers", Toast.LENGTH_SHORT).show();
                }
                if (!isNumeric(zipcodeText) || zipcodeText.length() != 5) {
                    Toast.makeText(FullNameZipHeight.this, "Zipcode must be a 5 digit number", Toast.LENGTH_SHORT).show();
                }
                if (!isNumeric(heightText)) {
                    Toast.makeText(FullNameZipHeight.this, "Height must be a number", Toast.LENGTH_SHORT).show();
                }
                else if (nameText.contains(" ") && (isNumeric(zipcodeText) && zipcodeText.length() == 5) && heightText.matches(".*[0-9].*") && nameOnlyLetters) {
                    finalName = nameText;
                    finalZipCode = Integer.parseInt(zipcodeText);
                    finalHeight = heightText + " " + finalHeightUnit;
                    allthedata = getIntent().getStringExtra("allthedata");
                    allthedata += nameText + "#" + zipcodeText + "#" + finalHeight + "#";
                    Intent intent = new Intent(FullNameZipHeight.this, GenderDOB.class);
                    intent.putExtra("allthedata", allthedata);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });

    }

    public void StringGetter() {
        nameText = fullname.getText().toString();
        zipcodeText = zipcode.getText().toString();
        heightText = height.getText().toString();
    }
    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
