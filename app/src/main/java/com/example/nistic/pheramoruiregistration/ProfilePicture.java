package com.example.nistic.pheramoruiregistration;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class ProfilePicture extends MainActivity {
    ImageView profilePic;
    Intent intent;
    Button next;
    public  static final int RequestPermissionCode  = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_picture);

        profilePic = findViewById(R.id.profilePic);
        Button camButton = findViewById(R.id.captureImage);
        Button selectImage = findViewById(R.id.selectImage);
        next = findViewById(R.id.next);
        EnableRuntimePermission();


        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePic.buildDrawingCache();
                Bitmap bitmap2 = profilePic.getDrawingCache();

                try {
                    //Write file
                    String filename = "bitmap.png";
                    FileOutputStream stream = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
                    bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    //Cleanup
                    stream.close();
                    bitmap2.recycle();

                    //Add intents
                    allthedata = getIntent().getStringExtra("allthedata");
                    Intent in1 = new Intent(ProfilePicture.this, Summary.class);
                    in1.putExtra("profile_picture", filename);
                    in1.putExtra("allthedata", allthedata);
                    startActivity(in1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(ProfilePicture.this,
                Manifest.permission.CAMERA)) {
        } else {
            ActivityCompat.requestPermissions(ProfilePicture.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(ProfilePicture.this,"Permission canceled, now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    profilePic.setImageURI(selectedImage);
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    profilePic.setImageURI(selectedImage);
                }
                break;
        }
    }
}
