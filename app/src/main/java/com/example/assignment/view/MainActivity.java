package com.example.assignment.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.adapter.dswallets_spinner;
import com.example.assignment.adapter.spnierds_muc;
import com.example.assignment.adapter.viewPager2_Main;
import com.example.assignment.dao.categoriesDAO;
import com.example.assignment.dao.transactionsDAO;
import com.example.assignment.dao.userDao;
import com.example.assignment.dao.walletsDAO;
import com.example.assignment.fragment.frm_canhan;
import com.example.assignment.fragment.frm_giaodich;
import com.example.assignment.model.Categories;
import com.example.assignment.model.Transactions;
import com.example.assignment.model.User;
import com.example.assignment.model.Wallets;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private ViewPager2 layoutchinh;
    private userDao userDao;
    Calendar myCalendar= Calendar.getInstance();
    private walletsDAO walletsDAO;
    private SharedPreferences preferences;
    private BottomNavigationView bottomNavigationView;
    private transactionsDAO transactionsDAO;
    private User user;
    private EditText gio,ngay;
    private boolean check;
    private CardView naptien;
    private viewPager2_Main adapter;
    private Long time_ne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(Color.parseColor("#FF4850"));
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawelayout);
        navigationView = (NavigationView) findViewById(R.id.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        layoutchinh = findViewById(R.id.main);
        preferences = getSharedPreferences("thongtin",MODE_PRIVATE);
        userDao = new userDao(MainActivity.this);
        walletsDAO = new walletsDAO(MainActivity.this);
        String username = preferences.getString("username","");
        user= userDao.getUser(username);
        transactionsDAO = new transactionsDAO(this);
        Button btn_thoat = findViewById(R.id.clickme);
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bottomNavigationView = findViewById(R.id.nav_duoi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        layoutchinh.setCurrentItem(0);
                        break;
                    case R.id.giaodich:
                        layoutchinh.setCurrentItem(1);
                        break;
                    case R.id.canhan:
                        layoutchinh.setCurrentItem(2);

                }
                return true;
            }
        });
        layoutchinh.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position)
                {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home);

                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.giaodich);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.canhan);
                        break;
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new viewPager2_Main(MainActivity.this,user,dsWallets());
        layoutchinh.setAdapter(adapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
    public ArrayList<Wallets> dsWallets()
    {
        return walletsDAO.getAll(user);

    }
    public ArrayList<Transactions> getAll(Wallets wallets)
    {
        return transactionsDAO.getAll(wallets);
    }
    public void goiThu(Integer chimuc)
    {
        Intent intent = new Intent(MainActivity.this,themgiaodich.class);
        intent.putExtra("chimuc",chimuc);
        startActivity(intent);

    }
    public void thongke()
    {
        startActivity(new Intent(MainActivity.this,ChartActivity2.class));
    }
    public void menu_(View view, Transactions transactions)
    {
        PopupWindow popupMenu = new PopupWindow(MainActivity.this);
        View layout = getLayoutInflater().inflate(R.layout.menu_dialog,null);
        popupMenu.setContentView(layout);
        popupMenu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupMenu.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupMenu.setOutsideTouchable(true);
        popupMenu.setFocusable(true);
        popupMenu.showAsDropDown(view,view.getWidth(),0);
        TextView btn_capnhat = layout.findViewById(R.id.capnhat);
        TextView btn_xoa = layout.findViewById(R.id.xoa);
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_capnhat(transactions);
                popupMenu.dismiss();
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoane(transactions.getId());
                Wallets wallets5 = ((frm_giaodich) Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("f1"))).wallets_get;
                ((frm_giaodich) Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("f1"))).adapter_lv.updateData(getAll(wallets5));
                popupMenu.dismiss();
            }
        });
    }
    public void xoane(Integer id)
    {
        transactionsDAO.xoa(id);
    }
    public void dialog_capnhat(Transactions transactions)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_themgiaodich,null);
        ImageView thoat = view.findViewById(R.id.imageView2);
        Button btn_them = view.findViewById(R.id.btn_them);
        btn_them.setText("Cập nhật");
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        TextView title = view.findViewById(R.id.title_ne);
        title.setText("Cập nhật");
        Spinner dsvi = view.findViewById(R.id.dsvi);
        dswallets_spinner apdater_vi = new dswallets_spinner(MainActivity.this,dsWallets());
        dsvi.setAdapter(apdater_vi);
        dsvi.setSelection(transactions.getWallets_id()-1);
        Spinner ds_muc = view.findViewById(R.id.dsdanhmuc);
        categoriesDAO categoriesDAO = new categoriesDAO(MainActivity.this);
        ArrayList<Categories> ds_ca = categoriesDAO.getAll();
        spnierds_muc apdater_muc = new spnierds_muc(MainActivity.this,ds_ca);
        ds_muc.setAdapter(apdater_muc);
        ds_muc.setSelection(transactions.getCategory_id()-1);
        EditText money = view.findViewById(R.id.nhaptien);
        money.setText(String.valueOf(transactions.getAmount()));
        ngay = view.findViewById(R.id.nhapngay);
        gio = view.findViewById(R.id.nhapgio);
        Date date1 = new Date(transactions.getDate());
        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                myCalendar.set(Calendar.HOUR_OF_DAY,i);
                myCalendar.set(Calendar.MINUTE,i1);
                updateLabel2();
            }
        };
        gio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity.this,time,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE),true).show();
            }
        });
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        myCalendar.setTime(date1);
        updateLabel();
        updateLabel2();

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        EditText note = view.findViewById(R.id.nhapnote);
        note.setText(transactions.getNotes());
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLabel();
                updateLabel2();
                Categories categories = (Categories) ds_muc.getSelectedItem();
                Wallets wallets2 =(Wallets) dsvi.getSelectedItem();
                Transactions trams = new Transactions(transactions.getId(),transactions.getType(),Double.parseDouble(money.getText().toString()),categories.getId(),wallets2.getId(),note.getText().toString(),time_ne);
                Boolean kq = transactionsDAO.capnhat(trams);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View view2 = inflater.inflate(R.layout.diaglog_thongbao,null);
                builder.setView(view2);
                GifImageView hinh = view2.findViewById(R.id.hinh);
                TextView noidung = view2.findViewById(R.id.noidung);
                Button btn_ok = view2.findViewById(R.id.comfirm);
                if(!kq)
                {
                    noidung.setText("Thật bại");
                    hinh.setImageResource(R.mipmap.no);
                }
                AlertDialog alertDialog2 = builder.create();
                alertDialog2.getWindow().getAttributes().windowAnimations = R.style.animastion;
                alertDialog2.show();
                Wallets wallets5 = ((frm_giaodich) Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("f1"))).wallets_get;
                ((frm_giaodich) Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("f1"))).adapter_lv.updateData(getAll(wallets5));
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(kq)
                        {
                            alertDialog2.dismiss();
                            alertDialog.dismiss();
                        }
                        else{
                            alertDialog2.dismiss();
                        }
                    }
                });
            }
        });
    }
    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ngay.setText(dateFormat.format(myCalendar.getTime()));
        time_ne = myCalendar.getTimeInMillis();
    }
    private void updateLabel2(){
        String myFormat="HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        gio.setText(dateFormat.format(myCalendar.getTime()));
        time_ne = myCalendar.getTimeInMillis();

    }
    public User getUSER_ne()
    {
        return user;
    }
    public void capnhatuser(User user)
    {
        boolean yes = userDao.capnhat(user);
        if(yes)
        {

        }
    }
    public void reloadData()
    {
        onResume();
    }
}