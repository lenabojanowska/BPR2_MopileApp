package com.example.mobileapp.activities.wishlist;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.mobileapp.activities.main.MainActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.adapter.WishlistAdapter;
import com.example.mobileapp.connection.apis.WishlistApi;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.viewmodels.WishlistViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class WishlistsActivity extends AppCompatActivity implements WishlistAdapter.OnWishlistListener {

    BottomNavigationView bottomNavigationView;

    //ViewModel
    private WishlistViewModel wishlistViewModel;
    private WishlistApi wishlistApi;

    private RecyclerView recyclerView;
    private WishlistAdapter adapter;

    private List<WishlistModel> wishlistList;

    private FirebaseAuth firebaseAuth;

    private WishlistModel wishlistModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        wishlistViewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlists);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String id = firebaseUser.getUid();

        Log.d(TAG, "uid on Main Activity " + id);

        recyclerView = (RecyclerView) findViewById(R.id.wishlistRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WishlistAdapter(wishlistList, this, this);
        recyclerView.setAdapter(adapter);
        wishlistList = new ArrayList<>();

        wishlistViewModel = ViewModelProviders.of(this).get(WishlistViewModel.class);
        wishlistViewModel.getWishlistList().observe(this, new Observer<List<WishlistModel>>() {
            @Override
            public void onChanged(List<WishlistModel> wishlistModels) {
                if(wishlistModels != null){
                    wishlistList = wishlistModels;
                    adapter.setWishlistList(wishlistModels);
                }
            }
        });

        wishlistViewModel.GetProductListByCategory(id);
        //wishlistViewModel.GetRetrofitResponse();


        //initialize and assign variable
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

                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), BasketActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.wishlist:

                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onWishlistClick(int position) {

        Intent intent = new Intent(this, WishlistProductsActivity.class);
        intent.putExtra("name", wishlistList.get(position).getName());

        intent.putExtra("id", wishlistList.get(position).getId());
        intent.putExtra("profileId", wishlistList.get(position).getProfileId());
        startActivity(intent);
        Log.d(TAG, "wishlist clicked: " + wishlistList.get(position).getId());
    }

    /*private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(this, WishlistActivity.class));
            finish();
            Log.d(TAG, "no firebase user on Wishlist Activity") ;
        }else{
            String email = firebaseUser.getProviderId();
            String id = firebaseUser.getUid();
            Log.d(TAG, "id provider on Main Activity " + email);
            Log.d(TAG, "uid on Main Activity " + id);

            wishlistViewModel.GetProductListByCategory(id);

            //binding.emailTextView.setText(email);
        }
    }*/
}
