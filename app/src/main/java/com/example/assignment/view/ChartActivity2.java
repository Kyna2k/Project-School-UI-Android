package com.example.assignment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;


import com.example.assignment.R;
import com.example.assignment.dao.transactionsDAO;
import com.example.assignment.dao.userDao;
import com.example.assignment.dao.walletsDAO;
import com.example.assignment.model.Transactions;
import com.example.assignment.model.User;
import com.example.assignment.model.Wallets;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ChartActivity2 extends AppCompatActivity {
    private transactionsDAO transactionsDAO;
    private walletsDAO walletsDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart2);
        overridePendingTransition(R.anim.dilen,R.anim.dilen2);
        LineChart chart = (LineChart) findViewById(R.id.chart);
        transactionsDAO = new transactionsDAO(this);
        SharedPreferences preferences = getSharedPreferences("thongtin",MODE_PRIVATE);
        String username = preferences.getString("username","");
        userDao userDao = new userDao(this);
        User user = userDao.getUser(username);
        walletsDAO = new walletsDAO(this);
        ArrayList<Wallets> ds = walletsDAO.getAll(user);
        ArrayList<Transactions> ds2 = transactionsDAO.getAll(ds.get(0));
        List<Entry> entries = new ArrayList<Entry>();
        for (int i=0;i<ds2.size();i++)
        {
            Float tien = Float.parseFloat(String.valueOf(ds2.get(i).getAmount())) ;
            entries.add(new Entry((tien),Math.round(Math.random()*500)));
        }
        Collections.sort(entries, new EntryXComparator());
        LineDataSet dataSet = new LineDataSet(entries,"Label");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.dilen2,R.anim.dixuong);
    }
}