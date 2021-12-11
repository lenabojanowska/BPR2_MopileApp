package com.example.mobileapp.fragments.profile.viewPageAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mobileapp.fragments.profile.review.AddReviewFragment;
import com.example.mobileapp.fragments.profile.review.MyReviewsFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new MyReviewsFragment();
            case 1:
                return new AddReviewFragment();
        }
            return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
