package com.example.assignment.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.assignment.R;
import com.example.assignment.adapter.grid_avatar;
import com.example.assignment.model.User;
import com.example.assignment.view.LoginActivity;

import java.util.ArrayList;

public class frm_dangky extends Fragment {
    private static final String Param = "item";
    private String username;
    private ImageView back,avatarne;
    private EditText repass,pass,name,email,address;
    private String hinh = "";
    private GridView gridView;
    private Button btn_dangky;
    private User user;
    private TextView usernamemoi;
    public frm_dangky() {
    }
    public static frm_dangky getInstance(String nameuser)
    {
        frm_dangky frm_dangky = new frm_dangky();
        Bundle bundle = new Bundle();
        bundle.putString(Param,nameuser);
        frm_dangky.setArguments(bundle);
        return frm_dangky;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            username = getArguments().getString(Param);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup_step2,container,false);
        View view1 = view.findViewById(R.id.avatarne);
        avatarne = view1.findViewById(R.id.avatar_ne);
        usernamemoi = view.findViewById(R.id.username_moi);
        usernamemoi.setText("Xin chào tài khoảng mới, " + username);
        avatarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdialog();
            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).back();
            }
        });
        pass = view.findViewById(R.id.password);
        repass = view.findViewById(R.id.repassword);
        View view3 = view.findViewById(R.id.morinfo);
        name = view3.findViewById(R.id.name);
        address = view3.findViewById(R.id.address);
        email = view3.findViewById(R.id.email);
        btn_dangky = view.findViewById(R.id.btn_dangky);
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().equals(repass.getText().toString()))
                {
                    user = new User(username,pass.getText().toString(),name.getText().toString(),address.getText().toString(),email.getText().toString(),hinh);
                    ((LoginActivity)getActivity()).getDangky(user);
                }
            }
        });
    }
    public void getdialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder((LoginActivity)getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_avatar,null);
        builder.setView(view);
        gridView  = view.findViewById(R.id.grid_avatar);
        Button btn_okay = view.findViewById(R.id.chon);
        Button btn_cancel = view.findViewById(R.id.cancel);
        ArrayList<String> ds = new ArrayList<>();
        ds.add("avatar1");
        ds.add("avatar2");
        ds.add("avatar3");
        ds.add("avatar4");
        ds.add("avatar5");
        ds.add("avatar6");
        ds.add("avatar7");
        ds.add("avatar8");
        ds.add("avatar9");
        grid_avatar apdater = new grid_avatar((LoginActivity)getActivity(),ds);
        gridView.setAdapter(apdater);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String layhinh =(String) adapterView.getAdapter().getItem(i);
                hinh = layhinh;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatarne.setImageResource(getContext().getResources().getIdentifier(hinh,"mipmap",getContext().getPackageName()));
                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}
