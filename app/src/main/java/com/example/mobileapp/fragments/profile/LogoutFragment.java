package com.example.mobileapp.fragments.profile;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends DialogFragment{

    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.logout_fragment, container);

        firebaseAuth = FirebaseAuth.getInstance();




        return view;
    }

    public void logout(View view){
        firebaseAuth.signOut();
    }
}
