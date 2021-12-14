package com.example.mobileapp.fragments.profile.review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.ReviewApi;
import com.example.mobileapp.fragments.profile.review.adapter.ReviewAdapter;
import com.example.mobileapp.fragments.store.adapter.StoreAdapter;
import com.example.mobileapp.models.ReviewModel;
import com.example.mobileapp.models.StoreModel;
import com.example.mobileapp.viewmodels.ReviewViewModel;
import com.example.mobileapp.viewmodels.StoreViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReviewFragment extends Fragment implements StoreAdapter.OnStoreListener, NumberPicker.OnValueChangeListener {

    private RecyclerView recyclerView;
    private StoreAdapter storeAdapter;

    private StoreViewModel storeViewModel;
    private ReviewViewModel reviewViewModel;

    private List<StoreModel> storeList;

    private FirebaseAuth firebaseAuth;

    private NumberPicker numberPicker;
    private EditText comment;

    private Button button;

    public String storeName;

    public int value;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_review, container, false);

        numberPicker = view.findViewById(R.id.numberPicker);
        comment = view.findViewById(R.id.editTextView);
        button = view.findViewById(R.id.addReviewButton);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        String username = firebaseUser.getEmail();

        recyclerView = (RecyclerView) view.findViewById(R.id.storeReviewRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        storeAdapter = new StoreAdapter(storeList, this.getContext(),(StoreAdapter.OnStoreListener) this);
        recyclerView.setAdapter(storeAdapter);
        storeList = new ArrayList<>();

        storeViewModel = ViewModelProviders.of(this).get(StoreViewModel.class);
        storeViewModel.getStores().observe(getViewLifecycleOwner(), new Observer<List<StoreModel>>() {
            @Override
            public void onChanged(List<StoreModel> storeModels) {
                if(storeModels != null){
                    storeList = storeModels;
                    storeAdapter.setStoreList(storeModels);
                }

            }
        });
        storeViewModel.GetStores();

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(5);
        numberPicker.setOnValueChangedListener(this);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = comment.getText().toString();

                //reviewViewModel.postReview(username, value, text, "storeName");
                ReviewApi reviewApi = ServiceGenerator.getReviewApi();
                ReviewModel reviewModel = new ReviewModel(username, value, text, storeName);
                Call<ReviewModel> call = reviewApi.postReview(reviewModel);
                call.enqueue(new Callback<ReviewModel>() {
                    @Override
                    public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                        Log.v("Tag", "Product added " + response.body().getId());
                    }

                    @Override
                    public void onFailure(Call<ReviewModel> call, Throwable t) {

                    }
                });
            }
        });


        return view;
    }

    @Override
    public void onStoreClick(int position) {

        storeName = storeList.get(position).getName();
        //storeId = storeList.get(position).getId();

        Toast.makeText(this.getActivity(), storeName, Toast.LENGTH_LONG).show();

    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        value = newVal;

    }
}