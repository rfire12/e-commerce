package com.example.e_commerce.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_commerce.Helpers.DatabaseHelper;
import com.example.e_commerce.Models.Category;
import com.example.e_commerce.Models.Product;

import java.util.ArrayList;
import java.util.List;

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

        contentValues.put("name", product.getName());
        contentValues.put("description", product.getDescription());
        contentValues.put("price", product.getPrice());
        contentValues.put("image", product.getImage());
        contentValues.put("categoryId", product.getCategoryId());

        database.insert("products", null, contentValues);
    }

    public void createCategory(Category category) {
        openConnection();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", category.getName());
        contentValues.put("image", category.getImage());

        database.insert("categories", null, contentValues);
    }

    public Cursor getProducts() {
        openConnection();
        Cursor cursor = database.query("products", new String[]{"_id", "name", "description", "price", "image"}, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }

    public List<String> getCategories() {
        openConnection();
        List<String> list = new ArrayList<>();

        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery("select * from categories", null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

    public void deleteProduct(int _id) {
        database.delete("products", "_id = " + _id, null);
    }

    public int updateProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("description", product.getDescription());
        contentValues.put("price", product.getPrice());
        contentValues.put("image", product.getImage());
        return database.update("products", contentValues, "_id = " + product.getId(), null);
    }
}
