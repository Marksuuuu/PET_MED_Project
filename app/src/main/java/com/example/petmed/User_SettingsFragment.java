package com.example.petmed;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class User_SettingsFragment extends Fragment {

    CardView cardAbout;
    CardView cardProfile;
    CardView cardGuide;
    CardView cardLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__settings, container, false);

        cardAbout =  view.findViewById(R.id.cardAbout);
        cardProfile = view.findViewById(R.id.cardProfile);
        cardGuide = view.findViewById(R.id.cardGuide);
        cardLogout = view.findViewById(R.id.cardLogout);
        cardAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),About.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AdminProfile.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Guide.class);
                intent.putExtra("some", "Some Data");
                startActivity(intent);
            }
        });
        cardLogout.setOnClickListener(new View.OnClickListener() {
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