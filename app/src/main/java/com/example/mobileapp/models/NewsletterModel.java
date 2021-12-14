package com.example.mobileapp.models;

import java.util.Date;

public class NewsletterModel {

    private int id;
    private String title;
    private String details;
    private long validFromMiliseconds;
    private long validToMiliseconds;


    public NewsletterModel(int id, String title, String details, long validFromMiliseconds, long validToMiliseconds) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.validFromMiliseconds = validFromMiliseconds;
        this.validToMiliseconds = validToMiliseconds;
    }

    public long getValidFromMiliseconds() {
        return validFromMiliseconds;
    }

    public void setValidFromMiliseconds(long validFromMiliseconds) {
        this.validFromMiliseconds = validFromMiliseconds;
    }

    public long getValidToMiliseconds() {
        return validToMiliseconds;
    }

    public void setValidToMiliseconds(long validToMiliseconds) {
        this.validToMiliseconds = validToMiliseconds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        details = details;
    }
}
