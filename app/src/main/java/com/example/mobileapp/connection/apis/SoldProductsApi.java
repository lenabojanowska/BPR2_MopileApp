package com.example.mobileapp.connection.apis;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.SoldProductsModel;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SoldProductsApi {

    @POST("api/soldProducts/{store}/{productId}/{time}/{quantity}")
    Call<ProductModel> postPurchasedProducts(@Path("store") String storeName, @Path("productId") long productId, @Path("time") String date, @Path("quantity") int quantity);
}
