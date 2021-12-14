package com.example.mobileapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class ProductModel {

    @PrimaryKey(autoGenerate = true)
    private int pId;

    private long id;
    private long barcode;
    private String name;
    private String category;
    private double price;
    private String brand;

    public ProductModel(long id, long barcode, String name, String category, double price, String brand) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.price = price;
        this.brand = brand;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
