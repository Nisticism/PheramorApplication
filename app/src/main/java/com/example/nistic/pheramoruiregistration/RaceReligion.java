package com.example.nistic.pheramoruiregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RaceReligion extends MainActivity {
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_religion);
        next = findViewById(R.id.button);

        //Race selection
        final Spinner raceSelection = findViewById(R.id.race);
        String[] raceList = new String[]{"Elven", "Dwarf", "Human", "Goblin", "Dragon", "Hobbit"};
        ArrayAdapter<String> raceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, raceList);
        raceSelection.setAdapter(raceAdapter);

        //Religion selection
        final Spinner religionSelection = findViewById(R.id.religion);
        String[] religionList = new String[]{"Church of the Flying Spaghetti Monster", "Atheist", "The world is pretty neat", "Christian", "Islamic", "Buddist"};
        ArrayAdapter<String> religionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, religionList);
        religionSelection.setAdapter(religionAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finalRace = raceSelection.getSelectedItem().toString();
                    finalReligion = religionSelection.getSelectedItem().toString();
                    allthedata = getIntent().getStringExtra("allthedata");
                    allthedata += finalRace + "#" + finalReligion;
                    Intent intent = new Intent(RaceReligion.this, ProfilePicture.class);
                    intent.putExtra("allthedata", allthedata);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

}
