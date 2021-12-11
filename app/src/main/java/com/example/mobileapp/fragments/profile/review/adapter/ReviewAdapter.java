package com.example.mobileapp.fragments.profile.review.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.search.adapter.SearchAdapter;
import com.example.mobileapp.models.ReviewModel;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<ReviewModel> reviewList;
    private Context context;

    public ReviewAdapter(List<ReviewModel> reviewList, Context context){
        this.reviewList = reviewList;
        this.context = context;
    }

    public void setAllReviews(List<ReviewModel> reviewList){
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.storeName.setText(this.reviewList.get(position).getStoreName());
        holder.comment.setText(this.reviewList.get(position).getComment());
        holder.rating.setText(String.valueOf(this.reviewList.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        if (this.reviewList != null) {
            return this.reviewList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView storeName, comment, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storeName = (TextView) itemView.findViewById(R.id.storeNameReview);
            comment = (TextView) itemView.findViewById(R.id.comment);
            rating = (TextView) itemView.findViewById(R.id.rating);

        }
    }
}
