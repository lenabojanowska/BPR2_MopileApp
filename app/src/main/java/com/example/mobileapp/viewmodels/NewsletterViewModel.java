package com.example.mobileapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.NewsletterModel;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.repositories.NewsletterRepository;
import com.example.mobileapp.repositories.ProductRepository;

import java.util.List;

public class NewsletterViewModel extends ViewModel {

    private NewsletterRepository newsletterRepository;

    public NewsletterViewModel(){
        newsletterRepository = NewsletterRepository.getInstance();
    }

    public LiveData<List<NewsletterModel>> getNewsletters(){
        return newsletterRepository.getNewsletters();
    }

    public void GetNewsletters(){
        newsletterRepository.GetNewsletters();
    }
}
