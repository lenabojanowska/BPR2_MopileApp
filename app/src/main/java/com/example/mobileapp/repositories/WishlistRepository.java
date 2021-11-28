package com.example.mobileapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.WishlistApi;
import com.example.mobileapp.connection.responses.WishlistsResponse;
import com.example.mobileapp.models.WishlistModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistRepository {

    private static WishlistRepository instance;

    private final MutableLiveData<List<WishlistModel>> mWishlist;

    public static WishlistRepository getInstance(){
        if(instance == null) {
            instance = new WishlistRepository();
        }
        return instance;
    }

    public WishlistRepository() {
        mWishlist = new MutableLiveData<>();
    }

    public LiveData<List<WishlistModel>> getWishlistList(){
        return mWishlist;
    }

    public void GetWishlistList() {

        WishlistApi wishlistApi = ServiceGenerator.getWishListApi();
        Call<List<WishlistModel>> call = wishlistApi.getWishList();
        call.enqueue(new Callback<List<WishlistModel>>() {

            @Override
            public void onResponse(Call<List<WishlistModel>> call, Response<List<WishlistModel>> response) {
                if(response.isSuccessful()){
                    Log.v("Tag", "--------//--------");
                    Log.v("Tag", "Getting Wishlists");
                    Log.v("Tag", "The Wishlist list:");
                    //Log.v("Tag", "the response " + response.body().toString());

                    mWishlist.setValue(response.body());
                    /*List<WishlistModel> wishlists = response.body();

                    for(WishlistModel wishlistModel: wishlists) {


                        Log.v("Tag", "The Wishlist " + wishlistModel.getName());
                    }*/
                }
                else {
                    try {
                        mWishlist.postValue(null);
                        Log.v("Tag", "Error " + response.errorBody().string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<WishlistModel>> call, Throwable t) {

                Log.v("Tag", t.toString(),t.getCause());
                t.getStackTrace();
            }
        });

    }

    public void CallRetrofit(String postName, String userName) {

       WishlistApi wishlistApi = ServiceGenerator.getWishListApi();
        WishlistModel wishlistModel = new WishlistModel(postName, userName);

        Call<WishlistModel> call = wishlistApi.postWishlist(wishlistModel);

        call.enqueue(new Callback<WishlistModel>() {
            @Override
            public void onResponse(Call<WishlistModel> call, Response<WishlistModel> response) {
                Log.v("Tag", "--------//--------");
                Log.v("Tag", "Posting Wishlist");
                Log.v("Tag", "The Wishlist posted");
                Log.v("Tag", "The Wishlist " + response.body().getName());
                GetWishlistList();
            }

            @Override
            public void onFailure(Call<WishlistModel> call, Throwable t) { Log.v("Tag", t.toString());
            }
        });

    }

    /*public void DeleteRetrofitResponse(String id) {
        WishlistApi wishlistApi = ServiceGenerator.getWishListApi();
        Call<Void> call = wishlistApi.deleteWishlistById(Integer.parseInt(id));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.v("Tag", "--------//--------");
                Log.v("Tag", "Deleting wishlist by id ");
                Log.v("Tag", "Deleted " + response.code());
                Log.v("Tag", "Deleted Wishlist with " + id + "id");
                GetRetrofitResponse();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.v("Tag", t.toString());
            }
        });
    }*/

}
