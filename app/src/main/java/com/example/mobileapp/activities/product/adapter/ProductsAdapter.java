package com.example.mobileapp.activities.product.adapter;

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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{

    private List<ProductModel> productList;
    private Context context;

    public ProductsAdapter(List<ProductModel> productList, Context context){
        this.productList = productList;
        this.context = context;
    }

    public void setProductList(List<ProductModel> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(productList.get(position).getName());
        holder.category.setText(productList.get(position).getCategory());
        holder.price.setText(String.valueOf(productList.get(position).getPrice()));
        holder.brand.setText(productList.get(position).getBrand());

    }


    @Override
    public int getItemCount() {
        if(this.productList != null){
            return this.productList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, category, price, brand;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameTextView);
            category =(TextView) itemView.findViewById(R.id.categoryTextView);
            price = (TextView) itemView.findViewById(R.id.priceTextView);
            brand = (TextView) itemView.findViewById(R.id.brandTextView);
        }
    }
}
