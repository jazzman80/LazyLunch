package com.example.lazylunch;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;

public class Repository implements RepositoryInterface {
    RemoteDataSourceInterface remoteDataSource;
    private RecipeModel currentRecipe = new RecipeModel("Turkey Meatloaf",
            "Heat oven to 180C/160C fan/gas 4. Heat the oil in a large frying pan and cook the onion for 8-10 mins until softened. Add the garlic, Worcestershire sauce and 2 tsp tomato pur\u00e9e, and stir until combined. Set aside to cool.\r\n\r\nPut the turkey mince, egg, breadcrumbs and cooled onion mix in a large bowl and season well. Mix everything to combine, then shape into a rectangular loaf and place in a large roasting tin. Spread 2 tbsp barbecue sauce over the meatloaf and bake for 30 mins.\r\n\r\nMeanwhile, drain 1 can of beans only, then pour both cans into a large bowl. Add the remaining barbecue sauce and tomato pur\u00e9e. Season and set aside.\r\n\r\nWhen the meatloaf has had its initial cooking time, scatter the beans around the outside and bake for 15 mins more until the meatloaf is cooked through and the beans are piping hot. Scatter over the parsley and serve the meatloaf in slices.",
            "https://www.themealdb.com/images/media/meals/ypuxtw1511297463.jpg");

    Repository(RemoteDataSourceInterface remoteDataSource){
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public RecipeModel getCurrentRecipe() {
        return currentRecipe;
    }

    @Override
    public void loadNextRecipe() {
        String jsonString = remoteDataSource.getRandomRecipe();


        try {
            JSONArray jsonArray = new JSONObject(jsonString).getJSONArray("meals");
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            currentRecipe = new RecipeModel(jsonObject.getString("strMeal"),
                    jsonObject.getString("strInstructions"),
                    jsonObject.getString("strMealThumb"));

        } catch (Exception exception) {
            exception.getStackTrace();
        }

    }
}
