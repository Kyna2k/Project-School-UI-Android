package com.example.assignment.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment.R;
import com.example.assignment.adapter.dswallets_spinner;
import com.example.assignment.adapter.gird_chongia;
import com.example.assignment.adapter.spnierds_muc;
import com.example.assignment.model.Categories;
import com.example.assignment.model.Transactions;
import com.example.assignment.model.Wallets;
import com.example.assignment.view.MainActivity;
import com.example.assignment.view.themgiaodich;

import java.util.ArrayList;
import java.util.Locale;

public class frm_thu extends Fragment {
    private static final String Parem = "khongrotmon";
    private EditText tien,ngay,gio,note;
    private Integer trangthai;
    private Spinner spinner,spinner2;
    private Button btn_them;
    private TextView title;
    final Calendar myCalendar= Calendar.getInstance();
    private Wallets wallets2;
    public frm_thu(){}
    public static frm_thu getInstance(Integer trangthai2)
    {
        frm_thu frm_thu = new frm_thu();
        Bundle bundle = new Bundle();
        bundle.putInt(Parem,trangthai2);
        frm_thu.setArguments(bundle);
        return frm_thu;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            trangthai = getArguments().getInt(Parem);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_themgiaodich,container,false);
        tien = view.findViewById(R.id.nhaptien);
        ngay = view.findViewById(R.id.nhapngay);
        gio = view.findViewById(R.id.nhapgio);
        note = view.findViewById(R.id.nhapnote);
        spinner = view.findViewById(R.id.dsvi);
        btn_them = view.findViewById(R.id.btn_them);
        title = view.findViewById(R.id.title_ne);
        ImageView back = view.findViewById(R.id.imageView2);
        GridView gridView = view.findViewById(R.id.grid_giaodich);
        ArrayList<Double> dateGrid = new ArrayList<>();
        dateGrid.add(20000.00);
        dateGrid.add(50000.00);
        dateGrid.add(100000.00);
        dateGrid.add(500000.00);
        dateGrid.add(1000000.00);
        dateGrid.add(2000000.00);
        gird_chongia apdater = new gird_chongia(getActivity(),dateGrid);
        gridView.setAdapter(apdater);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Double tienchon = (Double) adapterView.getAdapter().getItem(i);
                tien.setText(String.valueOf(tienchon));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        if(trangthai == 1)
        {
            title.setText("Nạp Tiền");
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dswallets_spinner adapter = new dswallets_spinner(getActivity(),((themgiaodich)getActivity()).getAllWallet());
        spinner.setAdapter(adapter);
        spinner2 = view.findViewById(R.id.dsdanhmuc);
        spnierds_muc adaper2 = new spnierds_muc(getActivity(),((themgiaodich)getActivity()).getAllCate());
        spinner2.setAdapter(adaper2);

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
                new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


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
                new TimePickerDialog(getActivity(),time,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE),true).show();
            }
        });
        btn_them.setEnabled(false);
        tien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equals(""))
                {
                    btn_them.setEnabled(false);

                }else
                {
                    btn_them.setEnabled(true);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(trangthai == 1)
                {
                    spinner2.setSelection(0);
                    spinner2.setEnabled(false);
                }
                updateLabel();
                updateLabel2();
                Categories categories = (Categories) spinner2.getSelectedItem();
                wallets2 =(Wallets) spinner.getSelectedItem();
                Transactions transactions = new Transactions(trangthai,Double.parseDouble(tien.getText().toString()),categories.getId(),wallets2.getId(),note.getText().toString(),((themgiaodich)getActivity()).getTimene());
                ((themgiaodich)getActivity()).addTrans(transactions);
                Double tienmoi = wallets2.getMoney();
                if(trangthai == 2)
                {
                    wallets2.setMoney(tienmoi - Double.parseDouble(tien.getText().toString()));

                }else
                {
                    wallets2.setMoney(tienmoi + Double.parseDouble(tien.getText().toString()));

                }
                ((themgiaodich)getActivity()).capnhatvi(wallets2);
                ((themgiaodich)getActivity()).dialog();
            }
        });

    }
    private void updateLabel(){
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ngay.setText(dateFormat.format(myCalendar.getTime()));
        ((themgiaodich)getActivity()).Timene(myCalendar.getTimeInMillis());
    }
    private void updateLabel2(){
        String myFormat="HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        gio.setText(dateFormat.format(myCalendar.getTime()));
        ((themgiaodich)getActivity()).Timene(myCalendar.getTimeInMillis());
    }

}
