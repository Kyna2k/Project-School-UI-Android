package com.example.assignment.fragment;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment.R;
import com.example.assignment.model.Wallets;

import java.util.Locale;

public class frm_card extends Fragment {
    public static final String Param = "get";
    private Wallets wallet;
    private TextView name_vi,money,thongtinthe;
    public frm_card() {
    }
    public static frm_card getInstance(Wallets wallets)
    {
        frm_card frm_card = new frm_card();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Param,wallets);
        frm_card.setArguments(bundle);
        return frm_card;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            wallet = (Wallets) getArguments().getSerializable(Param);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_vi,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name_vi = view.findViewById(R.id.name_vi);
        money = view.findViewById(R.id.money);
        thongtinthe = view.findViewById(R.id.thongtinthem);
        name_vi.setText(wallet.getType());
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String moneyAfter =nf.format(wallet.getMoney()).replace("$","") + " VNĐ";
        money.setText(moneyAfter);
        thongtinthe.setText(wallet.getDescription());
    }
}
