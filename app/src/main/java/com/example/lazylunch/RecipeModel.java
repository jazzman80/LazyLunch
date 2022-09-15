package com.example.lazylunch;

import android.graphics.Bitmap;

public class RecipeModel {
    boolean isFavorite = false;
    String name;
    String description;
    Bitmap image;


    RecipeModel(String name, String description, Bitmap image){
        this.name = name;
        this.description = description;
        this.image = image;
    }

}
