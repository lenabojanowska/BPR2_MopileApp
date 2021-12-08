package com.example.mobileapp.viewmodels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.StoreModel;
import com.example.mobileapp.repositories.StoreRepository;

import java.util.List;

public class StoreViewModel extends ViewModel {

    private StoreRepository storeRepository;

    public StoreViewModel() {
        storeRepository = StoreRepository.getInstance();
    }

    public LiveData<List<StoreModel>> getStores(){
        return storeRepository.getStores();
    }

    public void GetStores(){
        storeRepository.GetStores();
    }

}
