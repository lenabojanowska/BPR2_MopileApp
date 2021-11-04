package com.example.mobileapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileapp.models.WishlistModel;

import java.util.List;

public class WishlistRepository {

    private static WishlistRepository instance;

    private MutableLiveData<List<WishlistModel>> mWishlists;

    public static WishlistRepository getInstance(){
        if(instance == null) {
            instance = new WishlistRepository();
        }
        return instance;
    }

    private WishlistRepository() {
        mWishlists = new MutableLiveData<>();
    }

    public LiveData<List<WishlistModel>> getWishlists(){ return mWishlists; }
}
