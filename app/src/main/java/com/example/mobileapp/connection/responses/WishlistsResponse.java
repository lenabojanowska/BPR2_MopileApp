package com.example.mobileapp.connection.responses;

import androidx.annotation.NonNull;

import com.example.mobileapp.models.WishlistModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WishlistsResponse implements Serializable {

    @SerializedName("Wishlists")
    @Expose()
    private WishlistModel wishlists;

    public WishlistModel getWishlists() { return wishlists; }


  /*  @Override
    public String toString() {
        return "WishlistsResponse{" +
                "wishlists=" + wishlists +
                '}';
    }*/
}
