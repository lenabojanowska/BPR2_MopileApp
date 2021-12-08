package com.example.mobileapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.NewsletterApi;
import com.example.mobileapp.models.NewsletterModel;
import com.example.mobileapp.models.ProductModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsletterRepository {

    private static NewsletterRepository instance;

    private final MutableLiveData<List<NewsletterModel>> mNewsletters;

    public static NewsletterRepository getInstance(){
        if(instance == null) {
            instance = new NewsletterRepository();
        }
        return instance;
    }

    public NewsletterRepository() {
        mNewsletters = new MutableLiveData<>();
    }

    public LiveData<List<NewsletterModel>> getNewsletters(){
        return mNewsletters;
    }

    public void GetNewsletters(){
        NewsletterApi newsletterApi = ServiceGenerator.getNewsletterApi();

        Call<List<NewsletterModel>> call = newsletterApi.getNewsletters();
        call.enqueue(new Callback<List<NewsletterModel>>() {
            @Override
            public void onResponse(Call<List<NewsletterModel>> call, Response<List<NewsletterModel>> response) {
                if(response.isSuccessful()){
                    mNewsletters.setValue(response.body());
                    Log.v("Tag", "lalalalalala " + response.body().toString());
                }else{
                    try {
                        mNewsletters.postValue(null);
                        Log.v("Tag", "Error " + response.errorBody().string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    Log.v("Tag", "Error " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<NewsletterModel>> call, Throwable t) {
                Log.v("Tag", "helplololkjh"+t.toString());
            }
        });
    }

}
