package com.example.assignment.fragment;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment.R;
import com.example.assignment.model.User;
import com.example.assignment.view.LoginActivity;

public class frm_dangnhap2 extends Fragment {
    private static final String PARAM1 = "nhappassne";
    private User user;
    private TextView name;
    private ImageView hinh,showpass,back;
    private Button btn_danhnhap;
    private EditText passwork;
    boolean check = true;
    public frm_dangnhap2(){}
    public static frm_dangnhap2 getInstance(User user1)
    {
        frm_dangnhap2 fm = new frm_dangnhap2();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PARAM1,user1);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            user = (User) getArguments().getSerializable(PARAM1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup_step1_2,container,false);
        showpass = view.findViewById(R.id.showpass);

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check)
                {
                    showpass.setImageResource(R.drawable.eye_close);
                    passwork.setInputType(InputType.TYPE_CLASS_TEXT);
                    check = false;
                }
                else
                {
                    showpass.setImageResource(R.drawable.eye_open);
                    passwork.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    check = true;
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.name);
        hinh =view.findViewById(R.id.avatar);
        name.setText("Xin chào, " + user.getName());;
        hinh.setImageResource(getContext().getResources().getIdentifier(user.getAvatar(), "mipmap",getContext().getPackageName()));
        passwork = view.findViewById(R.id.password);
        back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).back();
            }
        });
        btn_danhnhap = view.findViewById(R.id.btn_dangnhap);
        btn_danhnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setPasswork(passwork.getText().toString());
                ((LoginActivity)getActivity()).getDanhnhap(user);
            }
        });
    }
}
