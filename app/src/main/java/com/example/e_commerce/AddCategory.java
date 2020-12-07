package com.example.e_commerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class AddCategory extends AppCompatActivity {
    Button image_select, save, cancel;
    ImageView imageCat;
    StorageReference mStorageRef;
    String imgUrl;

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
            // Save category to database
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
                Uri filePath = data.getData();
                imageCat.setImageURI(filePath);

                // Code for showing progressDialog while uploading
                ProgressDialog progressDialog
                        = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                // Uploading image
                StorageReference ref = mStorageRef.child("images/" + UUID.randomUUID().toString());
                ref.putFile(filePath).addOnSuccessListener(taskSnapshot -> {
                    taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(download -> {
                        imgUrl = download.toString();
                        progressDialog.dismiss();
                    });
                }).addOnFailureListener(error -> {
                    // Error, Image not uploaded
                    progressDialog.dismiss();
                    Toast
                            .makeText(AddCategory.this,
                                    "Failed " + error.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                })
                        .addOnProgressListener(taskSnapshot -> {
                            // Progress Listener for loading
                            // percentage on the dialog box
                            double progress
                                    = (100.0
                                    * taskSnapshot.getBytesTransferred()
                                    / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage(
                                    "Uploaded "
                                            + (int) progress + "%");
                        });
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }
}