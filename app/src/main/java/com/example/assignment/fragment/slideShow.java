package com.example.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment.R;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class slideShow extends Fragment {
    private static final String Name_Hinh = "nameHinh";
    private String hinh;
    GifImageView gifImageView;
    public slideShow() {
    }
    public static slideShow getSlideshow(String hinhne)
    {
        slideShow slideShow = new slideShow();
        Bundle args = new Bundle();
        args.putString(Name_Hinh,hinhne);
        slideShow.setArguments(args);
        return slideShow;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null)
        {
            this.hinh = getArguments().getString(Name_Hinh);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.slideshow,container,false);

        return  view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gifImageView =view.findViewById(R.id.title_slideshow);
        int id = getContext().getResources().getIdentifier(hinh,"drawable",getContext().getPackageName());
            gifImageView.setImageResource(id);
    }
}
