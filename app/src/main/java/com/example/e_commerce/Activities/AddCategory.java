package com.example.e_commerce.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.e_commerce.Helpers.Encoding;
import com.example.e_commerce.Models.Category;
import com.example.e_commerce.R;
import com.example.e_commerce.Services.DatabaseService;

import retrofit2.Retrofit;

import java.io.File;
import java.io.InputStream;

public class AddCategory extends AppCompatActivity {
    EditText txtName;
    Button image_select, save, cancel;
    ImageView imageCat;
    Category category = new Category();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        txtName = findViewById(R.id.txtCategoryName);
        imageCat = findViewById(R.id.create_cat_img);
        image_select = findViewById(R.id.select_img_btn);
        image_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseService databaseService = new DatabaseService(AddCategory.this);
                databaseService.createCategory(new Category(txtName.getText().toString(), category.getImage()));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream imageStream = null;

        try {
            imageCat.setImageURI(data.getData());
            imageStream = this.getContentResolver().openInputStream(data.getData());
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            category.setImage(Encoding.encodeToBase64(selectedImage));
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
