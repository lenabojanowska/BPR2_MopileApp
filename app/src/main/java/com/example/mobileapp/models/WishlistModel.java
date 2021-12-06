package com.example.mobileapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class WishlistModel {

    private long id;

    private String name;

    private String profileId;

    public WishlistModel(String name, String profileId) {
        this.name = name;
        this.profileId = profileId;
    }

    public WishlistModel(String name) {
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileId() {
        return profileId;
    }
}
