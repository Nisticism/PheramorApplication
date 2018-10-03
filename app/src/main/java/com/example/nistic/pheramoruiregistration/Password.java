package com.example.nistic.pheramoruiregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class Password extends MainActivity{
    Button passwordNext;
    EditText password;

    String passwordText;
    Integer count = 0;
    List<String> specialChar = Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "{", "}", "[", "]", "=", "+", "|", "?", "/", "_", "-", ";", ":", "'", "<", ">", ".", ",");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        passwordNext = findViewById(R.id.passNext);
        password = findViewById(R.id.password);
        passwordNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordGetter();
                if (!passwordText.matches(".*[0-9].*")) {
                    Toast.makeText(Password.this, "Password must contain at least one number", Toast.LENGTH_SHORT).show();
                }
                if (!passwordText.matches(".*[a-z].*")) {
                    Toast.makeText(Password.this, "Password must contain at least one letter", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < specialChar.size(); i ++) {
                    if (passwordText.contains(specialChar.get(i))) {
                        count += 1;
                    }
                }
                if (count == 0) {
                    Toast.makeText(Password.this, "Password must contain at least one special character ( /!@#$%^&*(){}[]=_:;?-., )", Toast.LENGTH_SHORT).show();
                }
                if (passwordText.length() < 6) {
                    Toast.makeText(Password.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                }
                else if (passwordText.matches(".*[0-9].*") && passwordText.matches(".*[a-z].*") && count > 0 && passwordText.length() >= 6){
                    finalPassword = passwordText;
                    count = 0;
                    allthedata = getIntent().getStringExtra("allthedata");
                    Intent intent = new Intent(Password.this, FullNameZipHeight.class);
                    intent.putExtra("allthedata", allthedata);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
    }

    public void passwordGetter() {
        passwordText = password.getText().toString();
    }
}
