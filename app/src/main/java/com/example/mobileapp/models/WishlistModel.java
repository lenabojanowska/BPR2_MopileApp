package com.example.mobileapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class WishlistModel {

    private long id;

    private String name;

    private String username;

    public WishlistModel(String name, String username) {
        this.name = name;
        this.username = username;
    }

    protected WishlistModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
