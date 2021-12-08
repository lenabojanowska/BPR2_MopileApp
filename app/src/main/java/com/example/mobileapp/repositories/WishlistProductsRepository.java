package com.example.mobileapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.ProductApi;
import com.example.mobileapp.models.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistProductsRepository {

    private static WishlistProductsRepository instance;

    private final MutableLiveData<List<ProductModel>> mProduct;

    public static WishlistProductsRepository getInstance(){
        if(instance == null) {
            instance = new WishlistProductsRepository();
        }
        return instance;
    }

    public WishlistProductsRepository() {
        mProduct = new MutableLiveData<>();
    }

    public LiveData<List<ProductModel>> getProductsOnWishlist(){
        return mProduct;
    }

    public void GetProductsOnWishlist(int wishlistId){
        ProductApi productApi = ServiceGenerator.getProductApi();
        Call<List<ProductModel>> call = productApi.getProductsOnWishlist(wishlistId);
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if(response.isSuccessful()){
                    mProduct.setValue(response.body());
                    Log.v("Tag", "lalalalalala " + response.body());
                }else{
                    Log.v("Tag", "Error " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Log.v("Tag", "helplololkjh"+t.toString());
            }
        });

    }
}
