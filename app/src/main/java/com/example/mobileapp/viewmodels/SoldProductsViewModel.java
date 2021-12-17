package com.example.mobileapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.SoldProductsModel;
import com.example.mobileapp.repositories.SoldProductsRepository;

public class SoldProductsViewModel extends ViewModel {

    private SoldProductsRepository soldProductsRepository;

    public SoldProductsViewModel(){ soldProductsRepository = SoldProductsRepository.getInstance();}

    public void postSoldProducts(String storeName, long pId, int quantity){
        soldProductsRepository.postSoldProducts(storeName, pId, quantity);
    }
}
