package com.example.e_commerce;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import retrofit2.Retrofit;

import java.io.File;
import java.util.UUID;

public class AddCategory extends AppCompatActivity {

    Button image_select, save, cancel;
    ImageView imageCat;
    StorageReference mStorageRef;
    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_category);
        imageCat = findViewById(R.id.create_cat_img);
        image_select = findViewById(R.id.select_img_btn);
        image_select.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        });
        save.setOnClickListener(v -> {
            StorageReference ref = mStorageRef.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath).addOnSuccessListener(taskSnapshot -> {
                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(download -> {
                    // Store image URL
                });
            });
        });
        cancel.setOnClickListener(v -> {

        });

        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == 1) {
                    filePath = data.getData();
                    imageCat.setImageURI(filePath);
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }
}