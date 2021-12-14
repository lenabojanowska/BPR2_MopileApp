package com.example.mobileapp.connection.apis;

import com.example.mobileapp.models.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApi {

    @GET("api/Products/{id}")
    Call<ProductModel> getProductById(@Path("id") long id);

    @GET("wishListProducts/{wishListId}")
    Call<List<ProductModel>> getProductsOnWishlist(@Path("wishListId") long wishlistId);

    @GET("api/Products")
    Call<List<ProductModel>> getProducts();

    @POST("wishListProducts/{wishListId}/{productId}")
    Call<ProductModel> postProductOnWishlist(@Path("wishListId") int wishlistId, @Path("productId") long productId, @Body ProductModel productModel);

    @GET("storeProducts/{storeId}")
    Call<List<ProductModel>> getProductsOnStore(@Path("storeId") long storeId);

    @GET("barcode/{barcode}")
    Call<ProductModel> getProductByBarcode(@Path("barcode") long barcode);




}
