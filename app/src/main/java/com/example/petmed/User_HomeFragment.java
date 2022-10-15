package com.example.petmed;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class User_HomeFragment extends Fragment {

    CardView cardAppoint;
    CardView cardGrooming;
    CardView cardProducts;
    CardView cardTransactions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        cardAppoint =  view.findViewById(R.id.cardAppoint);
        cardGrooming = view.findViewById(R.id.cardGrooming);
        cardProducts = view.findViewById(R.id.cardProducts);
        cardTransactions = view.findViewById(R.id.cardTransactions);
        cardAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Appointment.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardGrooming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),grooming.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Products.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Transactions.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });

        return view;


    }
}