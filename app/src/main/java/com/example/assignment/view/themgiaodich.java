package com.example.assignment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.assignment.R;
import com.example.assignment.adapter.dswallets_spinner;
import com.example.assignment.adapter.gird_chongia;
import com.example.assignment.dao.categoriesDAO;
import com.example.assignment.dao.transactionsDAO;
import com.example.assignment.dao.userDao;
import com.example.assignment.dao.walletsDAO;
import com.example.assignment.fragment.frm_thu;
import com.example.assignment.model.Categories;
import com.example.assignment.model.Transactions;
import com.example.assignment.model.Wallets;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class themgiaodich extends AppCompatActivity {
    private Boolean dachonngay = true, dachongio = true;
    private long time2;
    private userDao userDao;
    private walletsDAO walletsDAO;
    private Wallets wallets2;
    private transactionsDAO transactionsDAO;
    private String username;
    private categoriesDAO categoriesDAO;
    private Integer chimuc;
    private boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chimuc = getIntent().getExtras().getInt("chimuc");
        setContentView(R.layout.themgiaodich_layout);
        overridePendingTransition(R.anim.dilen,R.anim.dilen2);
        categoriesDAO = new categoriesDAO(this);
        transactionsDAO = new transactionsDAO(this);
        SharedPreferences preferences = getSharedPreferences("thongtin",MODE_PRIVATE);
        username = preferences.getString("username","");
        userDao = new userDao(this);
        walletsDAO = new walletsDAO(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fm_giaodich, frm_thu.getInstance(chimuc))
                .commit();


    }
    public ArrayList<Wallets> getAllWallet()
    {
        return walletsDAO.getAll(userDao.getUser(username));

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.dilen2,R.anim.dixuong);

    }
    public void Timene(long timene)
    {
        time2 = timene;
    }
    public long getTimene()
    {
        return time2;
    }
    public void addTrans(Transactions trans)
    {
       check = transactionsDAO.addTrans(trans);
    }
    public ArrayList<Categories> getAllCate()
    {
        return categoriesDAO.getAll();
    }
    public void dialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaglog_thongbao,null);
        builder.setView(view);
        GifImageView hinh = view.findViewById(R.id.hinh);
        TextView noidung = view.findViewById(R.id.noidung);
        Button btn_ok = view.findViewById(R.id.comfirm);
        if(!check)
        {
            noidung.setText("Thật bại");
            hinh.setImageResource(R.mipmap.no);
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.animastion;
        alertDialog.show();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check)
                {
                    alertDialog.dismiss();
                    finish();
                }
                else{
                    alertDialog.dismiss();
                }
            }
        });
    }
    public void capnhatvi(Wallets wallets)
    {
        walletsDAO.getMoney(wallets);
    }
}