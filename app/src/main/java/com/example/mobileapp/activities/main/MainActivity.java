package com.example.mobileapp.activities.main;

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

import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.main.adapter.NewsletterAdapter;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.WishlistsActivity;
import com.example.mobileapp.activities.wishlist.adapter.WishlistAdapter;
import com.example.mobileapp.databinding.ActivityMainBinding;
import com.example.mobileapp.models.NewsletterModel;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.viewmodels.NewsletterViewModel;
import com.example.mobileapp.viewmodels.WishlistViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ActivityMainBinding binding;

    private NewsletterViewModel newsletterViewModel;

    private RecyclerView recyclerView;
    private NewsletterAdapter adapter;

    private List<NewsletterModel> newsletterList;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //newsletterViewModel = new ViewModelProvider(this).get(NewsletterViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* firebaseAuth = FirebaseAuth.getInstance();
        checkUser();*/

        recyclerView = (RecyclerView) findViewById(R.id.newslettersRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsletterAdapter(newsletterList, this);
        recyclerView.setAdapter(adapter);
        newsletterList = new ArrayList<>();

        newsletterViewModel = ViewModelProviders.of(this).get(NewsletterViewModel.class);
        newsletterViewModel.getNewsletters().observe(this, new Observer<List<NewsletterModel>>() {
            @Override
            public void onChanged(List<NewsletterModel> newsletterModels) {
                if(newsletterModels != null){
                    newsletterList = newsletterModels;
                    adapter.setNewsletterList(newsletterModels);
                }
            }
        });
        newsletterViewModel.GetNewsletters();

        //initialize and assign variable
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        //perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        return true;

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

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

   /* private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            String email = firebaseUser.getEmail();
            Log.d(TAG, "email on Main Activity " + email);
            //binding.emailTextView.setText(email);
        }
    }*/
}