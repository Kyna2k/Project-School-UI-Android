package com.example.assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignment.model.User;
import com.example.assignment.sqlite.SQLite;

public class userDao {
    private SQLite sqLite;
    public userDao(Context context)
    {
        sqLite = SQLite.getSqlite(context);
    }
    public boolean dangky(User user)
    {
        boolean result =true;
        SQLiteDatabase database = sqLite.getWritableDatabase();
        database.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("username",user.getUsername());
            contentValues.put("password",user.getPasswork());
            contentValues.put("name",user.getName());
            contentValues.put("avatar",user.getAvatar());
            contentValues.put("address",user.getAddress());
            contentValues.put("email",user.getEmail());
            long row =database.insertOrThrow("USERS",null,contentValues);
            database.setTransactionSuccessful();
            result = row >= 1;
        }
        catch (Exception e)
        {
            Log.e("LoiDangky", e +"" );
        }finally {
            database.endTransaction();
        }
        return result;
    }

    public boolean capnhat(User user)
    {
        Boolean result =true;
        SQLiteDatabase database = sqLite.getWritableDatabase();
        database.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",user.getName());
            contentValues.put("avatar",user.getAvatar());
            contentValues.put("address",user.getAddress());
            contentValues.put("email",user.getEmail());
            long row =database.update("USERS",contentValues,"id = ?",new String[]{String.valueOf(user.getId())});
            if(row >= 1)
            {
                database.setTransactionSuccessful();
                result = true;
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            Log.e("LoiDangky", e +"" );
            result = false;
        }finally {
            database.endTransaction();
        }
        return result;
    }
    public boolean dangnhap(User user)
    {
        boolean result =true;
        SQLiteDatabase database = sqLite.getReadableDatabase();
        database.beginTransaction();
        String sql = "Select id, password from users where username = ?";
        Cursor cursor =database.rawQuery(sql,new String[]{user.getUsername()});

        try {
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    String password =cursor.getString(1);
                    if(password.equals(user.getPasswork()))
                    {
                        result = true;
                    }
                    else {
                        result = false;
                    }
                    cursor.moveToNext();
                }
                database.setTransactionSuccessful();
            }
            else {
                throw new Exception();
            }
        }catch (Exception e)
        {
            result = false;
            Log.e("loidangnhap", e + "" );
        }finally {
            if(!cursor.isClosed())
            {
                cursor.isClosed();
            }
            database.endTransaction();
        }
        return result;
    }
    public boolean checktaikhoang(User user)
    {
        boolean result =true;
        SQLiteDatabase database = sqLite.getReadableDatabase();
        database.beginTransaction();
        String sql = "Select username from users where username = ?";
        Cursor cursor =database.rawQuery(sql,new String[]{user.getUsername()});

            try {
                if(cursor.getCount() > 0)
                {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast())
                    {
                        String userName =cursor.getString(0);
                        cursor.moveToNext();
                    }
                    database.setTransactionSuccessful();
                    result = true;
                }
                else {
                    throw new Exception();

                }
            }catch (Exception e)
            {
                result = false;
                Log.e("loidangnhap", e + "" );
            }finally {
                if(!cursor.isClosed())
                {
                    cursor.isClosed();
                }
                database.endTransaction();
            }
            return result;
    }
    public User getUser(String username1)
    {
        SQLiteDatabase database = sqLite.getReadableDatabase();
        database.beginTransaction();
        String sql = "Select id,username,password,avatar,name,email,address from users where username = ?";
        User user = new User();
        Cursor cursor =database.rawQuery(sql,new String[]{username1});
        if(cursor.getCount() > 0)
        {
            try {
                if(cursor.moveToFirst())
                {
                    while (!cursor.isAfterLast())
                    {
                        user.setId(cursor.getInt(0));
                        user.setUsername(cursor.getString(1));
                        user.setPasswork(cursor.getString(2));
                        user.setAvatar(cursor.getString(3));
                        user.setName(cursor.getString(4));
                        user.setEmail(cursor.getString(5));
                        user.setAddress(cursor.getString(6));
                        cursor.moveToNext();
                    }
                    database.setTransactionSuccessful();
                }
            }catch (Exception e)
            {
                Log.e("loidangnhap", e + "" );
            }finally {
                if(!cursor.isClosed())
                {
                    cursor.isClosed();
                }
                database.endTransaction();
            }
        }
        return user;
    }

}
