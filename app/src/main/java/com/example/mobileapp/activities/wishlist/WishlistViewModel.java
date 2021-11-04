package com.example.mobileapp.activities.wishlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.repositories.WishlistRepository;

import java.util.List;

public class WishlistViewModel extends ViewModel {

    private WishlistRepository wishlistRepository;

    public WishlistViewModel() {
        wishlistRepository = WishlistRepository.getInstance();
    }

    public LiveData<List<WishlistModel>> getWishlists(){ return wishlistRepository.getWishlists(); }
}
