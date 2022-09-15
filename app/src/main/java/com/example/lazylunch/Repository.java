package com.example.lazylunch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;

public class Repository implements RepositoryInterface {
    RemoteDataSourceInterface remoteDataSource;
    private RecipeModel currentRecipe;

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
            URL imageUrl = new URL(jsonObject.getString("strMealThumb"));
            LoadImage loadImage = new LoadImage(imageUrl);
            loadImage.start();
            loadImage.join();

            currentRecipe = new RecipeModel(jsonObject.getString("strMeal"),
                    jsonObject.getString("strInstructions"),
                    loadImage.image);

        } catch (Exception exception) {
            exception.getStackTrace();
        }

    }

    @Override
    public void removeFromFavorites() {
        currentRecipe.isFavorite = !currentRecipe.isFavorite;
        Log.e("TEST", currentRecipe.name + " removed from favorites");
    }

    @Override
    public void addToFavorites() {
        currentRecipe.isFavorite = !currentRecipe.isFavorite;
        Log.e("TEST", currentRecipe.name + " added to favorites");
    }
}
