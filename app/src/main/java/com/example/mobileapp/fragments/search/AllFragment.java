/*
package com.example.mobileapp.fragments.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.search.adapter.SearchAdapter;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.viewmodels.AllProductsViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {

    private AllProductsViewModel allProductsViewModel;

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

    private List<ProductModel> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.all_fragment, container, false);

        Bundle bundle = getArguments();
        String first = bundle.getString("looool");
        Log.v("tag", "first: " + first);

        recyclerView = (RecyclerView) view.findViewById(R.id.allProductsRecyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        searchAdapter = new SearchAdapter(productList, this.getActivity());
        recyclerView.setAdapter(searchAdapter);
        productList = new ArrayList<>();

        allProductsViewModel = ViewModelProviders.of(this.getActivity()).get(AllProductsViewModel.class);
        allProductsViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                if(productModels != null){
                    productList = productModels;
                    searchAdapter.setAllProductsList(productModels);
                }

            }
        });
        allProductsViewModel.GetAllProducts();




        return(view);

    }
}
*/
