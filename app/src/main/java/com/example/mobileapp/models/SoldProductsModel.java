package com.example.mobileapp.models;

import java.text.SimpleDateFormat;

public class SoldProductsModel {

    private long id;
    private long productId;
    private String date;
    private int Quantity;
    private String storeName;

    public SoldProductsModel(long id, long productId, String date, int quantity, String storeName) {
        this.id = id;
        this.productId = productId;
        this.date = date;
        Quantity = quantity;
        this.storeName = storeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
