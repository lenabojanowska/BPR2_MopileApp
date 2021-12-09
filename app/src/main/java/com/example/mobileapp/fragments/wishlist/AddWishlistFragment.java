package com.example.mobileapp.fragments.wishlist;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mobileapp.R;
import com.example.mobileapp.connection.apis.WishlistApi;
import com.example.mobileapp.models.WishlistModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWishlistFragment extends DialogFragment {

    private FirebaseAuth firebaseAuth;

    private EditText wishlistName;
    private Button addWishlistButton;

    private WishlistModel wishlistModel;
    private WishlistApi wishlistApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wishlist_dialog_fragment, container);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String id = firebaseUser.getUid();

        wishlistName = view.findViewById(R.id.wishlistEditText);
        addWishlistButton = view.findViewById(R.id.addWishlistButton);

        String name = wishlistName.getText().toString();

        addWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return view;
    }

    private void createWishlist(){
        WishlistModel wishlistModel = new WishlistModel(wishlistName.getText().toString(), firebaseAuth.getUid());

        Call<WishlistModel> call = wishlistApi.postWishlist(wishlistModel);
        call.enqueue(new Callback<WishlistModel>() {
            @Override
            public void onResponse(Call<WishlistModel> call, Response<WishlistModel> response) {
                if(!response.isSuccessful()){
                    Log.v("Tag", "error");
                    return;
                }
                WishlistModel 


            }

            @Override
            public void onFailure(Call<WishlistModel> call, Throwable t) {

            }
        });
    }
}
