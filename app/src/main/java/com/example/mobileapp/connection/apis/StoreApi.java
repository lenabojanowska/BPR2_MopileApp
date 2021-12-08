package com.example.mobileapp.connection.apis;

import com.example.mobileapp.models.StoreModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StoreApi {

    @GET("api/Stores")
    Call<List<StoreModel>> getStores();

    /*@GET("api/Stores")
    Call<String> getStores();*/

}
