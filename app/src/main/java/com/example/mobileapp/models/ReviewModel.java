package com.example.mobileapp.models;

public class ReviewModel {

    private long id;
    private String username;
    private int rating;
    private String comment;
    private String storeName;

    public ReviewModel(String username, int rating, String comment, String storeName) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.storeName = storeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
