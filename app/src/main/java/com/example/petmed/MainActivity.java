package com.example.petmed;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    User_HomeFragment User_HomeFragment = new User_HomeFragment();
    User_ChatFragment User_ChatFragment = new User_ChatFragment();
    User_SettingsFragment User_SettingsFragment = new User_SettingsFragment();

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pet-med-8a8bc-default-rtdb.asia-southeast1.firebasedatabase.app/");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, User_HomeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, User_HomeFragment).commit();
                        return true;
                    case R.id.chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, User_ChatFragment).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, User_SettingsFragment).commit();
                        return true;

                }
                return false;
            }
        });
    }
}

