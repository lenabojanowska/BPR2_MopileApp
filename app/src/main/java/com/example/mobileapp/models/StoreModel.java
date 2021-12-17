package com.example.mobileapp.models;

import java.io.Serializable;

public class StoreModel implements Serializable {

    public long id;
    public String name;
    public String address;
    public String town;

    public StoreModel() {

    }

    public StoreModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String toString(){
        return name;
    }
}
