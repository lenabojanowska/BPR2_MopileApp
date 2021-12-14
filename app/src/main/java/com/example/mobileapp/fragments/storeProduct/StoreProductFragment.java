package com.example.mobileapp.fragments.storeProduct;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
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

import com.example.mobileapp.activities.storeProduct.StoreProductActivity;
import com.example.mobileapp.fragments.store.adapter.StoreAdapter;
import com.example.mobileapp.fragments.storeProduct.StoreProductFragment;
import com.example.mobileapp.models.StoreModel;
import com.example.mobileapp.viewmodels.StoreViewModel;

import java.util.ArrayList;
import java.util.List;

public class StoreProductFragment extends DialogFragment  implements  StoreAdapter.OnStoreListener{

    private StoreViewModel storeViewModel;

    private RecyclerView recyclerView;

    private StoreAdapter adapter;

    private List<StoreModel> storeList;

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
        fragmentTransaction.commit();
*/
        recyclerView = (RecyclerView) view.findViewById(R.id.storeRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new StoreAdapter(storeList, this.getActivity(), (StoreAdapter.OnStoreListener) this);
        recyclerView.setAdapter(adapter);
        storeList = new ArrayList<>();

        storeViewModel = ViewModelProviders.of(this.getActivity()).get(StoreViewModel.class);
        storeViewModel.getStores().observe(this, new Observer<List<StoreModel>>() {
            @Override
            public void onChanged(List<StoreModel> storeModels) {
                if(storeModels != null){
                    storeList = storeModels;
                    adapter.setStoreList(storeModels);
                }
            }
        });
        storeViewModel.GetStores();

        return  view;
    }

    @Override
    public void onStoreClick(int position) {
        String storeName = storeList.get(position).getName();
        Toast.makeText(this.getActivity(), storeName, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(StoreProductFragment.this.getActivity(), StoreProductActivity.class);
        intent.putExtra("storeId", storeList.get(position).getId());
        intent.putExtra("storeName", storeList.get(position).getName());
        intent.putExtra("storeAddress", storeList.get(position).getAddress());
        startActivity(intent);

    }
}
