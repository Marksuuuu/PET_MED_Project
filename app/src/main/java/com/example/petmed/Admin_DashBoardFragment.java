package com.example.petmed;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Admin_DashBoardFragment extends Fragment {

    CardView cardAppoint2;
    CardView cardGrooming2;
    CardView cardProducts2;
    CardView cardTransactions2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin__home, container, false);

        cardAppoint2 =  view.findViewById(R.id.cardAppoint2);
        cardGrooming2 = view.findViewById(R.id.cardGrooming2);
        cardProducts2 = view.findViewById(R.id.cardProducts2);
        cardTransactions2 = view.findViewById(R.id.cardTransactions2);
        cardAppoint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AdminAppointment.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardGrooming2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AdminGrooming.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardProducts2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AdminShop.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardTransactions2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AdminTransaction.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });

        return view;


    }
}