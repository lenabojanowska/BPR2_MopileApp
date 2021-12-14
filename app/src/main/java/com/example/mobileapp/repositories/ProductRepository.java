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

    private final MutableLiveData<ProductModel> mProduct;

    public static ProductRepository getInstance(){
        if(instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public ProductRepository() {
        mProduct = new MutableLiveData<>();
    }

    public LiveData<ProductModel> getProductListById(){
        return mProduct;
    }

    public void getProductById(long id){
        ProductApi productApi = ServiceGenerator.getProductApi();
        Call<ProductModel> call = productApi.getProductById(id);
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
               if(response.isSuccessful()){
                   mProduct.setValue(response.body());
                   String name = response.body().getName();
                   Log.v("Tag", "name  " + name);

                   Log.v("Tag", "Successful " + response.body());
               }else{
                   Log.v("Tag", "Errorsaasxasas " + response.errorBody());
               }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.v("Tag", t.toString() + "Errorsaasxasas " );
            }
        });
    }

    public void getProductByBarcode(long barcode){
        ProductApi productApi = ServiceGenerator.getProductApi();
        Call<ProductModel> call = productApi.getProductByBarcode(barcode);
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                if(response.isSuccessful()){
                    mProduct.setValue(response.body());
                    String name = response.body().getName();
                    Log.v("Tag", "name  " + name);

                    Log.v("Tag", "Successful " + response.body());
                }else{
                    Log.v("Tag", "Errorsaasxasas " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.v("Tag", t.toString() + "Errorsaasxasas " );
            }
        });
    }

}
