package com.example.lazylunch;

import android.graphics.Bitmap;

import org.json.JSONObject;

public class RecipeModel {
    String name;
    String description;
    String imageAddress;
    Bitmap image;


    RecipeModel(String name, String description, String imageAddress, Bitmap image) {
        this.name = name;
        this.description = description;
        this.imageAddress = imageAddress;
        this.image = image;
    }

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("strMeal", name);
            jsonObject.put("strInstructions", description);
            jsonObject.put("strMealThumb", imageAddress);


        } catch (Exception exception) {
            exception.getStackTrace();
        }
        return jsonObject.toString();
    }

}
