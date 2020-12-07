package com.example.e_commerce.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_commerce.Helpers.DatabaseHelper;
import com.example.e_commerce.Models.Product;

public class DatabaseService {
    DatabaseHelper databaseHelper;
    Context context;
    SQLiteDatabase database;

    public DatabaseService(Context context) {
        this.context = context;
        databaseHelper = DatabaseHelper.getInstance(context);

        openConnection();
    }

    public void openConnection() {
        if (databaseHelper == null) DatabaseHelper.getInstance(context);
        database = databaseHelper.getWritableDatabase();
    }

    public void closeConnection() {
        databaseHelper.close();
    }

    public void createProduct(Product product) {
        openConnection();

        ContentValues contentValues = new ContentValues();
    }
}
