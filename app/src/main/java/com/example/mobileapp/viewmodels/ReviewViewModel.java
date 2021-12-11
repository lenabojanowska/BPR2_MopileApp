package com.example.mobileapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileapp.models.ReviewModel;
import com.example.mobileapp.repositories.ReviewRepository;

import java.util.List;

public class ReviewViewModel extends ViewModel {

    private ReviewRepository reviewRepository;

    public ReviewViewModel(){ reviewRepository = ReviewRepository.getInstance(); }

    public LiveData<List<ReviewModel>> getReviews(){
        return reviewRepository.getReviews();
    }

    public LiveData<ReviewModel> postReview(){
        return reviewRepository.postReview();
    }

    public void getReviewList(){ reviewRepository.getReviewList(); }

    public void postReview(String username, int rating, String comment, String storeName){
        reviewRepository.postReview(username, rating, comment,storeName);
    }


}
