package com.example.mobileapp.activities.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mobileapp.activities.main.MainActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.WishlistsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        BottomNavigationView bottomNavigationView;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initialize and assign variable
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.profile);
        //perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), BasketActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.wishlist:
                        startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:

                        return true;
                }
                return false;
            }
        });
    }
}