package com.example.mobileapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class WishlistModel {

    private long id;

    private String name;

    private String username;

    public WishlistModel(long id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    protected WishlistModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
    }

   /* public static final Creator<WishlistModel> CREATOR = new Creator<WishlistModel>() {
        @Override
        public WishlistModel createFromParcel(Parcel in) {
            return new WishlistModel(in);
        }

        @Override
        public WishlistModel[] newArray(int size) {
            return new WishlistModel[size];
        }
    };*/

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    /*@Override
    public int describeContents() {
        return 0;
    }*/

   /* @Override
    public void writeToParcel(Parcel parcel, long i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(username);
    }*/
}
