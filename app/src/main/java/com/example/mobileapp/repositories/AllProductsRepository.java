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

public class AllProductsRepository {

        private static AllProductsRepository instance;

        private final MutableLiveData<List<ProductModel>> mProducts;


        public static AllProductsRepository getInstance(){
            if(instance == null) {
                instance = new AllProductsRepository();
            }
            return instance;
        }

        public AllProductsRepository() {

            mProducts = new MutableLiveData<>();

        }

        public LiveData<List<ProductModel>> getProducts(){
            return mProducts;
        }

        public void getAllProducts(){
            ProductApi productApi = ServiceGenerator.getProductApi();
            Call<List<ProductModel>> call = productApi.getProducts();
            call.enqueue(new Callback<List<ProductModel>>() {
                @Override
                public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                    if(response.isSuccessful()){
                        mProducts.setValue(response.body());
                    }else{
                        Log.v("Tag", "ErrorasADSADASDS " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                    Log.v("Tag", "ErrorasADSADASDS " + t.toString());
                }
            });
        }


}
