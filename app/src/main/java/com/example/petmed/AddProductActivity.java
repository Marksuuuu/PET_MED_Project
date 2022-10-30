package com.example.petmed;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AddProductActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 200;
    private EditText etId, etName, etImageURL, etPrice, etDescription;
    Button btn_Add;
    ImageView upload;
    DatabaseReference productref;
    FirebaseStorage storage;
    StorageReference storeReference;
    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etImageURL = findViewById(R.id.etImageURL);
        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        btn_Add = findViewById(R.id.btn_add);
        upload = findViewById(R.id.imgupload);
        storage = FirebaseStorage.getInstance();
        storeReference = storage.getReference();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureselect();
            }
        });
        productref = FirebaseDatabase.getInstance().getReference().child("products");
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });
    }
    private void pictureselect() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(intent);
    }
    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null
                            && data.getData() != null) {
                        selectedImageUri  = data.getData();
                        Bitmap qwetryui = null;
                        try {
                            qwetryui
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        upload.setImageBitmap(qwetryui);
                    }
                }
            });
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == RESULT_OK ){
//            imageURI  = data.getData();
//
//            upload.setImageURI(imageURI);
//        }else{
//            Toast.makeText(AddProductActivity.this,"Error ", Toast.LENGTH_SHORT).show();
//        }
//    }
    private void addProduct() {
        long id = Long.parseLong(etId.getText().toString().trim());
        String name = etName.getText().toString().trim();
        String imageURL = etImageURL.getText().toString().trim();
        double price = Double.parseDouble(etPrice.getText().toString().trim());
        String description = etDescription.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(imageURL) || TextUtils.isEmpty(description)){
            Toast.makeText(AddProductActivity.this,"Empty field!, Error ", Toast.LENGTH_SHORT).show();
        }else {
            StorageReference riversRef = storeReference.child("images/"+ id);
            riversRef.putFile(selectedImageUri).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(AddProductActivity.this,"something went wrong!, Upload Error ", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AddProductActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            });
            Product product = new Product(id,name,imageURL,price,description);
            productref.push().setValue(product);
            Toast.makeText(AddProductActivity.this,"Adding Product, Success!", Toast.LENGTH_SHORT).show();
        }
        etId.getText().clear();
        etName.getText().clear();
        etImageURL.getText().clear();
        etPrice.getText().clear();
        etDescription.getText().clear();
    }
}