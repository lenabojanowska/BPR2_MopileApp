package com.example.mobileapp.connection.apis;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.ReviewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReviewApi {
    @GET("api/Reviews")
    Call<List<ReviewModel>> getReviews();

    @POST("api/Reviews")
    Call<ReviewModel> postReview(@Body ReviewModel reviewModel);
}
