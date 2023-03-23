package com.example.assignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.adapter.viewPager2_SlideShow;
import com.example.assignment.dao.categoriesDAO;
import com.example.assignment.dao.transactionsDAO;
import com.example.assignment.dao.userDao;
import com.example.assignment.dao.walletsDAO;
import com.example.assignment.fragment.frm_dangky;
import com.example.assignment.fragment.frm_dangnhap;
import com.example.assignment.fragment.frm_dangnhap2;
import com.example.assignment.model.Categories;
import com.example.assignment.model.Transactions;
import com.example.assignment.model.User;
import com.example.assignment.model.Wallets;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class LoginActivity extends AppCompatActivity {
    private Integer dem = -1;
    private userDao userDao;
    private User user;
    private static final String getFrm1 = "danhnhapne";
    private static final String getFrm2 = "danhnhapne2";
    private static final String getFrm3 = "danhnhapne3";
    private SharedPreferences preferences;
    private walletsDAO walletsDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_layout);
        userDao = new userDao(LoginActivity.this);
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.trai_qua_phai,R.anim.trai_ra_phai,R.anim.phai_qua_trai,R.anim.phai_ra_trai)
                .add(R.id.frm_layout_dangnhap, frm_dangnhap.getInstance(true),getFrm1)
                .commit();
        preferences = getSharedPreferences("thongtin",MODE_PRIVATE);
        walletsDAO = new walletsDAO(LoginActivity.this);
    }
    public void getCheckTaiKhoan(String username)
    {
        user = new User();
        user.setUsername(username);
        boolean checktaikhoangne =  userDao.checktaikhoang(user);
        if(checktaikhoangne)
        {
            User userchuan = userDao.getUser(username);
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.phai_qua_trai,R.anim.phai_ra_trai,R.anim.trai_qua_phai,R.anim.trai_ra_phai)
                    .replace(R.id.frm_layout_dangnhap, frm_dangnhap2.getInstance(userchuan),getFrm2)
                    .commit();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username",user.getUsername());
            editor.commit();
        }else
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.phai_qua_trai,R.anim.phai_ra_trai,R.anim.trai_qua_phai,R.anim.trai_ra_phai)
                    .replace(R.id.frm_layout_dangnhap, frm_dangky.getInstance(username),getFrm3)
                    .commit();
        }
    }
    public void back()
    {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.trai_qua_phai,R.anim.trai_ra_phai,R.anim.phai_qua_trai,R.anim.phai_ra_trai)
                .replace(R.id.frm_layout_dangnhap, frm_dangnhap.getInstance(true),getFrm1)
                .commit();
    }
    public void getDanhnhap(User userne){
        boolean checkdanhnhap = userDao.dangnhap(userne);
        if(checkdanhnhap)
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        else {
            Toast.makeText(this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();

        }
    }
    public void Tost()
    {
        Toast.makeText(this, "hêlo", Toast.LENGTH_SHORT).show();
    }
    public void getDangky(User user12)
    {
        boolean check = userDao.dangky(user12);
        if(check)
        {
            User chuan = userDao.getUser(user12.getUsername());
            walletsDAO.addWallets(new Wallets("Tiền mặt","",0.0,"Ví tiêu dùng",chuan.getId()));
            walletsDAO.addWallets(new Wallets("Tiết kiệm","",0.0,"Ví Tiết kiệm",chuan.getId()));
            walletsDAO.addWallets(new Wallets("Ví khoản nợ","",0.0,"Ví khoản nợ",chuan.getId()));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username",user.getUsername());
            editor.commit();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));

        }else
        {
            Toast.makeText(this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();

        }
    }
}