package com.example.nistic.pheramoruiregistration;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.Calendar;

public class GenderDOB extends MainActivity implements TextWatcher {
    RadioButton male;
    RadioButton female;
    Button next;
    EditText date;

    private String current = "";
    private String ddmmyyyy = "DDMMYYYY";
    private Calendar cal = Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gender_dob);
        date = findViewById(R.id.date);
        date.addTextChangedListener(this);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        next = findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (male.isChecked() && female.isChecked()) {
                    Toast.makeText(GenderDOB.this, "Please select only male or female, not both", Toast.LENGTH_SHORT).show();
                }
                if (!male.isChecked() && !female.isChecked()) {
                    Toast.makeText(GenderDOB.this, "Please select male or female", Toast.LENGTH_SHORT).show();
                }
                if (!Character.isDigit(date.getText().toString().charAt(9)) || date.getText().toString().equals("")) {
                    Toast.makeText(GenderDOB.this, "Please enter a complete date", Toast.LENGTH_SHORT).show();
                }
                else if (male.isChecked() || female.isChecked() && Character.isDigit(date.getText().toString().charAt(9))) {
                    finalDOB = date.getText().toString();
                    if (male.isChecked()) {
                        finalGender = "Male";
                    }
                    else if (female.isChecked()) {
                        finalGender = "Female";
                    }
                    finalDOB = date.getText().toString();
                    allthedata = getIntent().getStringExtra("allthedata");
                    allthedata += finalGender + "#" + finalDOB + "#";
                    Intent intent = new Intent(GenderDOB.this, GenderPrefAgeRange.class);
                    intent.putExtra("allthedata", allthedata);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals(current)) {
            String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
            String cleanC = current.replaceAll("[^\\d.]|\\.", "");

            int cl = clean.length();
            int sel = cl;
            for (int i = 2; i <= cl && i < 6; i += 2) {
                sel++;
            }
            //Fix for pressing delete next to a forward slash

            if (clean.equals(cleanC)) sel--;

            if (clean.length() < 8){
                clean = clean + ddmmyyyy.substring(clean.length());
            }else{
                //This part makes sure that when we finish entering numbers
                //the date is correct, fixing it otherwise

                int day  = Integer.parseInt(clean.substring(0,2));
                int mon  = Integer.parseInt(clean.substring(2,4));
                int year = Integer.parseInt(clean.substring(4,8));

                mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                cal.set(Calendar.MONTH, mon-1);
                year = (year<1900)?1900:(year>2100)?2100:year;
                cal.set(Calendar.YEAR, year);

                // This should work correctly with leap years

                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                clean = String.format("%02d%02d%02d",day, mon, year);
            }

            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                    clean.substring(2, 4),
                    clean.substring(4, 8));

            sel = sel < 0 ? 0 : sel;
            current = clean;
            date.setText(current);
            date.setSelection(sel < current.length() ? sel : current.length());
        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void afterTextChanged(Editable s) {}
}
