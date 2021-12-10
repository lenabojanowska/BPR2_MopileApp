package com.example.mobileapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.repositories.WishlistRepository;

import java.util.List;

public class WishlistViewModel extends ViewModel {

    private WishlistRepository wishlistRepository;

   //MutableLiveData<List<WishlistModel>> postMutableLiveData = new MutableLiveData<>();

    public WishlistViewModel() {
        wishlistRepository = WishlistRepository.getInstance();
    }

    public LiveData<List<WishlistModel>> getWishlistList(){
        return wishlistRepository.getWishlistList();
    }

    public void GetProductListByCategory(String customerId){
        wishlistRepository.GetWishlistListByCustomerId(customerId);
    }

    public void GetRetrofitResponse(){
        wishlistRepository.GetWishlistList();
    }

    public void addWishlist(String n, String un){
        wishlistRepository.addWishlist(n, un);
    }

    public void deleteWishlist(long id, WishlistModel wishlistModel){
        wishlistRepository.deleteWishlist(id, wishlistModel);
    }
}
