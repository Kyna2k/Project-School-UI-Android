package com.example.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.assignment.R;

import java.util.ArrayList;

public class grid_avatar extends BaseAdapter {
    ArrayList<String> ds;
    Context context;
    public grid_avatar (Context context,ArrayList<String> ds)
    {
        this.ds = ds;
        this.context = context;
    }
    public void update(ArrayList<String> list)
    {
        this.ds.clear();
        this.ds.addAll(list);
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
        View view_ = view;
        if(view_ == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view_ = inflater.inflate(R.layout.getavatar_layout,viewGroup,false);
            ImageView avatarne = view_.findViewById(R.id.avatar_ne);
            viewHolder viewHolder = new viewHolder(avatarne);
            view_.setTag(viewHolder);
        }
        viewHolder holder =(viewHolder) view_.getTag();
        holder.avatar.setImageResource(context.getResources().getIdentifier(ds.get(i),"mipmap",context.getPackageName()));
        return view_;
    }
    private static class viewHolder{
        private ImageView avatar;
        public viewHolder(ImageView avatar)
        {
            this.avatar = avatar;
        }
    }
}
