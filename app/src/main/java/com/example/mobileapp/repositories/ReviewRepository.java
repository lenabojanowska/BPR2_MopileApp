package com.example.mobileapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.NewsletterApi;
import com.example.mobileapp.connection.apis.ProductApi;
import com.example.mobileapp.connection.apis.ReviewApi;
import com.example.mobileapp.models.NewsletterModel;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.ReviewModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewRepository {

    private static ReviewRepository instance;

    private final MutableLiveData<List<ReviewModel>> mReviews;

    private final MutableLiveData<ReviewModel> mReview;

    public static ReviewRepository getInstance(){
        if(instance == null) {
            instance = new ReviewRepository();
        }
        return instance;
    }

    public ReviewRepository() {
        mReviews = new MutableLiveData<>();
        mReview = new MutableLiveData<>();
    }

    public LiveData<List<ReviewModel>> getReviews(){
        return mReviews;
    }

    public LiveData<ReviewModel> postReview(){
        return mReview;
    }

    public void getReviewList(){
        ReviewApi reviewApi = ServiceGenerator.getReviewApi();

        Call<List<ReviewModel>> call = reviewApi.getReviews();
        call.enqueue(new Callback<List<ReviewModel>>() {
            @Override
            public void onResponse(Call<List<ReviewModel>> call, Response<List<ReviewModel>> response) {
                if(response.isSuccessful()){
                    mReviews.setValue(response.body());
                    Log.v("Tag", "lalalalalala " + response.body().toString());
                }else{
                    try {
                        mReviews.postValue(null);
                        Log.v("Tag", "Error " + response.errorBody().string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    Log.v("Tag", "Error " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<ReviewModel>> call, Throwable t) {
                Log.v("Tag", "helplololkjh"+t.toString());
            }
        });

    }

    public void postReview(String username, int rating, String comment, String storeName){
        ReviewApi reviewApi = ServiceGenerator.getReviewApi();
        ReviewModel reviewModel = new ReviewModel(username, rating, comment, storeName);
        Call<ReviewModel> call = reviewApi.postReview(reviewModel);
        call.enqueue(new Callback<ReviewModel>() {
           @Override
           public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
               Log.v("Tag", "Product added " + response.body().getId());
               mReview.setValue(response.body());
           }

           @Override
           public void onFailure(Call<ReviewModel> call, Throwable t) {

           }
       });
    }

/*    ProductApi productApi = ServiceGenerator.getProductApi();
    ProductModel productModel = new ProductModel(wId, wBarcode, wName, wCat, wPrice, wBrand);
    Call<ProductModel> call = productApi.postProductOnWishlist(id, wId, productModel);

        call.enqueue(new Callback<ProductModel>() {
        @Override
        public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
            Log.v("Tag", "Product added " + response.body().getName());
        }

        @Override
        public void onFailure(Call<ProductModel> call, Throwable t) {
            Log.v("Tag", "Error adding "+ t.toString());
        }
    });*/

}
