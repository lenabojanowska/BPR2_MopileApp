package com.example.mobileapp.connection.apis;

import com.example.mobileapp.models.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApi {

    @GET("Products/{id}")
    Call<List<ProductModel>> getProductListByCategory(@Query("id") long id);

    @GET("wishListProducts")
    Call<List<ProductModel>> getProductsOnWishlist(@Query("wishListId") long wishlistId);

    @GET("api/Products")
    Call<List<ProductModel>> getProducts();



}
