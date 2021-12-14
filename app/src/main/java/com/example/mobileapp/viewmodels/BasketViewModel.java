package com.example.mobileapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.repositories.BasketRepository;

import java.util.List;

public class BasketViewModel extends AndroidViewModel {
    private BasketRepository basketRepository;
    private LiveData<List<ProductModel>> productList;

    public BasketViewModel(@NonNull Application application) {
        super(application);
        basketRepository = new BasketRepository(application);
        productList = basketRepository.getAllProducts();
    }

    public void insertProduct(ProductModel productModel){
        basketRepository.insertProduct(productModel);
    }

    public void deleteProduct(ProductModel productModel){
        basketRepository.deleteProduct(productModel);
    }

    public void deleteAllProducts(){
        basketRepository.deleteAllProducts();
    }

    public LiveData<List<ProductModel>> getAllProducts(){
        return productList;
    }
}
