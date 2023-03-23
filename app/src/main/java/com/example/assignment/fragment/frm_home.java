package com.example.assignment.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.assignment.R;
import com.example.assignment.adapter.vierPager2_Card;
import com.example.assignment.adapter.viewPager2_SlideShow;
import com.example.assignment.model.User;
import com.example.assignment.model.Wallets;
import com.example.assignment.view.MainActivity;
import com.example.assignment.view.themgiaodich;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class frm_home extends Fragment {
    private static final String parame1 = "getvalue1";
    private static final String parame2 = "getvalue2";

    private User user;
    private ArrayList<Wallets> ds_wallets;
    private ImageView avatar,btn_chuyen;
    private TextView name,email;
    private ViewPager2 viewPager2,slideshow;
    private Integer dem = 0;
    private Integer    count = 0;
    private CardView btn_thu,btn_chi,btn_thongke;

    public frm_home() {
    }
    public static frm_home getInstance(User user1,ArrayList<Wallets> wallets1)
    {
        frm_home frm_home1 = new frm_home();
        Bundle bundle = new Bundle();
        bundle.putSerializable(parame1,user1);
        bundle.putSerializable(parame2,wallets1);
        frm_home1.setArguments(bundle);
        return frm_home1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null)
        {
            user = (User) getArguments().getSerializable(parame1);
            ds_wallets = (ArrayList<Wallets>) getArguments().getSerializable(parame2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_home_layout,container,false);
        btn_thu = view.findViewById(R.id.btn_thu);
        btn_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).goiThu(1);
            }
        });
        btn_chi = view.findViewById(R.id.btn_chi);
        btn_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).goiThu(2);
            }
        });
        btn_thongke = view.findViewById(R.id.thongke);
        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).thongke();
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        avatar = view.findViewById(R.id.avatar_user);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        avatar.setImageResource(getContext().getResources().getIdentifier(user.getAvatar(),"mipmap",getContext().getPackageName()));
        name.setText(user.getName());
        email.setText(user.getEmail());
        viewPager2 = view.findViewById(R.id.viewPager2);
        viewPager2.setAdapter(new vierPager2_Card(getActivity(),ds_wallets));
        btn_chuyen = view.findViewById(R.id.chuyen);
        btn_chuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem++;
                if(dem > ds_wallets.size())
                {
                    dem = 0;
                }
                viewPager2.setCurrentItem(dem);
            }
        });
        slideshow  = view.findViewById(R.id.slideshow);
        slideshow.setAdapter(new viewPager2_SlideShow(getActivity()));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                if(count > 3)
                {
                    count = 0;
                }
                slideshow.setCurrentItem(count);
            }
        },0,2000);
    }
}
