package com.example.mobileapp.connection.apis;

import com.example.mobileapp.models.NewsletterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsletterApi {

    @GET("api/Newsletters")
    Call<List<NewsletterModel>> getNewsletters();
}
