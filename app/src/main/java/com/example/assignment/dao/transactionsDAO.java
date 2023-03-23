package com.example.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignment.model.Categories;
import com.example.assignment.model.Transactions;
import com.example.assignment.model.Wallets;
import com.example.assignment.sqlite.SQLite;

import java.util.ArrayList;

public class transactionsDAO {
    private SQLite sqLite;
    public transactionsDAO(Context context)
    {
        sqLite = SQLite.getSqlite(context);
    }
    public ArrayList<Transactions> getAll(Wallets wallets)
    {
        ArrayList<Transactions> list = new ArrayList<>();
        SQLiteDatabase database = sqLite.getReadableDatabase();
        String sql = "Select id, type, amount, CATEGORY_ID,NOTES,DATE from  TRANSACTIONS where WALLETS_ID = ?";
        database.beginTransaction();
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(wallets.getId())});
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    Integer id = cursor.getInt(0);
                    Integer type =cursor.getInt(1);
                    Double amount = cursor.getDouble(2);
                    Integer CATEGORY_ID = cursor.getInt(3);
                    String NOTES = cursor.getString(4);
                    Long date = cursor.getLong(5);
                    list.add(new Transactions(id,type,amount,CATEGORY_ID,wallets.getId(),NOTES,date));
                    cursor.moveToNext();
                }
            }
            database.setTransactionSuccessful();
        }catch (Exception e)
        {
            Log.e("loi_hien_vi", e + "" );
        }finally {
            database.endTransaction();
        }
        return list;
    }
    public Boolean addTrans(Transactions transactions)
    {
        boolean result = true;
        SQLiteDatabase database = sqLite.getWritableDatabase();
        database.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put("Type",transactions.getType());
            values.put("AMOUNT",transactions.getAmount());
            values.put("CATEGORY_ID",transactions.getCategory_id());
            values.put("WALLETS_ID",transactions.getWallets_id());
            values.put("NOTES",transactions.getNotes());
            values.put("DATE",transactions.getDate());
            long row = database.insertOrThrow("TRANSACTIONS",null,values);
            database.setTransactionSuccessful();
            result = row >=1;
        }catch (Exception e)
        {
            Log.e("loigiaodich", e+"" );
        }finally {
            database.endTransaction();
        }
        return result;
    }
    public Boolean capnhat(Transactions transactions)
    {
        boolean result = true;
        SQLiteDatabase database = sqLite.getWritableDatabase();
        database.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put("Type",transactions.getType());
            values.put("AMOUNT",transactions.getAmount());
            values.put("CATEGORY_ID",transactions.getCategory_id());
            values.put("WALLETS_ID",transactions.getWallets_id());
            values.put("NOTES",transactions.getNotes());
            values.put("DATE",transactions.getDate());
            long row = database.update("TRANSACTIONS",values,"id = ?",new String[]{String.valueOf(transactions.getId())});
            database.setTransactionSuccessful();
            result = row >=1;
        }catch (Exception e)
        {
            Log.e("loigiaodich", e+"" );
        }finally {
            database.endTransaction();
        }
        return result;
    }
    public boolean xoa(Integer id)
    {
        boolean result = true;
        SQLiteDatabase database = sqLite.getWritableDatabase();
        database.beginTransaction();
        try{
            long row= database.delete("TRANSACTIONS","id = ?", new String[]{String.valueOf(id)});
            result = row >=1;
            if(!result)
            {
                throw new Exception();
            }
            database.setTransactionSuccessful();
        }catch (Exception e)
        {
            Log.e("loixoa", e + "" );
        }finally {
            database.endTransaction();
        }
        return result;
    }
}
