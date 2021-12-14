package com.example.mobileapp.activities.storeProduct;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.main.NewsletterActivity;
import com.example.mobileapp.activities.product.ProductActivity;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.search.adapter.SearchAdapter;
import com.example.mobileapp.activities.storeProduct.adapter.StoreProductAdapter;
import com.example.mobileapp.activities.wishlist.WishlistsActivity;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.StoreModel;
import com.example.mobileapp.repositories.AllProductsRepository;
import com.example.mobileapp.viewmodels.AllProductsViewModel;
import com.example.mobileapp.viewmodels.StoreViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StoreProductActivity extends AppCompatActivity implements StoreProductAdapter.OnProductListener {

    BottomNavigationView bottomNavigationView;
    private AllProductsViewModel allProductsViewModel;

    private RecyclerView recyclerView;
    private StoreProductAdapter adapter;

    private List<ProductModel> productList;
    private SearchView searchView;

    private TextView sName, sAddress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_product);

        sName = findViewById(R.id.onStoreName);
        sAddress = findViewById(R.id.onStoreAddress);

        Intent intent = getIntent();
        long storeId = intent.getLongExtra("storeId", 0);
        String storeName = intent.getStringExtra("storeName");
        String storeAddress = intent.getStringExtra("storeAddress");

        sName.setText(storeName);
        sAddress.setText(storeAddress);

        searchView = findViewById(R.id.onStoreSearchView);
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

        recyclerView = (RecyclerView) findViewById(R.id.productOnStoreRecyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new StoreProductAdapter(productList, this,this);
        recyclerView.setAdapter(adapter);
        productList = new ArrayList<>();

        allProductsViewModel = ViewModelProviders.of(this).get(AllProductsViewModel.class);
        allProductsViewModel.getProducts().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                productList = productModels;
                adapter.setAllProductsList(productModels);
            }
        });
        allProductsViewModel.getProductsOnStore(storeId);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.search);
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
                        startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
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
        adapter.filterList(filteredList);
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

        /*WishlistFragment fragment = new WishlistFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("wId", productList.get(position).getId());
        bundle.putLong("wBarcode", productList.get(position).getBarcode());
        bundle.putString("wName", productList.get(position).getName());
        bundle.putString("wCat", productList.get(position).getCategory());
        bundle.putDouble("wPrice", productList.get(position).getPrice());
        bundle.putString("wBrand", productList.get(position).getBrand());
        fragment.setArguments(bundle);*/

        startActivity(intent);

    }
}
