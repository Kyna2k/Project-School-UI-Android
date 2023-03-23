package com.example.assignment.fragment;

import  android.os.Bundle;
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
import com.example.assignment.view.MainActivity;

import java.util.ArrayList;

public class frm_canhan extends Fragment {
    private GridView gridView;
    private String hinh = "";
    private ImageView avatar;
    public frm_canhan() {
    }

    public static frm_canhan getInstance()
    {
        frm_canhan frm_canhan = new frm_canhan();
        Bundle bundle = new Bundle();
        frm_canhan.setArguments(bundle);
        return frm_canhan;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frm_canhan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View view1 = view.findViewById(R.id.morinfo);
        View view2 = view.findViewById(R.id.avatarne);
        avatar = view2.findViewById(R.id.avatar_ne);
        EditText name = view1.findViewById(R.id.name);
        EditText address = view1.findViewById(R.id.address);
        EditText email = view1.findViewById(R.id.email);
        Button capnhat = view.findViewById(R.id.btn_dangky);
        TextView camnhatkhong = view.findViewById(R.id.comfirm);
        User user = ((MainActivity)getActivity()).getUSER_ne();
        name.setEnabled(false);
        address.setEnabled(false);
        email.setEnabled(false);
        avatar.setEnabled(false);
        name.setText(user.getName());
        address.setText(user.getAddress());
        email.setText(user.getEmail());
        avatar.setImageResource(getContext().getResources().getIdentifier(user.getAvatar(),"mipmap",getContext().getPackageName()));
        camnhatkhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setEnabled(true);
                address.setEnabled(true);
                email.setEnabled(true);
                avatar.setEnabled(true);
                capnhat.setEnabled(true);
            }
        });
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdialog();
            }
        });
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hinh.equals(""))
                {
                    hinh = user.getAvatar();
                }
                User newuser = new User(user.getId(), user.getUsername(), user.getPasswork(), name.getText().toString(),address.getText().toString(),email.getText().toString(),hinh);
                ((MainActivity)getActivity()).capnhatuser(newuser);
                name.setEnabled(false);
                address.setEnabled(false);
                email.setEnabled(false);
                avatar.setEnabled(false);
                capnhat.setEnabled(false);
                onResume();
            }
        });

    }
    public void getdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity) getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_avatar, null);
        builder.setView(view);
        gridView = view.findViewById(R.id.grid_avatar);
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
        grid_avatar apdater = new grid_avatar((MainActivity) getActivity(), ds);
        gridView.setAdapter(apdater);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String layhinh = (String) adapterView.getAdapter().getItem(i);
                hinh = layhinh;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avatar.setImageResource(getContext().getResources().getIdentifier(hinh, "mipmap", getContext().getPackageName()));
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
