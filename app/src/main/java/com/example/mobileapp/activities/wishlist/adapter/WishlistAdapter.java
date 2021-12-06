package com.example.mobileapp.activities.wishlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.models.WishlistModel;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<WishlistModel> wishlistList;
    private Context context;

    private OnWishlistListener onWishlistListener;

    public WishlistAdapter(List<WishlistModel> wishlistList, Context context, OnWishlistListener onWishlistListener) {
        this.wishlistList = wishlistList;
        this.context = context;
        this.onWishlistListener = onWishlistListener;
    }

    public void setWishlistList(List<WishlistModel> wishlistList){
        this.wishlistList = wishlistList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wishlist_item, parent, false);
        return new ViewHolder(view, onWishlistListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.wishlistName.setText(this.wishlistList.get(position).getName());

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
                WishlistFragment wishlistFragment = new WishlistFragment();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.wishlistView, wishlistFragment).addToBackStack(null).commit();

            }
        });*/
    }

    @Override
    public int getItemCount() {
        if(this.wishlistList != null){
            return this.wishlistList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView wishlistName;
        OnWishlistListener onWishlistListener;

        public ViewHolder(@NonNull View itemView, OnWishlistListener onWishlistListener) {
            super(itemView);
            wishlistName = (TextView)  itemView.findViewById(R.id.wishlistName);
            this.onWishlistListener = onWishlistListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onWishlistListener.onWishlistClick(getAdapterPosition());
        }
    }

    public interface OnWishlistListener{
        void onWishlistClick(int position);
    }
}
