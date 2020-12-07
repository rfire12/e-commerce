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

    public ArrayList<Product> getProductsForListing() {
        ArrayList<Product> products = new ArrayList<>();
        openConnection();

        Cursor cursor = database.query("products", new String[]{"_id", "name", "description", "price", "image", "categoryId"}, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            products.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getString(4), cursor.getInt(5)));
            cursor.moveToNext();
        }

        cursor.close();

        return products;
    }

    public ArrayList<Category> getCategoriesForListing() {
        ArrayList<Category> categories = new ArrayList<>();
        openConnection();
        Cursor cursor = database.query("categories", new String[]{"_id", "name", "image"}, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            categories.add(new Category(cursor.getColumnIndex("_id"), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }

        cursor.close();

        return categories;
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

    public void updateProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("description", product.getDescription());
        contentValues.put("price", product.getPrice());
        contentValues.put("image", product.getImage());
        database.update("products", contentValues, "_id = " + product.getId(), null);
    }

    public Product getProductById(int id) {
        Cursor cursor = database.rawQuery("select * from products where _id = " + id, null);
        cursor.moveToFirst();
        return new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getString(4), cursor.getInt(5));
    }
}
