package com.example.nistic.pheramoruiregistration;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Summary extends MainActivity {
    ImageView profilePic;
    TextView fullName;
    TextView summaryText;
    Button sendData;
    String resultFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        resultFinal = "This definitely failed";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        fullName = findViewById(R.id.fullname);
        summaryText = findViewById(R.id.summaryText);
        sendData = findViewById(R.id.button);
        profilePic = findViewById(R.id.profilePic);

        Bitmap bitmap2 = null;
        String filename = getIntent().getStringExtra("profile_picture");
        try {
            FileInputStream is = this.openFileInput(filename);
            bitmap2 = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        profilePic.setImageBitmap(bitmap2);
        allthedata = getIntent().getStringExtra("allthedata");
        String[] result = allthedata.split("#", 11);
        final String summaryInfo = "Email:                  " + result[0] + "\n" + "Zipcode:             " + result[2] + "\n" + "Height:                " + result[3] + "\n" + "Gender:               " + result[4] + "\n" +
                "Date of birth:     " + result[5] + "\n" + "Interested in:     " + result[6] + "\n" + "Between ages:  " + result[7] + " to " + result[8] + "\n" +
                "Race:                    " + result[9] + "\n" + "Religion:              " + result[10] + "\n";
        final String name2 = result[1];
        fullName.setText(result[1]);
        summaryText.setText(summaryInfo);

        final JSONObject userReg = new JSONObject();
        try {
            userReg.put("Email", result[0]);
            userReg.put("Name", result[1]);
            userReg.put("Zipcode", result[2]);
            userReg.put("Height", result[3]);
            userReg.put("Gender", result[4]);
            userReg.put("Date of birth", result[5]);
            userReg.put("Interested in", result[6]);
            userReg.put("Age range", result[7] + " to " + result[8]);
            userReg.put("Race", result[9]);
            userReg.put("Religion", result[10]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String jsonString = userReg.toString();
        final String apiEndpoint = "https://external.dev.pheramor.com/";

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
                Toast.makeText(Summary.this, "Registration Complete!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    protected String jsonStringGetter() {
        allthedata = getIntent().getStringExtra("allthedata");
        String[] result = allthedata.split("#", 11);
        final JSONObject userReg = new JSONObject();
        try {
            userReg.put("Email", result[0]);
            userReg.put("Name", result[1]);
            userReg.put("Zipcode", result[2]);
            userReg.put("Height", result[3]);
            userReg.put("Gender", result[4]);
            userReg.put("Date of birth", result[5]);
            userReg.put("Interested in", result[6]);
            userReg.put("Age range", result[7] + " to " + result[8]);
            userReg.put("Race", result[9]);
            userReg.put("Religion", result[10]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String jsonString = userReg.toString();
        return jsonString;
    }

    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                Post_JSON(jsonStringGetter());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });


    public void Post_JSON(String json) {
        String query_url = "https://external.dev.pheramor.com/";
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
            System.out.println("Result after Reading JSON Response");
//            JSONObject myResponse = new JSONObject(result);
//            System.out.println("jsonrpc- "+myResponse.getString("jsonrpc"));
//            System.out.println("id- "+myResponse.getInt("id"));
//            System.out.println("result- "+myResponse.getString("result"));
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}