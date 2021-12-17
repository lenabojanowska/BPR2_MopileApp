package com.example.mobileapp.activities.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.mobileapp.activities.main.NewsletterActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.product.ProductActivity;
import com.example.mobileapp.activities.profile.ProfileActivity;

import com.example.mobileapp.activities.wishlist.WishlistProductsActivity;
import com.example.mobileapp.activities.wishlist.WishlistsActivity;
import com.example.mobileapp.activities.search.adapter.SearchAdapter;
import com.example.mobileapp.fragments.store.StoreFragment;
import com.example.mobileapp.fragments.storeProduct.StoreProductFragment;
import com.example.mobileapp.fragments.wishlist.WishlistFragment;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.viewmodels.AllProductsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnProductListener {

    BottomNavigationView bottomNavigationView;

    private AllProductsViewModel allProductsViewModel;

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    private List<ProductModel> productList;
    private SearchView searchView;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        floatingActionButton = findViewById(R.id.storeFloatingActionButton);

        searchView = findViewById(R.id.allProductsSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.allProductsRecyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        searchAdapter = new SearchAdapter(productList, this,this);
        recyclerView.setAdapter(searchAdapter);
        productList = new ArrayList<>();

        allProductsViewModel = ViewModelProviders.of(this).get(AllProductsViewModel.class);
        allProductsViewModel.getProducts().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                if(productModels != null){
                    productList = productModels;
                    searchAdapter.setAllProductsList(productModels);
                }

            }
        });
        allProductsViewModel.GetAllProducts();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreProductFragment storeFragment = new StoreProductFragment();

                storeFragment.show(getSupportFragmentManager(), "store fragment");
            }
        });


        //initialize and assign variable
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.search);
        //perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:

                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), NewsletterActivity.class));
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

    private void filter(String newText) {
        List<ProductModel> filteredList = new ArrayList<>();
        for(ProductModel item : productList){
            if(item.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        searchAdapter.filterList(filteredList);
    }

    @Override
    public void onProductClick(int position) {


        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("name", productList.get(position).getName());
        intent.putExtra("category", productList.get(position).getCategory());
        intent.putExtra("brand",  productList.get(position).getBrand());
        intent.putExtra("price",  productList.get(position).getPrice());
        intent.putExtra("pId", productList.get(position).getId());
        intent.putExtra("pBarcode", productList.get(position).getBarcode());


        startActivity(intent);

    }



}