package com.example.mobileapp.activities.product;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.main.NewsletterActivity;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.WishlistsActivity;
import com.example.mobileapp.fragments.wishlist.WishlistFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {

    private TextView pName, pCategory, pBrand, pPrice;

    BottomNavigationView bottomNavigationView;

    Toolbar toolbar;

    FloatingActionButton floatingActionButton;

    public long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.wishlist);
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
                        startActivity(new Intent(getApplicationContext(), NewsletterActivity.class));
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
                        startActivity(new Intent(getApplicationContext(), BasketActivity.class));
                        overridePendingTransition(0,0);
                        return true;




                }
                return false;
            }
        });

        pName = findViewById(R.id.productNameTextView);
        pCategory = findViewById(R.id.productCategoryTextView);
        pBrand = findViewById(R.id.productBrandTextView);
        pPrice = findViewById(R.id.productPriceTextView);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWishlistDialogFragment();
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        String brand = intent.getStringExtra("brand");
        double price = intent.getDoubleExtra("price", 0);
        long id = intent.getLongExtra("pId",0);
        long barcode = intent.getLongExtra("pBarcode",0);

        pName.setText(name);
        pCategory.setText(category);
        pBrand.setText(brand);
        pPrice.setText(String.valueOf(price));


    }

    private void openWishlistDialogFragment() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        String brand = intent.getStringExtra("brand");
        long barcode = intent.getLongExtra("pBarcode",0);
        double price = intent.getDoubleExtra("price", 0);
        long id = intent.getLongExtra("pId",0);

       /* Intent intent = getIntent();
        long id = intent.getLongExtra("pId",0);*/

        WishlistFragment wishlistFragment = new WishlistFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("wId",id);
        bundle.putString("wName",name);
        bundle.putString("wCat",category);
        bundle.putString("wBrand",brand);
        bundle.putDouble("wPrice",price);
        bundle.putLong("wBarcode", barcode);
        wishlistFragment.setArguments(bundle);
        wishlistFragment.show(getSupportFragmentManager(), "wishlist fragment");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
