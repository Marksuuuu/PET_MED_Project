package com.example.petmed;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pet-med-8a8bc-default-rtdb.asia-southeast1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText fullName = findViewById(R.id.fullName);
        final EditText EditTextAddress = findViewById(R.id.EditTextAddress);
        final EditText EditTextEmail = findViewById(R.id.EditTextEmail);
        final EditText EditTextpasswordvar = findViewById(R.id.EditTextpassword);
        final EditText EditTextpassword2var = findViewById(R.id.EditTextpassword2);
        final EditText phone = findViewById(R.id.phone);
        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView createText = findViewById(R.id.createText);
        final ProgressBar progressBar = findViewById(R.id.progressBar);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnameTxt = fullName.getText().toString();
                final String EditTextAddressVar = EditTextAddress.getText().toString();
                final String EditTextEmailAddressVar = EditTextEmail.getText().toString();
                final String EditTextpassVal = EditTextpasswordvar.getText().toString();
                final String EditTextpassVal2 = EditTextpassword2var.getText().toString();
                final String phoneVar = phone.getText().toString();

                if(fullnameTxt.isEmpty() || EditTextAddressVar.isEmpty() || EditTextEmailAddressVar.isEmpty() || EditTextpassVal.isEmpty() || EditTextpassVal2.isEmpty() || phoneVar.isEmpty()){
                    Toast.makeText(Registration.this, "Please Fill This fields Thank you!.." , Toast.LENGTH_SHORT).show();
                }else if(!EditTextpassVal.equals(EditTextpassVal2)){
                    Toast.makeText(Registration.this, "Password are not , match Please Repeat", Toast.LENGTH_SHORT).show();

                }else{
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(fullnameTxt)){
                                Toast.makeText( Registration.this, "This Phone Number Already Exists/Registered" , Toast.LENGTH_SHORT).show();
                            }else{
                                databaseReference.child("Users").child(fullnameTxt).child("Email Address").setValue(EditTextEmailAddressVar);
                                databaseReference.child("Users").child(fullnameTxt).child("Full Name").setValue(fullnameTxt);
                                databaseReference.child("Users").child(fullnameTxt).child("Address").setValue(EditTextAddressVar);
                                databaseReference.child("Users").child(fullnameTxt).child("Password").setValue(EditTextpassVal);

                                Toast.makeText(Registration.this, "User Registered Successfully ", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });


        createText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}