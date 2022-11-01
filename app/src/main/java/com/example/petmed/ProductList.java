package com.example.petmed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petmed.Adapter.ProductAdapter;
import com.example.petmed.model.Prod;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



class ProductList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ProductAdapter productAdapter;
    ArrayList<Prod> prods;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.productlist);
        databaseReference = FirebaseDatabase.getInstance().getReference("appointment");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        prods = new ArrayList<>();
        productAdapter = new ProductAdapter(this,prods);
        recyclerView.setAdapter(productAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Prod prod = dataSnapshot.getValue(Prod.class);
                    prods.add(prod);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}