package com.example.mobileapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.ProductApi;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.viewmodels.ProductViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private static ProductRepository instance;

    private final MutableLiveData<List<ProductModel>> mProduct;

    public static ProductRepository getInstance(){
        if(instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public ProductRepository() {
        mProduct = new MutableLiveData<>();
    }

    public LiveData<List<ProductModel>> getProductListByCat(){
        return mProduct;
    }

    public void getProductListByCategory(long id){
        ProductApi productApi = ServiceGenerator.getProductApi();
        Call<List<ProductModel>> call = productApi.getProductListByCategory(id);
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
               if(response.isSuccessful()){
                   mProduct.setValue(response.body());
               }else{
                   Log.v("Tag", "Errorsaasxasas " + response.errorBody());
               }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });
    }
}
