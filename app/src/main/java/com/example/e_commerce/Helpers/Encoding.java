package com.example.e_commerce.Helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;

public class Encoding {
    public static String encodeToBase64(Bitmap bitmap) {
        Bitmap image = bitmap;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static Bitmap decodeToBitmap(String image) {
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
