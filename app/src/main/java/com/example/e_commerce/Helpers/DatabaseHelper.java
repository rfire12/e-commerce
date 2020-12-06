package com.example.e_commerce.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static DatabaseHelper instance;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "ecommerce.db", null, 1);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) instance = new DatabaseHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Here goes table creation
        sqLiteDatabase.execSQL("create table categories (_id integer primary key autoincrement, name varchar(256) not null unique, image text)");
        sqLiteDatabase.execSQL("create table products (_id varchar(256) primary key, name varchar(256) not null, description text not null, price float not null, categoryId integer, image text not null, foreign key(categoryId) references categories(_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Here goes table drop
        sqLiteDatabase.execSQL("drop table if exists products");
        sqLiteDatabase.execSQL("drop table if exists categories");
        onCreate(sqLiteDatabase);
    }
}
