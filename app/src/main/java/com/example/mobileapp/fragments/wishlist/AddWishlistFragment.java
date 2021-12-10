package com.example.mobileapp.fragments.wishlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mobileapp.R;
import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.WishlistApi;
import com.example.mobileapp.models.WishlistModel;
import com.example.mobileapp.viewmodels.WishlistViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWishlistFragment extends DialogFragment {

    private FirebaseAuth firebaseAuth;

    private TextView wishlistName;
    private Button addWishlistButton;

    private WishlistViewModel wishlistViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_wishlist_dialog_fragment, container);

        wishlistName = (TextView) view.findViewById(R.id.wishlistEditText);
        addWishlistButton = (Button) view.findViewById(R.id.sthButton);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String id = firebaseUser.getUid();
        //Toast.makeText(this.getActivity(), "id " + id, Toast.LENGTH_LONG).show();
        String name = wishlistName.getText().toString();
        Toast.makeText(this.getActivity(), "name " + name, Toast.LENGTH_LONG).show();


        addWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistApi wishlistApi = ServiceGenerator.getWishListApi();
                WishlistModel wishlistModel = new WishlistModel(name, id);

                Call<WishlistModel> call = wishlistApi.postWishlist(wishlistModel);

                call.enqueue(new Callback<WishlistModel>() {
                    @Override
                    public void onResponse(Call<WishlistModel> call, Response<WishlistModel> response) {
                        Log.v("Tag", "--------//--------");
                        Log.v("Tag", "Posting Wishlist");
                        Log.v("Tag", "The Wishlist posted");
                        Log.v("Tag", "The Wishlist " + response.body().getName());

                        //mWishlist.setValue((List<WishlistModel>) response.body());
                    }

                    @Override
                    public void onFailure(Call<WishlistModel> call, Throwable t) { Log.v("Tag", t.toString());
                    }
                });
            }
        });

        /*Intent intent = new Intent(this.getActivity(), WishlistsActivity.class);
        //intent.putExtra("name", storeList.get(position).getName());
        startActivity(intent);*/

        return view;
    }
}
