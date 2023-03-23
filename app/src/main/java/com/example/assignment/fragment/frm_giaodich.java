package com.example.assignment.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.assignment.R;
import com.example.assignment.adapter.dswallets_spinner;
import com.example.assignment.adapter.lv_trans_adapter;
import com.example.assignment.dao.categoriesDAO;
import com.example.assignment.model.Transactions;
import com.example.assignment.model.Wallets;
import com.example.assignment.view.MainActivity;

import java.util.ArrayList;


public class frm_giaodich extends Fragment {
    private static final String Param = "value";
    private ArrayList<Transactions> ds;
    private Spinner spinner;
    public Wallets wallets_get;
    private ListView lv;
    public lv_trans_adapter adapter_lv;

    public frm_giaodich()
    {

    }
    public static frm_giaodich getInstance()
    {
        frm_giaodich frm_giaodich = new frm_giaodich();
        Bundle bundle = new Bundle();
        frm_giaodich.setArguments(bundle);
        return frm_giaodich;
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
        View view = inflater.inflate(R.layout.giadich_layout,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Wallets> wallets = ((MainActivity)getActivity()).dsWallets();
        spinner = view.findViewById(R.id.dsvi);
        dswallets_spinner adapter = new dswallets_spinner(getActivity(),wallets);
        spinner.setAdapter(adapter);
        lv = view.findViewById(R.id.lv);
        categoriesDAO categoriesDAO = new categoriesDAO(getActivity());
        reloadLV();
        adapter_lv= new lv_trans_adapter(getActivity(),ds,categoriesDAO);
        lv.setAdapter(adapter_lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView note = view.findViewById(R.id.note);
                note.setVisibility(View.VISIBLE);

            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Transactions transactions = (Transactions) adapterView.getAdapter().getItem(i);
                ((MainActivity)getActivity()).menu_(view,transactions);
                return true;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reloadLV();
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void reloadLV()
    {
        wallets_get=(Wallets) spinner.getSelectedItem();
        ds = ((MainActivity)getActivity()).getAll(wallets_get);
    }
    public void update()
    {
        adapter_lv.updateData(ds);
    }

}
