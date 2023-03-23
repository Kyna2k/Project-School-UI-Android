package com.example.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;
import com.example.assignment.model.Categories;
import com.example.assignment.model.Wallets;

import java.util.ArrayList;

public class spnierds_muc extends BaseAdapter {
    private ArrayList<Categories> ds;
    private Context context;
    public spnierds_muc(Context context,ArrayList<Categories> ds)
    {
        this.ds = ds;
        this.context = context;
    }
    public void updateData(ArrayList<Categories> ds)
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
            view1 = layoutInflater.inflate(R.layout.spinner_danhmuc,viewGroup,false);
            TextView textView = view1.findViewById(R.id.info);
            ImageView hinh = view1.findViewById(R.id.hinh);
            viewHoder viewHoder = new viewHoder(hinh,textView);
            view1.setTag(viewHoder);
        }
        viewHoder hoder =(viewHoder) view1.getTag();
        hoder.imageView.setImageResource(context.getResources().getIdentifier(ds.get(i).getImages(),"mipmap",context.getPackageName()));
        hoder.textView.setText(ds.get(i).getName());
        return view1;
    }
    private static class viewHoder{
        TextView textView;
        ImageView imageView;
        public viewHoder(ImageView imageView,TextView textView)
        {
            this.imageView = imageView;
            this.textView = textView;
        }
    }
}
