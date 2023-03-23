package com.example.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignment.R;
import com.example.assignment.model.Wallets;

import java.util.ArrayList;

public class dswallets_spinner extends BaseAdapter {
    private ArrayList<Wallets> ds;
    private Context context;
    public dswallets_spinner(Context context,ArrayList<Wallets> ds)
    {
        this.ds = ds;
        this.context = context;
    }
    public void updateData(ArrayList<Wallets> ds)
    {
        this.ds.clear();
        this.ds.addAll(ds);
        notifyDataSetChanged();
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
        View view1 = view;
        if(view1 == null)
        {
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            view1 = layoutInflater.inflate(R.layout.spnier_vi,viewGroup,false);
            TextView textView = view1.findViewById(R.id.text);
            viewHoder viewHoder = new viewHoder(textView);
            view1.setTag(viewHoder);

        }
        viewHoder hoder =(viewHoder) view1.getTag();
        hoder.textView.setText(ds.get(i).getType());
        return view1;
    }
    private static class viewHoder{
        TextView textView;
        public viewHoder(TextView textView)
        {
            this.textView = textView;
        }
    }
}
