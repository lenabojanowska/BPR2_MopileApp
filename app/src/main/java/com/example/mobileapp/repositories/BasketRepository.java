package com.example.mobileapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.room.ProductDao;
import com.example.mobileapp.room.BasketDatabase;

import java.util.List;

public class BasketRepository {

    private ProductDao productDao;
    private LiveData<List<ProductModel>> productList;

    public BasketRepository(Application application){
        BasketDatabase database = BasketDatabase.getInstance(application);
        productDao = database.productDao();
        productList = productDao.getAllProducts();

    }

    public void insertProduct(ProductModel productModel){
        new InsertNoteAsyncTask(productDao).execute(productModel);
    }

    public void deleteProduct(ProductModel productModel){
        new DeleteNoteAsyncTask(productDao).execute(productModel);
    }

    public void deleteAllProducts(){
        new DeleteAllProductsNoteAsyncTask(productDao).execute();
    }

    public LiveData<List<ProductModel>> getAllProducts(){
        return productList;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<ProductModel, Void, Void>{

        private ProductDao productDao;

        private InsertNoteAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(ProductModel... productModels) {
            productDao.insertProduct(productModels[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<ProductModel, Void, Void>{

        private ProductDao productDao;

        private DeleteNoteAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(ProductModel... productModels) {
            productDao.deleteProduct(productModels[0]);
            return null;
        }
    }

    private static class DeleteAllProductsNoteAsyncTask extends AsyncTask<Void, Void, Void>{

        private ProductDao productDao;

        private DeleteAllProductsNoteAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllProducts();
            return null;
        }
    }

}
