package com.example.petmed;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {

//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pet-med-8a8bc-default-rtdb.asia-southeast1.firebasedatabase.app/");
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

         EditText fullName = findViewById(R.id.fullName);
         EditText EditTextAddress = findViewById(R.id.EditTextAddress);
         EditText email = findViewById(R.id.EditTextEmail);
         EditText password = findViewById(R.id.EditTextpassword);
         EditText EditTextpassword2var = findViewById(R.id.EditTextpassword2);
         EditText phone = findViewById(R.id.phone);
         Button registerBtn = findViewById(R.id.registerBtn);
         TextView createText = findViewById(R.id.createText);
         ProgressBar progressBar = findViewById(R.id.progressBar);


         auth = FirebaseAuth.getInstance();

         registerBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String str_fullname = fullName.getText().toString();
                 String str_Address = EditTextAddress.getText().toString();
                 String str_email = email.getText().toString();
                 String str_password = password.getText().toString();
                 String str_password2 = EditTextpassword2var.getText().toString();

                 if(TextUtils.isEmpty(str_fullname) || TextUtils.isEmpty(str_Address) || TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password) ||  TextUtils.isEmpty(str_password2)){
                        Toast.makeText(Registration.this,"You Must Fill All Fields , YOU FOOOOOOL!", Toast.LENGTH_SHORT).show();

                 }else if(str_password.length()<6 && str_password2.length() <6){

                 }else{
                     register(str_fullname,str_email,str_password,str_Address);
                 }
             }
         });
         

    }
    private void register(String fullName, String email, String password, String EditTextAddress){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("Fullname", fullName);
                            hashMap.put("id", userid);
                            hashMap.put("Email", email);
                            hashMap.put("Password", password);
                            hashMap.put("Address", EditTextAddress);

                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(Registration.this, Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(Registration.this,"Registration Completed, FOOOOLL!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Registration.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}