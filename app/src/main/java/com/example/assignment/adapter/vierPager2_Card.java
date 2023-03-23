package com.example.assignment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assignment.fragment.frm_card;
import com.example.assignment.model.Wallets;

import java.util.ArrayList;

public class vierPager2_Card extends FragmentStateAdapter {
    private ArrayList<Wallets> wallets;
    public vierPager2_Card(@NonNull FragmentActivity fragmentActivity,ArrayList<Wallets> wallets) {
        super(fragmentActivity);
        this.wallets = wallets;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return frm_card.getInstance(wallets.get(position));
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }
}
