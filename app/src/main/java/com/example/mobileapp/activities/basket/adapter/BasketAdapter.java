package com.example.mobileapp.activities.basket.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.search.adapter.SearchAdapter;
import com.example.mobileapp.activities.wishlist.adapter.WishlistProductsAdapter;
import com.example.mobileapp.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private List<ProductModel> productList;
    private Context context;

    public BasketAdapter(List<ProductModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
        //productListFull = new ArrayList<>(productList);
    }

    public void setProductList(List<ProductModel> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.basket_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(this.productList.get(position).getName());
        holder.price.setText(String.valueOf(this.productList.get(position).getPrice()));
        Log.v("Tag", "namehbsDyAGSdiu " + productList.get(position).getName());

    }


    @Override
    public int getItemCount() {
        if (this.productList != null) {
            return this.productList.size();

        }
        //Log.v("Tag", "hjkl" + productList.toString());
        return 0;
    }

    public ProductModel getProductAt(int position){
        return productList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.basketItem);
            price = (TextView) itemView.findViewById(R.id.price);

        }
    }

}
