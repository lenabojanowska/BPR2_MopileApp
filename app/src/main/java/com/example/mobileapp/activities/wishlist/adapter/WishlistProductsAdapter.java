package com.example.mobileapp.activities.wishlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.models.ProductModel;

import java.util.List;

public class WishlistProductsAdapter extends RecyclerView.Adapter<WishlistProductsAdapter.ViewHolder>{

    private List<ProductModel> productList;
    private Context context;

    private OnWishlistProductListener onWishlistProductListener;

    public WishlistProductsAdapter(List<ProductModel> productList, Context context, OnWishlistProductListener onWishlistProductListener){
        this.productList = productList;
        this.context = context;
        this.onWishlistProductListener = onWishlistProductListener;
    }

    public void setWishlistList(List<ProductModel> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wishlist_product_item, parent, false);
        return new ViewHolder(view, onWishlistProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistProductsAdapter.ViewHolder holder, int position) {
        holder.productName.setText(this.productList.get(position).getName());
        holder.wishlistItemPrice.setText(String.valueOf(productList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        if(this.productList != null){
            return this.productList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView productName, wishlistItemPrice;
        OnWishlistProductListener onWishlistProductListener;

        public ViewHolder(@NonNull View itemView, OnWishlistProductListener onWishlistProductListener) {
            super(itemView);
            productName = (TextView)  itemView.findViewById(R.id.productOnWishlistName);
            wishlistItemPrice = (TextView) itemView.findViewById(R.id.wishlistItemPrice);
            this.onWishlistProductListener = onWishlistProductListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onWishlistProductListener.onWishlistProductListener(getAdapterPosition());
        }

       /* @Override
        public void onClick(View v) {
            onWishlistListener.onWishlistClick(getAdapterPosition());
        }*/
    }
    public interface OnWishlistProductListener{
        void onWishlistProductListener(int position);
    }
}


