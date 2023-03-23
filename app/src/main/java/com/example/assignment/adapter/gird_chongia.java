package com.example.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.icu.text.NumberFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment.R;

import java.util.ArrayList;
import java.util.Locale;

public class gird_chongia extends BaseAdapter {
    private ArrayList<Double> ds;
    private Context context;
    public gird_chongia(Context context,ArrayList<Double> ds) {
        this.ds = ds;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int i) {
        return ds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.gridgia,viewGroup,false);
        }
        TextView textView = view.findViewById(R.id.gia);
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String tien =nf.format(ds.get(i)).replace("$","") + "đ";
        textView.setText(tien);
        return view;
    }
}
