package com.example.lazylunch;

public class RecipeModel {
    String name;
    String description;
    String imageAddress;

    RecipeModel(String name, String description, String imageAddress){
        this.name = name;
        this.description = description;
        this.imageAddress = imageAddress;
    }
}
