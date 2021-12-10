package com.example.mobileapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class WishlistModel {

    private int id;

    private String name;

    private String profileId;

    public WishlistModel(String name, String profileId) {
        this.name = name;
        this.profileId = profileId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileId() {
        return profileId;
    }
}
