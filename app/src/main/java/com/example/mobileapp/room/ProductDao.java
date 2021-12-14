package com.example.mobileapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobileapp.models.ProductModel;

import java.lang.reflect.Modifier;
import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(ProductModel productModel);

    @Delete
    void deleteProduct(ProductModel productModel);

    @Query("DELETE FROM product_table")
    void deleteAllProducts();

    @Query("SELECT * FROM product_table")
    LiveData<List<ProductModel>> getAllProducts();


}
