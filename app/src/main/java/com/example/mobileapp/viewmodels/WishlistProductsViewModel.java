package com.example.mobileapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.repositories.WishlistProductsRepository;

import java.util.List;

public class WishlistProductsViewModel extends ViewModel {

    private WishlistProductsRepository wishlistProductsRepository;

    public WishlistProductsViewModel(){
        wishlistProductsRepository = WishlistProductsRepository.getInstance();
    }

    public LiveData<List<ProductModel>> getProductsOnWishlist(){
        return wishlistProductsRepository.getProductsOnWishlist();
    }

    public void GetProductsOnWishlist(int wishlistId){
        wishlistProductsRepository.GetProductsOnWishlist(wishlistId);
    }
}
