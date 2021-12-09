package com.example.mobileapp.activities.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.models.NewsletterModel;

import java.util.List;

public class NewsletterAdapter extends RecyclerView.Adapter<NewsletterAdapter.ViewHolder>{

    private List<NewsletterModel> newsletterList;
    private Context context;

    public NewsletterAdapter(List<NewsletterModel> newsletterList, Context context){
        this.newsletterList = newsletterList;
        this.context = context;
    }

    public void setNewsletterList(List<NewsletterModel> newsletterList){
        this.newsletterList = newsletterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsletter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String string = newsletterList.get(position).getTitle();

        holder.newsletterName.setText(string);
       // Log.v("Tag","hello"+ newsletterList.get(position).getTitle().toString());
    }


    @Override
    public int getItemCount() {
        if(this.newsletterList != null){
            return this.newsletterList.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView newsletterName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newsletterName = (TextView) itemView.findViewById(R.id.omg);

            Log.v("tag", "check sth " + newsletterName.toString());


        }
    }
}

