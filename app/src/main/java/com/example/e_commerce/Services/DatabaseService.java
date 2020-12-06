package com.example.e_commerce.Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_commerce.Helpers.DatabaseHelper;

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
        database = databaseHelper.getWritableDatabase();
    }

    public void closeConnection() {
        databaseHelper.close();
    }
}
