package com.example.mobileapp.connection.apis;

import com.example.mobileapp.connection.responses.WishlistsResponse;
import com.example.mobileapp.models.WishlistModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WishlistApi {
    @GET("Wishlists")
    Call<List<WishlistModel>> getWishList();

    @GET("customerWishLists/{customerId}")
    Call<List<WishlistModel>> getWishListsByCustomerID(@Path("customerId") String profileId);

    @POST("api/Wishlists")
    Call<WishlistModel> postWishlist(@Body WishlistModel wishlistModel);

    @DELETE("api/Wishlists/{id}")
    Call<Void> deleteWishlistById(@Path("id") long id);



}
