package com.example.mobileapp.repositories;

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

    private final MutableLiveData<SoldProductsModel> mSoldProduct;

    public static SoldProductsRepository getInstance(){
        if(instance == null) {
            instance = new SoldProductsRepository();
        }
        return instance;
    }

    public SoldProductsRepository() {
        mSoldProduct = new MutableLiveData<>();
    }

    public LiveData<SoldProductsModel> soldProducts(){
        return mSoldProduct;
    }

    public void postSoldProducts(String storeName, long pId, int quantity){
        /*SoldProductsApi soldProductsApi = ServiceGenerator.getSoldProductsApi();
        Call<ProductModel> call = soldProductsApi.postPurchasedProducts(storeName, pId, quantity);
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                //mSoldProduct.setValue(response.body().getId());
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {

            }
        });*/

    }
}
