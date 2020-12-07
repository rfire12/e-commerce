package com.example.e_commerce.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static DatabaseHelper instance;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "ecommerce.db", null, 3);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) instance = new DatabaseHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table categories (_id integer primary key autoincrement, name varchar(128) not null unique, image text not null)");
        sqLiteDatabase.execSQL("create table products (_id integer primary key autoincrement, name varchar(128) not null, description text not null, price float not null, image text not null, categoryId integer, foreign key(categoryId) references categories(_id))");
        sqLiteDatabase.execSQL("create table users (_id integer primary key autoincrement, name varchar(128), username varchar(128), email varchar(128), password varchar(256), contact varchar(128))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists products");
        sqLiteDatabase.execSQL("drop table if exists categories");
        sqLiteDatabase.execSQL("drop table if exists users");
        onCreate(sqLiteDatabase);
    }
}
