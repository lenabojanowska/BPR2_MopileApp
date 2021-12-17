package com.example.mobileapp.activities.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.wishlist.adapter.WishlistAdapter;
import com.example.mobileapp.models.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<ProductModel> allProductList;
    //private List<ProductModel> productListFull;
    private Context context;

    private OnProductListener onProductListener;

    public SearchAdapter(List<ProductModel> productList, Context context, OnProductListener onProductListener) {
        this.allProductList = productList;
        this.context = context;
        //productListFull = new ArrayList<>(productList);
        this.onProductListener = onProductListener;
    }

    public void setAllProductsList(List<ProductModel> allProductList) {
        this.allProductList = allProductList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view, onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(this.allProductList.get(position).getName());
        holder.productCat.setText(this.allProductList.get(position).getCategory());
        holder.productPrice.setText(String.valueOf(this.allProductList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        if (this.allProductList != null) {
            return this.allProductList.size();
        }
        return 0;
    }

    public void filterList(List<ProductModel> filteredList){
        allProductList = filteredList;
        notifyDataSetChanged();
    }

    /*@Override
    public Filter getFilter() {
        return filter;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView productName, productCat, productPrice ;

        OnProductListener onProductListener;

        public ViewHolder(@NonNull View itemView, OnProductListener onProductListener) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.nameTextView);
            productCat = (TextView) itemView.findViewById(R.id.categoryTextView);
            productPrice = (TextView) itemView.findViewById(R.id.priceTextView);

            this.onProductListener = onProductListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onProductListener.onProductClick(getAdapterPosition());
        }

    }

    public interface OnProductListener{
        void onProductClick(int position);
    }




}



