 package com.example.wagoner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.Console;
import java.io.IOException;
import java.net.URI;

 public class CreateWheelActivity extends AppCompatActivity {

     Wheel newUserWheel;

    ImageView imageView;
    Button chooseBtn;
    Button confirmBtn;
    Bitmap wheelImage;
    String idInput;
    Gson gson = new Gson();

    private static final int IMAGE_PICK_CODE = 1000;
     private static final int PERMISSION_CODE = 1001;
     private TextInputLayout createWheelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wheel);

        //VIEWS
        createWheelID = findViewById(R.id.create_wheelID);
        imageView = findViewById(R.id.image_view);
        chooseBtn = findViewById(R.id.choose_image_btn);
        confirmBtn = findViewById(R.id.confirm_btn);

        //Handlers
        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //permission not yet granted
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else {
                        //Permission already granted
                        pickImageFromGallery();
                    }
                }
                else {
                    //System OS is less than Marshmallow
                    pickImageFromGallery();
                }
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateWheelIDInput()) {
                    newUserWheel = new Wheel(wheelImage, idInput);
                    String json = gson.toJson(newUserWheel);
                    new SendDeviceDetails().execute("tancredbs.cspqiztnfuiy.us-east-1.rds.amazonaws.com", json);
                }
            }
        });
    }


    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         switch (requestCode) {
             case PERMISSION_CODE:
                 if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                     //Permission Granted
                     pickImageFromGallery();
                 }
                 else {
                     //permission denied
                     Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                 }
         }
     }

     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //set image to image view
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
            try {
                wheelImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     }

     public void confirmInput(View v) {
         if(!validateWheelIDInput()) {
             return;
         }
     }

     private boolean validateWheelIDInput() {
        idInput = createWheelID.getEditText().getText().toString().trim();

        if (idInput.isEmpty()) {
             createWheelID.setError("Field can't be empty");
             return false;
         }
         else {
             createWheelID.setError(null);
             return true;
         }
     }
 }