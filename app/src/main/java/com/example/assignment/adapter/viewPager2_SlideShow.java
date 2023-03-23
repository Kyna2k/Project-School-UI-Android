package com.example.assignment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assignment.R;
import com.example.assignment.fragment.slideShow;

public class viewPager2_SlideShow extends FragmentStateAdapter {
    public viewPager2_SlideShow(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return slideShow.getSlideshow("login_imges");
            case 1:
                return slideShow.getSlideshow("login_imges2");
            case 2:
                return slideShow.getSlideshow("login_imges3");
            case 3:
                return slideShow.getSlideshow("login_imges4");
            default:
                return slideShow.getSlideshow("login_imges");
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
