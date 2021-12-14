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

    public WishlistProductsAdapter(List<ProductModel> productList, Context context){
        this.productList = productList;
        this.context = context;
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
        return new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView productName, wishlistItemPrice;
        //WishlistAdapter.OnWishlistListener onWishlistListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = (TextView)  itemView.findViewById(R.id.productOnWishlistName);
            wishlistItemPrice = (TextView) itemView.findViewById(R.id.wishlistItemPrice);
            //this.onWishlistListener = onWishlistListener;

           // itemView.setOnClickListener(this);
        }

       /* @Override
        public void onClick(View v) {
            onWishlistListener.onWishlistClick(getAdapterPosition());
        }*/
    }
}


