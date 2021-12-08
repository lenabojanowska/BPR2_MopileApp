package com.example.mobileapp.models;

import java.util.Date;

public class NewsletterModel {

    private int id;
    private String Title;
    private String Details;

    public NewsletterModel(int id, String title, String details) {
        this.id = id;
        Title = title;
        Details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
