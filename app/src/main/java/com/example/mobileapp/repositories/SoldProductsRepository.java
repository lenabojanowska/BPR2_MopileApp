package com.example.mobileapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.SoldProductsApi;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.SoldProductsModel;
import com.example.mobileapp.models.StoreModel;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoldProductsRepository {

    private static SoldProductsRepository instance;

    public static SoldProductsRepository getInstance(){
        if(instance == null) {
            instance = new SoldProductsRepository();
        }
        return instance;
    }

    public void postSoldProducts(String storeName, long pId, int quantity){
        SoldProductsApi soldProductsApi = ServiceGenerator.getSoldProductsApi();
        Call<SoldProductsModel> call = soldProductsApi.postPurchasedProducts(storeName, pId, quantity);
        call.enqueue(new Callback<SoldProductsModel>() {
            @Override
            public void onResponse(Call<SoldProductsModel> call, Response<SoldProductsModel> response) {
                Log.v("Tag", "Post sold product");
            }
            @Override
            public void onFailure(Call<SoldProductsModel> call, Throwable t) {
                Log.v("Tag", "Failing post sold product" + t.toString());
            }
        });

    }
}
