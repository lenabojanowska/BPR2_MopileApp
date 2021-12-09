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
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.viewmodels.AllProductsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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



        //Toast.makeText(this, "name: " + name, Toast.LENGTH_LONG).show();

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

      /*  Bundle bundle = new Bundle();
        bundle.putString("name", productList.get(position).getName());
        bundle.putString("category", productList.get(position).getCategory());
        bundle.putString("brand",  productList.get(position).getBrand());
        bundle.putDouble("price",  productList.get(position).getPrice());*/

        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("name", productList.get(position).getName());
        intent.putExtra("category", productList.get(position).getCategory());
        intent.putExtra("brand",  productList.get(position).getBrand());
        intent.putExtra("price",  productList.get(position).getPrice());
        startActivity(intent);
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SearchView searchView = findViewById(R.id.allProductsSearchView);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }*/

   /*  private void openDialog() {
        StoreFragment storeFragment = new StoreFragment();
        storeFragment.show(getSupportFragmentManager(), "store fragment");

    }*/

   /* private void fetchJson() {
        StoreApi storeApi = ServiceGenerator.getStoreApi();
        Call<List<StoreModel>> call = storeApi.getStores();
        call.enqueue(new Callback<List<StoreModel>>() {
            @Override
            public void onResponse(Call<List<StoreModel>> call, Response<List<StoreModel>> response) {
                //Log.i("Responsestring", response.body());
                //Toast.makeText()
                if (response.isSuccessful()) {

                        Log.i("onSuccess", response.body().toString());

                            //String jsonResponse = response.body();
                            storeList = new ArrayList<StoreModel>();
                            //JSONArray jsonArray = new JSONArray(jsonResponse);
                            storeList.add(new StoreModel(-1, "Select Store"));
                            for(int i=0; i<storeList.size();i++){
                                StoreModel storeModel = new StoreModel();
                                //JSONObject jsonObject = jsonArray.getJSONObject(i);
                                storeModel.setId(storeModel.getId());
                                storeModel.setName(storeModel.getName());
                                storeList.add(storeModel);

                            }
                            for (int i = 0; i<storeList.size();i++){
                                getStoreName.add(storeList.get(i).getName());
                            }
                            ArrayAdapter<String> newStoreName = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_spinner_item, getStoreName);
                            newStoreName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(newStoreName);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        //storeList.add(new StoreModel(-1,""));

                }
            }

            @Override
            public void onFailure(Call<List<StoreModel>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
*/
   /* private void spinJSON(String jsonResponse) {
        try{
            JSONObject obj = new JSONObject(jsonResponse);
            if(obj.optString("status").equals("true")){
                storeModelArrayList = new ArrayList<>();
                JSONArray dataArray  = obj.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++){

                    StoreModel storeModel = new StoreModel();
                    JSONObject dataObj = dataArray.getJSONObject(i);

                    storeModel.setName(dataObj.getString("name"));

                    storeModelArrayList.add(storeModel);
                }
                for (int i = 0; i < storeModelArrayList.size(); i++){
                    storeNames.add(storeModelArrayList.get(i).getName());
                    Log.v("Tg", "c3456" + storeNames.add(storeModelArrayList.get(i).getName().toString()));

                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, storeNames);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spinner.setAdapter(spinnerArrayAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/


    public void getDiaryProducts(View view) {

        /*Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra("id", 2);
        startActivity(intent);*/
        Log.d("TAG", "dziala czyli cos innego sie zjebalo");

    }

    public void getSweetsProducts(View view) {

        Intent intent = new Intent(this, BasketActivity.class);
        //intent.putExtra("id", 3);
        startActivity(intent);


    }
}