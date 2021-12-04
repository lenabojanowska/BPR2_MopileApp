package com.example.mobileapp.activities.wishlist;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileapp.R;
import com.example.mobileapp.viewmodels.WishlistViewModel;

public class WishlistActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        Log.d(TAG, "wishlistactivity");
    }
}
