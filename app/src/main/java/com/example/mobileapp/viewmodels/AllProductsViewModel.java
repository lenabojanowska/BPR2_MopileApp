package com.example.mobileapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.repositories.AllProductsRepository;
import com.example.mobileapp.repositories.ProductRepository;

import java.util.List;

public class AllProductsViewModel extends ViewModel {

    private AllProductsRepository allProductsRepository;

    public AllProductsViewModel(){
        allProductsRepository = AllProductsRepository.getInstance();
    }

    public LiveData<List<ProductModel>> getProducts(){
        return allProductsRepository.getProducts();
    }

    public void GetAllProducts(){
        allProductsRepository.getAllProducts();
    }

    public void getProductsOnStore(long id){
        allProductsRepository.getProductsOnStore(id);
    }
}
