package com.example.nistic.pheramoruiregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email extends MainActivity{
    Button buttonEmail;

    EditText email1;
    EditText email2;

    String emailText;
    String emailConfirmText;
    static Pattern emailPattern = Pattern.compile("[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);

        buttonEmail = findViewById(R.id.buttonEmail);

        email1 = findViewById(R.id.email1);
        email2 = findViewById(R.id.email2);

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailGetter();
                if (!emailText.equals(emailConfirmText)) {
                    Toast.makeText(Email.this, "Emails do not match", Toast.LENGTH_SHORT).show();
                }
                if (!isValidEmail(emailText)) {
                    Toast.makeText(Email.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }
                else if (isValidEmail(emailText) && emailText.equals(emailConfirmText)) {
                    //finalEmail = emailText;
                    Intent intent = new Intent(Email.this, Password.class);
                    intent.putExtra("allthedata", emailText + "#");
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
    }

    public void emailGetter() {
        emailText = email1.getText().toString();
        emailConfirmText = email2.getText().toString();
    }
    public static boolean isValidEmail(String email)
    {
        Matcher m = emailPattern.matcher(email);
        return m.matches();
    }

}
