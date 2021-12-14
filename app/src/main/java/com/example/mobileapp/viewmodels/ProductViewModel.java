package com.example.mobileapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.repositories.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {

    private ProductRepository productRepository;

    public ProductViewModel(){
        productRepository = ProductRepository.getInstance();
    }

    public LiveData<ProductModel> getProductById(){
        return productRepository.getProductListById();
    }

    public void getProductById(long id){
        productRepository.getProductById(id);
    }

    public void getProductByBarcode(long barcode){
        productRepository.getProductByBarcode(barcode);
    }

}
