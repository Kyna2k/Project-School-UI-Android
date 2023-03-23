package com.example.assignment.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignment.model.Categories;
import com.example.assignment.model.Wallets;
import com.example.assignment.sqlite.SQLite;

import java.util.ArrayList;

public class categoriesDAO {
    private SQLite sqLite;
    public categoriesDAO(Context context)
    {
        sqLite = SQLite.getSqlite(context);
    }
    public ArrayList<Categories> getAll()
    {
        ArrayList<Categories> list = new ArrayList<>();
        SQLiteDatabase database = sqLite.getReadableDatabase();
        String sql = "Select id,name,DESCRIPTION,IMAGES from CATEGORIES";
        database.beginTransaction();
        Cursor cursor = database.rawQuery(sql,null);
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    Integer id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String DESCRIPTION = cursor.getString(2);
                    String IMAGES = cursor.getString(3);
                    list.add(new Categories(id,name,DESCRIPTION,IMAGES));
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
    public Categories getCategories(Integer id1)
    {
        Categories categories = new Categories();
        SQLiteDatabase database = sqLite.getReadableDatabase();
        String sql = "Select name,DESCRIPTION,IMAGES from CATEGORIES where id = ?";
        database.beginTransaction();
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(id1)});
        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    categories.setName(cursor.getString(0));
                    categories.setDescription(cursor.getString(1));
                    categories.setImages(cursor.getString(2));
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
        return categories;
    }

}
