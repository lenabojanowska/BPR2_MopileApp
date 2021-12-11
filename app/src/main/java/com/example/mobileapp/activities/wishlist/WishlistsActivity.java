package com.example.mobileapp.activities.wishlist;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mobileapp.activities.main.NewsletterActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.storeProduct.StoreProductActivity;
import com.example.mobileapp.activities.wishlist.adapter.WishlistAdapter;
import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.WishlistApi;
import com.example.mobileapp.fragments.wishlist.AddWishlistFragment;
import com.example.mobileapp.fragments.wishlist.WishlistFragment;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.viewmodels.WishlistViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private FloatingActionButton floatingActionButton;

    private String wishlistText;

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

        floatingActionButton = findViewById(R.id.addWishlistFloatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wishlistAlertDialog(v);
                /*AddWishlistFragment addWishlistFragment = new AddWishlistFragment();
                addWishlistFragment.show(getSupportFragmentManager(), "addWishlist fragment");*/
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

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
                        startActivity(new Intent(getApplicationContext(), NewsletterActivity.class));
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

    WishlistModel deletedWishlist = null;

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            String uid = firebaseUser.getUid();

            int position = viewHolder.getAdapterPosition();
            long id = wishlistList.get(position).getId();
            String name = wishlistList.get(position).getName();
            String profileId = wishlistList.get(position).getProfileId();

            switch (direction){
                case ItemTouchHelper.LEFT:
                    deletedWishlist = wishlistList.get(position);
                    wishlistList.remove(position);
                    adapter.notifyItemRemoved(position);
                    WishlistModel wishlistModel = new WishlistModel(name, profileId);
                    wishlistViewModel.deleteWishlist(id, wishlistModel);

                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(WishlistsActivity.this, R.color.colorAccent)).create().decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    public void wishlistAlertDialog(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Put the name of a new wishlist");
        final EditText wishlistName = new EditText(WishlistsActivity.this);
        wishlistName.setInputType(InputType.TYPE_CLASS_TEXT);
        alert.setView(wishlistName);


        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                wishlistText = wishlistName.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String id = firebaseUser.getUid();

                WishlistApi wishlistApi = ServiceGenerator.getWishListApi();
                WishlistModel wishlistModel = new WishlistModel(wishlistText, id);

                Call<WishlistModel> call = wishlistApi.postWishlist(wishlistModel);

                call.enqueue(new Callback<WishlistModel>() {
                    @Override
                    public void onResponse(Call<WishlistModel> call, Response<WishlistModel> response) {
                        Log.v("Tag", "--------//--------");
                        Log.v("Tag", "Posting Wishlist");
                        Log.v("Tag", "The Wishlist posted");
                        Log.v("Tag", "The Wishlist " + response.body().getName());

                        //mWishlist.setValue((List<WishlistModel>) response.body());
                    }

                    @Override
                    public void onFailure(Call<WishlistModel> call, Throwable t) { Log.v("Tag", t.toString());
                    }
                });
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.create().show();
        AlertDialog alertDialog = alert.create();
        alertDialog.getWindow().setLayout(200, 300);

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
