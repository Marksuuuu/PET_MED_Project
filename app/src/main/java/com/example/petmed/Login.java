package com.example.petmed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth auth;
        EditText email = findViewById(R.id.emailAddress);
        EditText password = findViewById(R.id.EditTextpassword);
        Button loginBtn = findViewById(R.id.loginBtn);
        TextView Register = findViewById(R.id.Register);
        TextView forgotpass = findViewById(R.id.forgotpass);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        googleBtn = findViewById(R.id.loginasgoogle);

        gso = new GoogleSignInOptions.Builder(gso.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        auth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_var = email.getText().toString();
                String pass_var = password.getText().toString();


                if(email_var.isEmpty() || pass_var.isEmpty()) {
                    Toast.makeText(Login.this, "Please Enter your Email and Password , FOOOOOLL!", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(email_var,pass_var)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        String reguser = user.getUid();
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(reguser);

                                        databaseReference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                System.out.println(dataSnapshot);
                                                String userType = dataSnapshot.child("Fullname").getValue().toString();
                                                if(userType.equals("Admin")){

                                                    Intent intent = new Intent(Login.this, Admin.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                                else{

                                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });



                                    }else{
                                        Toast.makeText(Login.this,"Authentication Failed , You FOOOOOL!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String emailAddressVar = email.getText().toString();
//                final String EditTextpassVal = password.getText().toString();
//
//
//                if(emailAddressVar.isEmpty() || EditTextpassVal.isEmpty()){
//                    Toast.makeText(Login.this,"Please Enter your Email and Password", Toast.LENGTH_SHORT).show();
//                }else{
//                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if(snapshot.hasChild(emailAddressVar)){
//                                if(EditTextpassVal.equals(emailAddressVar)){
//                                    startActivity(new Intent(Login.this, MainActivity.class));
//                                    finish();
//                                }else{
//                                    Toast.makeText(Login.this, "Please Check Carefully", Toast.LENGTH_SHORT).show();
//                                }
//                            }else{
//                                Toast.makeText(Login.this, "Details Not Found", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//
//                }
//
//            }
//        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, ForgotpasswordActivity.class));
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }


        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



    }

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
}