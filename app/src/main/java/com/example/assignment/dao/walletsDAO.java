package com.example.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignment.model.User;
import com.example.assignment.model.Wallets;
import com.example.assignment.sqlite.SQLite;

import java.util.ArrayList;

public class walletsDAO {
    private SQLite sqLite;
    public walletsDAO(Context context)
    {
        sqLite = SQLite.getSqlite(context);
    }
    public ArrayList<Wallets> getAll(User user)
    {
        ArrayList<Wallets> list = new ArrayList<>();
        SQLiteDatabase database = sqLite.getReadableDatabase();
        String sql = "Select id, type, images, money, description from WALLETS where id_user = ?";
        database.beginTransaction();
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(user.getId())});
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    Integer id = cursor.getInt(0);
                    String type = cursor.getString(1);
                    String images = cursor.getString(2);
                    Double money = cursor.getDouble(3);
                    String description = cursor.getString(4);
                    list.add(new Wallets(id,type,images,money,description,user.getId()));
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
    public boolean addWallets(Wallets wallet)
    {
        Boolean result = true;
        SQLiteDatabase database = sqLite.getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("type",wallet.getType());
            contentValues.put("images",wallet.getImage());
            contentValues.put("money",wallet.getMoney());
            contentValues.put("description",wallet.getDescription());
            contentValues.put("id_user",wallet.getId_user());
            long row = database.insertOrThrow("WALLETS",null,contentValues);
            database.setTransactionSuccessful();
            result = row >= 1;
        }catch (Exception e)
        {
            Log.e("loithemvi",e + "");
        }finally {
            database.endTransaction();
        }
        return  result;
    }
    public boolean getMoney(Wallets wallet)
    {
        Boolean result = true;
        SQLiteDatabase database = sqLite.getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("money",wallet.getMoney());
            long row = database.update("Wallets", contentValues,"id = ?",new String[]{String.valueOf(wallet.getId())});
            if(row > 0)
            {
                database.setTransactionSuccessful();
                result = true;
            }else
            {
                throw new Exception();
            }
        }catch (Exception e)
        {
            Log.e("loi cap nhat vi", e + "" );
            result = false;
        }finally {
            database.endTransaction();
        }
        return result;
    }
    public Wallets getWallet(Integer id, Integer userId)
    {
        Wallets wallet = new Wallets();
        SQLiteDatabase database = sqLite.getReadableDatabase();
        String sql = "Select type, images, money, description from WALLETS where id_user = ? and id = ?";
        database.beginTransaction();
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(userId),String.valueOf(id)});
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    String type = cursor.getString(1);
                    String images = cursor.getString(2);
                    Double money = cursor.getDouble(3);
                    String description = cursor.getString(4);
                    wallet = new Wallets(id,type,images,money,description,userId);
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
        return wallet;
    }
}
