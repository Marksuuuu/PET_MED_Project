package com.example.petmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



class ProductList extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productcard);


//        RecyclerView recyclerView;
//        DatabaseReference databaseReference;
//        connector connector;
//        ArrayList<productdetails> arraylist;
//
//        recyclerView = findViewById(R.id.productlist);
//        databaseReference = FirebaseDatabase.getInstance().getReference("products");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        arraylist = new ArrayList<>();
//        connector = new connector(this,arraylist);
//        recyclerView.setAdapter(connector);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    productdetails productdetails = dataSnapshot.getValue(productdetails.class);
//                    arraylist.add(productdetails);
//                }
//                connector.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



    }
}