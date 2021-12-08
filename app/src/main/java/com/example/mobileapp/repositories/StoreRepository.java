package com.example.mobileapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.StoreApi;
import com.example.mobileapp.models.StoreModel;
import com.example.mobileapp.models.WishlistModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreRepository {

    private static StoreRepository instance;

    private final MutableLiveData<List<StoreModel>> mStore;

    public static StoreRepository getInstance(){
        if(instance == null) {
            instance = new StoreRepository();
        }
        return instance;
    }

    public StoreRepository() {
        mStore = new MutableLiveData<>();
    }

    public LiveData<List<StoreModel>> getStores(){
        return mStore;
    }

    public void GetStores(){
        StoreApi storeApi = ServiceGenerator.getStoreApi();
        Call<List<StoreModel>> call = storeApi.getStores();
        call.enqueue(new Callback<List<StoreModel>>() {
            @Override
            public void onResponse(Call<List<StoreModel>> call, Response<List<StoreModel>> response) {
                if(response.isSuccessful()){

                    Log.v("Tag", "the response " + response.body().toString());

                    mStore.setValue(response.body());

                }
                else {
                    try {
                        mStore.postValue(null);
                        Log.v("Tag", "Errordserdfgth " + response.errorBody().string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<StoreModel>> call, Throwable t) {
                Log.v("Tag", "problem" + t.toString(),t.getCause());
                t.getStackTrace();
            }
        });
    }
}
