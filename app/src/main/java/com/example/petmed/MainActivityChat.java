package com.example.petmed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petmed.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivityChat extends AppCompatActivity {

    ImageButton imageButton;
    EditText editText;
    Toolbar toolbar;
    TextView fullname;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        imageButton = findViewById(R.id.SendButton);
        editText = findViewById(R.id.Messaget);


//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        fullname = findViewById(R.id.fullname);

        intent = getIntent();
//
        final String userid = intent.getStringExtra("id");
//
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();
                if(!msg.equals("")){
                    sendMessage(firebaseUser.getUid(),userid, msg);
                }else {
                    Toast.makeText(MainActivityChat.this,"You Cannot Send Empty Message You Fooooool!. ", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                fullname.setText(user.getFullname());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void sendMessage(String sender , String receiver , String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Sender", sender);
        hashMap.put("Receiver", receiver);
        hashMap.put("Message", message);

        reference.child("Chats").push().setValue(hashMap);
    }
}