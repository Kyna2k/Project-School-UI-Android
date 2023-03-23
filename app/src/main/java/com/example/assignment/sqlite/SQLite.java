package com.example.assignment.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "2tr6";
    private static final Integer DB_VERSION = 1;
    private static SQLite newSqLite;
    public static synchronized SQLite getSqlite(Context context)
    {
        if(newSqLite == null)
        {
            newSqLite = new SQLite(context);
        }
        return newSqLite;
    }

    public SQLite(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlUSER = "Create table if not exists users (id integer primary key autoincrement , username text unique, password text, name text, avatar text,address text,email text)";
        sqLiteDatabase.execSQL(sqlUSER);
        String sqlWallets = "Create table if not exists WALLETS(id integer primary key autoincrement, type text,images text ,money real, description text,id_user Integer, FOREIGN KEY(ID_USER) REFERENCES USERS(ID))";
        sqLiteDatabase.execSQL(sqlWallets);
            String sqlcCategories = "CREATE TABLE IF NOT EXISTS CATEGORIES(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT,IMAGES TEXT)";
        sqLiteDatabase.execSQL(sqlcCategories);
        String sqlTRANSACTIONS = "CREATE TABLE IF NOT EXISTS TRANSACTIONS(ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPE INTEGER, AMOUNT REAL, CATEGORY_ID INTEGER, WALLETS_ID INTEGER, NOTES TEXT, DATE REAL,  FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID), FOREIGN KEY(WALLETS_ID) REFERENCES WALLETS(ID) )";
        sqLiteDatabase.execSQL(sqlTRANSACTIONS);
        //insert USERS
        sqLiteDatabase.execSQL("insert into users (username, password, name,avatar, address, email) values ('username1', 'huy123', 'Carolus','avatar1', '8699 Union Circle', 'cjerdan0@blogs.com');");
        sqLiteDatabase.execSQL("insert into users (username, password, name,avatar ,address, email) values ('username2', 'huy123', 'Bennie', 'avatar2','6 Grayhawk Center', 'bbendik1@businessinsider.com');");
        //insert Wallets
        sqLiteDatabase.execSQL("insert into wallets (type, images, money, description, id_user) values ('Ví tiêu dùng', 'foag0', 104000, 'qhoneywood0', 1);");
        sqLiteDatabase.execSQL("insert into wallets (type, images, money, description, id_user) values ('Ví khoản vay', 'lgregorio1', 427, 'cshrimplin1', 1);");
        sqLiteDatabase.execSQL("insert into wallets (type, images, money, description, id_user) values ('Ví Khoản nợ', 'lgregorio1', 427, 'cshrimplin1', 1);");
        sqLiteDatabase.execSQL("insert into wallets (type, images, money, description, id_user) values ('Ví tiết kiệm', 'lgregorio1', 427, 'cshrimplin1', 1);");
        //insert Categories
        sqLiteDatabase.execSQL("insert into CATEGORIES (name, DESCRIPTION , IMAGES) values ('Nạp tiền', '','thu');");
        sqLiteDatabase.execSQL("insert into CATEGORIES (name, DESCRIPTION , IMAGES) values ('Xăng', 'Tiền xăng','ic_gas');");
        sqLiteDatabase.execSQL("insert into CATEGORIES (name, DESCRIPTION , IMAGES) values ('Mua sắm', '','muasam');");
        sqLiteDatabase.execSQL("insert into CATEGORIES (name, DESCRIPTION , IMAGES) values ('Hóa đơn', '','hoadon');");
        sqLiteDatabase.execSQL("insert into CATEGORIES (name, DESCRIPTION , IMAGES) values ('Học phí', '','hocphi');");
        sqLiteDatabase.execSQL("insert into CATEGORIES (name, DESCRIPTION , IMAGES) values ('Tiền nhà', '','tiennha');");
        sqLiteDatabase.execSQL("insert into CATEGORIES (name, DESCRIPTION , IMAGES) values ('Internet', '','internet');");
        sqLiteDatabase.execSQL("insert into CATEGORIES (name, DESCRIPTION , IMAGES) values ('Du lịch', '','dulich');");


        //insert TRANSACTIONS
        sqLiteDatabase.execSQL("insert into TRANSACTIONS (TYPE, AMOUNT, CATEGORY_ID, WALLETS_ID, NOTES, DATE) values (1, 60.23, 1, 1, 'jovise0', 24746789);");
        sqLiteDatabase.execSQL("insert into TRANSACTIONS (TYPE, AMOUNT, CATEGORY_ID, WALLETS_ID, NOTES, DATE) values (2, 39.19, 2, 1, 'cnoweak1', 45905772);");
        sqLiteDatabase.execSQL("insert into TRANSACTIONS (TYPE, AMOUNT, CATEGORY_ID, WALLETS_ID, NOTES, DATE) values (1, 6.9, 3, 1, 'arichold2', 67607138);");
        sqLiteDatabase.execSQL("insert into TRANSACTIONS (TYPE, AMOUNT, CATEGORY_ID, WALLETS_ID, NOTES, DATE) values (2, 59.5, 4, 1, 'apickerin3', 31491898);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion != newVersion)
        {
            sqLiteDatabase.execSQL("drop table if exists  TRANSACTIONS");
            sqLiteDatabase.execSQL("drop table if exists  WALLETS");
            sqLiteDatabase.execSQL("drop table if exists  CATEGORIES");
            sqLiteDatabase.execSQL("drop table if exists  users");
            onCreate(sqLiteDatabase);
        }

    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
