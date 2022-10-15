package com.example.petmed;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Admin_SettingsFragment extends Fragment {


    CardView cardProfileAdmin;
    CardView cardLogoutAdmin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin__settings, container, false);

        cardProfileAdmin = view.findViewById(R.id.cardProfileAdmin);
        cardLogoutAdmin = view.findViewById(R.id.cardLogoutAdmin);
        cardProfileAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Profile.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardLogoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Logout.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });

        return view;


    }
}