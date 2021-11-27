package com.example.mobileapp.activities.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobileapp.MainActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.WishlistApi;
import com.example.mobileapp.connection.responses.WishlistsResponse;
import com.example.mobileapp.models.WishlistModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button proofOfConceptButton;
    Button postButton;
    Button deleteButton;
    EditText name, username, id;
    //ViewModel
    private WishlistViewModel wishlistViewModel;
    private WishlistApi wishlistApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        wishlistViewModel = new ViewModelProvider(this).get(WishlistViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        proofOfConceptButton = findViewById(R.id.proofButton);

        proofOfConceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Tag", "Button Clicked");

                GetRetrofitResponse();
            }
        });

        name = findViewById(R.id.nameTextView);
        username = findViewById(R.id.usernameTextView);
        postButton = findViewById(R.id.postButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Tag", "Post Button Clicked");
                CallRetrofit();
            }
        });

        id = findViewById(R.id.idTextView);
        deleteButton = findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Tag", "Delete Button Clicked");
                DeleteRetrofitResponse();
            }
        });

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

    private void DeleteRetrofitResponse() {
        String idField = id.getText().toString();
        WishlistApi wishlistApi = ServiceGenerator.getWishListApi();
        Call<Void> call = wishlistApi.deleteWishlistById(Integer.parseInt(idField));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.v("Tag", "Deleted "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.v("Tag", t.toString());
            }
        });
    }

    private void CallRetrofit() {
        String postName = name.getText().toString();
        String postUsername = username.getText().toString();

        WishlistApi wishlistApi = ServiceGenerator.getWishListApi();
        WishlistModel wishlistModel = new WishlistModel(0, postName, postUsername);

        Log.v("Tag", "Post");
        Call<WishlistModel> call = wishlistApi.postWishlist(wishlistModel);

        call.enqueue(new Callback<WishlistModel>() {
            @Override
            public void onResponse(Call<WishlistModel> call, Response<WishlistModel> response) {
                Log.v("Tag", response.body().getName());
            }

            @Override
            public void onFailure(Call<WishlistModel> call, Throwable t) {
                Log.v("Tag", t.toString());
            }
        });

    }

    private void GetRetrofitResponse() {

        WishlistApi wishlistApi = ServiceGenerator.getWishListApi();
        Call<List<WishlistModel>> call = wishlistApi.getWishListSecond();
        call.enqueue(new Callback<List<WishlistModel>>() {

            @Override
            public void onResponse(Call<List<WishlistModel>> call, Response<List<WishlistModel>> response) {
                if(response.isSuccessful()){
                    Log.v("Tag", "the response " + response.body().toString());

                    List<WishlistModel> wishlists = response.body();

                    for(WishlistModel wishlistModel: wishlists) {
                        Log.v("Tag", "The List " + wishlistModel.getName());
                    }
                }
                else {
                    try {
                        Log.v("Tag", "Error " + response.errorBody().string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<WishlistModel>> call, Throwable t) {

                Log.v("Tag", t.toString(),t.getCause());
                t.getStackTrace();
            }
        });

    }

    //Observing any data changed
    private void ObserveAnyChange(){
        wishlistViewModel.getWishlists().observe(this, new Observer<List<WishlistModel>>() {
            @Override
            public void onChanged(List<WishlistModel> wishlistModels) {
                //Observing for any data change

            }
        });
    }
}
