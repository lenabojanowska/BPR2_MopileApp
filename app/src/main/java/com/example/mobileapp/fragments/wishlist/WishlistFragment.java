package com.example.mobileapp.fragments.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.product.ProductActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.adapter.WishlistAdapter;
import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.ProductApi;
import com.example.mobileapp.connection.apis.WishlistApi;
import com.example.mobileapp.fragments.store.adapter.StoreAdapter;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.StoreModel;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.viewmodels.StoreViewModel;
import com.example.mobileapp.viewmodels.WishlistViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistFragment extends DialogFragment implements WishlistAdapter.OnWishlistListener {

    private WishlistViewModel wishlistViewModel;

    private RecyclerView recyclerView;

    private WishlistAdapter adapter;

    private List<WishlistModel> wishlistList;

    private FirebaseAuth firebaseAuth;

    private ProductApi productApi;

    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wishlist_fragment, container);

        toolbar = view.findViewById(R.id.wishlistToolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String id = firebaseUser.getUid();


        recyclerView = (RecyclerView) view.findViewById(R.id.wishlistDialogRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new WishlistAdapter(wishlistList, this.getActivity(), (WishlistAdapter.OnWishlistListener) this);
        recyclerView.setAdapter(adapter);
        wishlistList = new ArrayList<>();

        wishlistViewModel = ViewModelProviders.of(this.getActivity()).get(WishlistViewModel.class);
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

        //Toast.makeText(this.getActivity(), "id: " + pId, Toast.LENGTH_LONG).show();

        return  view;
    }

    @Override
    public void onWishlistClick(int position) {

        long wId = getArguments().getLong("wId", 2);
        long wBarcode = getArguments().getLong("wBarcode", 2);
        String wName = getArguments().getString("wName");
        String wCat = getArguments().getString("wCat");
        double wPrice = getArguments().getDouble("wPrice", 0);
        String wBrand = getArguments().getString("wBrand");


        int id = wishlistList.get(position).getId();

        Toast.makeText(this.getActivity(), "id: " + wId + " " + wBrand+ " " + wBarcode+ " " + wName+ " " + wCat+ " " + wPrice, Toast.LENGTH_LONG).show();

        ProductApi productApi = ServiceGenerator.getProductApi();
        ProductModel productModel = new ProductModel(wId, wBarcode, wName, wCat, wPrice, wBrand);
        Call<ProductModel> call = productApi.postProductOnWishlist(id, wId, productModel);

        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                Log.v("Tag", "Product added " + response.body().getName());
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.v("Tag", "Error adding "+ t.toString());
            }
        });


    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){

        }
        return super.onOptionsItemSelected(item);
    }


}

