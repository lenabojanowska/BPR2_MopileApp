package com.example.mobileapp.fragments.profile.review;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.search.adapter.SearchAdapter;
import com.example.mobileapp.fragments.profile.review.adapter.ReviewAdapter;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.ReviewModel;
import com.example.mobileapp.viewmodels.AllProductsViewModel;
import com.example.mobileapp.viewmodels.ReviewViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyReviewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;

    private ReviewViewModel reviewViewModel;

    private List<ReviewModel> reviewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_reviews, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.reviewRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        reviewAdapter = new ReviewAdapter(reviewList, this.getContext());
        recyclerView.setAdapter(reviewAdapter);
        reviewList = new ArrayList<>();

        reviewViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);
        reviewViewModel.getReviews().observe(getViewLifecycleOwner(), new Observer<List<ReviewModel>>() {
            @Override
            public void onChanged(List<ReviewModel> reviewModels) {
                if(reviewModels != null){
                    reviewList = reviewModels;
                    reviewAdapter.setAllReviews(reviewModels);
                }

            }
        });
        reviewViewModel.getReviewList();

        return view;
    }
}