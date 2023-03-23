package com.example.assignment.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.assignment.R;
import com.example.assignment.adapter.viewPager2_SlideShow;
import com.example.assignment.view.LoginActivity;

public class frm_dangnhap extends Fragment {
    private static final String PARAM1 = "gettaikhoang";
    private boolean checktaikhoang;
    private EditText username;
    private Button dangnhap;
    private ImageView clean;
    private ViewPager2 viewPager2;
    public frm_dangnhap() {
    }
    public static frm_dangnhap getInstance(boolean check){
        frm_dangnhap frm_dangnhap = new frm_dangnhap();
        Bundle args = new Bundle();
        args.putBoolean(PARAM1,check);
        frm_dangnhap.setArguments(args);
        return frm_dangnhap;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            checktaikhoang = getArguments().getBoolean(PARAM1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup_step1,container,false);
        viewPager2 = view.findViewById(R.id.viewPager2);
        viewPager2_SlideShow viewPager2_slideShow = new viewPager2_SlideShow((LoginActivity)getActivity());
        viewPager2.setAdapter(viewPager2_slideShow);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = view.findViewById(R.id.username);
        clean = view.findViewById(R.id.clean_enu);
        dangnhap = view.findViewById(R.id.btn_dangnhap);
        dangnhap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ((LoginActivity)getActivity()).getCheckTaiKhoan(username.getText().toString());
             }
         });
        dangnhap.setEnabled(false);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!username.getText().toString().equalsIgnoreCase(""))
                {
                    clean.setVisibility(View.VISIBLE);
                    dangnhap.setEnabled(true);
                }
                else
                {
                    clean.setVisibility(View.GONE);
                    dangnhap.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
            }
        });

    }
}
