package com.example.mobileapp.activities.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.adapter.WishlistAdapter;
import com.example.mobileapp.activities.wishlist.adapter.WishlistProductsAdapter;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.viewmodels.WishlistProductsViewModel;
import com.example.mobileapp.viewmodels.WishlistViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WishlistProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WishlistProductsAdapter adapter;

    private WishlistProductsViewModel wishlistProductsViewModel;
    private List<ProductModel> wishlistProductsList;

    private FloatingActionButton floatingActionButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_products);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        String profileId = intent.getStringExtra("profileId");
        int id = intent.getIntExtra("id", 0);

        recyclerView = (RecyclerView) findViewById(R.id.wishlistProductsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WishlistProductsAdapter(wishlistProductsList, this);
        recyclerView.setAdapter(adapter);
        wishlistProductsList = new ArrayList<>();

        wishlistProductsViewModel = ViewModelProviders.of(this).get(WishlistProductsViewModel.class);
        wishlistProductsViewModel.getProductsOnWishlist().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                if(productModels != null){
                    wishlistProductsList = productModels;
                    adapter.setWishlistList(wishlistProductsList);
                }
            }
        });
        wishlistProductsViewModel.GetProductsOnWishlist(id);

        //Toast.makeText(this, "name: " + name, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "id: " + id, Toast.LENGTH_LONG).show();

        floatingActionButton = findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent1);
            }
        });
    }


}
