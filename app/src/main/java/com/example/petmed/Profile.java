package com.example.petmed;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    public static final int CAMERA_ACTION_CODE=1;
    Dialog myDialog;
    Button galleryImg;
    Button cameraImg;
    ImageView imgView;
    ActivityResultLauncher<Intent> activityResultLauncher;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pet-med-8a8bc-default-rtdb.asia-southeast1.firebasedatabase.app/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        myDialog = new Dialog(this);
        galleryImg = (Button) findViewById(R.id.galleryImg);
        cameraImg = (Button) findViewById(R.id.cameraImg);
        imgView = (ImageView) findViewById(R.id.imgView);









        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() !=null){
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imgView.setImageBitmap(bitmap);
                    imgView.setClipToOutline(true);
                }else {
                    Toast.makeText(Profile.this, "Cancelled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.setContentView(R.layout.popup_box);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }); */

        galleryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                galleryActivityResultLauncher.launch(intent);

            }
        });

        cameraImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activityResultLauncher.launch(intent);
            }
        });
    }

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri imageUri = data.getData();

                        imgView.setImageURI(imageUri);
                        imgView.setClipToOutline(true);
                    }
                    else{
                        Toast.makeText(Profile.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );


}