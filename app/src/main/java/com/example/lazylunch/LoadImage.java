package com.example.lazylunch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.URL;

public class LoadImage extends Thread {
    Bitmap image;
    URL imageUrl;

    LoadImage(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void run() {
        try {
            image = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }
}
