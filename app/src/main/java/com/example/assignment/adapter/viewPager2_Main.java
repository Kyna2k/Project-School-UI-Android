package com.example.assignment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assignment.fragment.frm_canhan;
import com.example.assignment.fragment.frm_giaodich;
import com.example.assignment.fragment.frm_home;
import com.example.assignment.model.Transactions;
import com.example.assignment.model.User;
import com.example.assignment.model.Wallets;

import java.util.ArrayList;

public class viewPager2_Main extends FragmentStateAdapter {
    private ArrayList<Wallets> wallets;
    private User user;
    public viewPager2_Main(@NonNull FragmentActivity fragmentActivity, User user, ArrayList<Wallets> wallets) {
        super(fragmentActivity);
        this.user = user;
        this.wallets = wallets;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return frm_home.getInstance(user,wallets);
            case 1:
                return frm_giaodich.getInstance();
            case 2:
                return frm_canhan.getInstance();
            default:
                return frm_home.getInstance(user,wallets);
        }
    }
    @Override
    public int getItemCount() {
        return 3;
    }

}
