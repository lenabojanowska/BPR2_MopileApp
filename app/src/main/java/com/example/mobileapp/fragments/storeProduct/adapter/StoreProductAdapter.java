package com.example.mobileapp.fragments.storeProduct.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.models.StoreModel;

import java.util.List;

public class StoreProductAdapter extends RecyclerView.Adapter<StoreProductAdapter.ViewHolder> {

    private List<StoreModel> storeList;
    private Context context;
    private OnStoreListener onStoreListener;

    private int selectedPos = RecyclerView.NO_POSITION;

    public StoreProductAdapter(List<StoreModel> storeList, Context context, OnStoreListener onStoreListener ) {
        this.storeList = storeList;
        this.context = context;
        this.onStoreListener = onStoreListener;

    }

    public void setStoreList(List<StoreModel> storeList){
        this.storeList = storeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item, parent, false);


        return new ViewHolder(view, onStoreListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.storeName.setText(this.storeList.get(position).getName());
        holder.storeAddress.setText(this.storeList.get(position).getAddress());
        holder.itemView.setSelected(selectedPos == position);
    }


    @Override
    public int getItemCount() {
        if(this.storeList != null){
            return this.storeList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView storeName, storeAddress;
        OnStoreListener onStoreListener;
        CardView cardView;

        public ViewHolder(@NonNull View itemView, OnStoreListener onStoreListener) {
            super(itemView);

            storeName = (TextView) itemView.findViewById(R.id.storeName);
            storeAddress = (TextView) itemView.findViewById(R.id.storeAddress);
            cardView = (CardView) itemView.findViewById(R.id.reviewCard);

            this.onStoreListener = onStoreListener;
            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            onStoreListener.onStoreClick(getAdapterPosition());
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);

        }
    }

    public interface OnStoreListener{
        void onStoreClick(int position);
    }


}

