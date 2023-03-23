package com.example.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;
import com.example.assignment.dao.categoriesDAO;
import com.example.assignment.model.Categories;
import com.example.assignment.model.Transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class lv_trans_adapter extends BaseAdapter {
    private ArrayList<Transactions> ds;
    private Context context;
    private categoriesDAO categoriesDAO;
    public lv_trans_adapter(Context context,ArrayList<Transactions> ds,categoriesDAO categoriesDAO)
    {
        this.context = context;
        this.ds = ds;
        this.categoriesDAO = categoriesDAO;
    }
    public void updateData(ArrayList<Transactions> ds)
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
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view1 = inflater.inflate(R.layout.lv_transaction,viewGroup,false);
            ImageView hinh= view1.findViewById(R.id.hinh);
            TextView info = view1.findViewById(R.id.info);
            TextView time = view1.findViewById(R.id.time);
            TextView money = view1.findViewById(R.id.money);
            TextView note = view1.findViewById(R.id.note);
            viewHoder viewHoder = new viewHoder(hinh,info,time,money,note);
            view1.setTag(viewHoder);
        }
        viewHoder hoder = (viewHoder) view1.getTag();
        Categories categories = categoriesDAO.getCategories(ds.get(i).getCategory_id());
        hoder.hinh.setImageResource(context.getResources().getIdentifier(categories.getImages(),"mipmap",context.getPackageName()));
        hoder.info.setText(categories.getName());
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        if(ds.get(i).getType() == 1)
        {

            hoder.money.setText("+"+ nf.format(ds.get(i).getAmount()).replace("$","") + " Đ");
            hoder.money.setTextColor(Color.parseColor("#287FCE"));
        }
        else
        {
            hoder.money.setText("-"+ nf.format(ds.get(i).getAmount()).replace("$","") + " Đ");
            hoder.money.setTextColor(Color.parseColor("#FF4850"));
        }
        Date date = new Date(ds.get(i).getDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        hoder.time.setText(dateFormat.format(date) );
        hoder.note.setText(ds.get(i).getNotes());
        return view1;
    }
    private static class viewHoder
    {
        ImageView hinh;
        TextView info,time,money,note;
        public viewHoder(ImageView hinh, TextView info,TextView time,TextView money,TextView note)
        {
            this.hinh = hinh;
            this.info = info;
            this.time = time;
            this.money = money;
            this.note = note;
        }
    }
}
