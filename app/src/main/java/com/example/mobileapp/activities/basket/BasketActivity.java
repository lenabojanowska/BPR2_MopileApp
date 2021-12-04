package com.example.mobileapp.activities.basket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.mobileapp.MainActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.scan.ScanActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.WishlistsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BasketActivity extends AppCompatActivity {

    private Button scanButton;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);



        //initialize and assign variable
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.basket);
        //perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.wishlist:
                        startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.basket:

                        return true;
                }
                return false;
            }
        });

        scanButton = findViewById(R.id.scanButton);
        scanButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScanActivity.class);
            startActivity(intent);
        });
    }

    private void openScanActivity() {
        startActivity(new Intent(getApplicationContext(), ScanActivity.class));
    }
}