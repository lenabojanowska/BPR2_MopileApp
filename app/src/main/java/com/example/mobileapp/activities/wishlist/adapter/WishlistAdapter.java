package com.example.mobileapp.activities.wishlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.models.WishlistModel;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<WishlistModel> wishlistList;
    private Context context;

    public WishlistAdapter(List<WishlistModel> wishlistList, Context context) {
        this.wishlistList = wishlistList;
        this.context = context;
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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.wishlistName.setText(this.wishlistList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(this.wishlistList != null){
            return this.wishlistList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView wishlistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            wishlistName = (TextView)  itemView.findViewById(R.id.wishlistName);
        }
    }
}
