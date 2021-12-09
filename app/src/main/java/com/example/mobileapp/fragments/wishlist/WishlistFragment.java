package com.example.mobileapp.fragments.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.product.ProductActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.adapter.WishlistAdapter;
import com.example.mobileapp.fragments.store.adapter.StoreAdapter;
import com.example.mobileapp.models.StoreModel;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.viewmodels.StoreViewModel;
import com.example.mobileapp.viewmodels.WishlistViewModel;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends DialogFragment implements WishlistAdapter.OnWishlistListener {

    private WishlistViewModel wishlistViewModel;

    private RecyclerView recyclerView;

    private WishlistAdapter adapter;

    private List<WishlistModel> wishlistList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_fragment, container);

        String first = "storeList.get(position).getName()";
        Bundle bundle = new Bundle();
        bundle.putString("looool", first);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /*AllFragment allFragment = new AllFragment();
        allFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.allProductsRecyclerView, allFragment);
        fragmentTransaction.commit();*/

        recyclerView = (RecyclerView) view.findViewById(R.id.storeRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new WishlistAdapter(wishlistList, this.getActivity(), (WishlistAdapter.OnWishlistListener) this);
        recyclerView.setAdapter(adapter);
        wishlistList = new ArrayList<>();

        wishlistViewModel = ViewModelProviders.of(this.getActivity()).get(WishlistViewModel.class);
        wishlistViewModel.getWishlistList().observe(this, new Observer<List<WishlistModel>>() {
            @Override
            public void onChanged(List<WishlistModel> wishlistModels) {
                if(wishlistModels != null){
                    wishlistList = wishlistModels;
                    adapter.setWishlistList(wishlistModels);
                }
            }
        });
        wishlistViewModel.GetRetrofitResponse();

        return  view;
    }

    @Override
    public void onWishlistClick(int position) {
        Intent intent = new Intent(this.getActivity(), ProductActivity.class);
        //intent.putExtra("name", storeList.get(position).getName());
        startActivity(intent);

    }
}

