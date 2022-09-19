package com.example.lazylunch;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Repository implements RepositoryInterface {
    RemoteDataSourceInterface remoteDataSource;
    LocalDataSourceInterface localDataSource;
    private RecipeModel currentRecipe;
    private final HashSet<RecipeModel> favorites = new HashSet<RecipeModel>() {
    };

    Repository(RemoteDataSourceInterface remoteDataSource, LocalDataSourceInterface localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
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

            currentRecipe = new RecipeModel(
                    jsonObject.getString("strMeal"),
                    jsonObject.getString("strInstructions"),
                    jsonObject.getString("strMealThumb"),
                    loadImage.image);

        } catch (Exception exception) {
            exception.getStackTrace();
        }

    }

    @Override
    public void removeFromFavorites() {
        favorites.remove(currentRecipe);
    }

    @Override
    public void addToFavorites() {
        favorites.add(currentRecipe);
    }

    @Override
    public HashSet<RecipeModel> getFavorites() {
        return favorites;
    }

    @Override
    public void setCurrentFromFavorites(int id) {
        List<RecipeModel> list = new ArrayList<>(favorites);
        currentRecipe = list.get(id);
    }

    @Override
    public boolean isCurrentFavorite() {
        return favorites.contains(currentRecipe);
    }

    @Override
    public void saveFavorites() {
        StringBuilder jsonString = new StringBuilder("{\"meals\":[");
        List<RecipeModel> list = new ArrayList<>(favorites);
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) jsonString.append(",");
            jsonString.append(list.get(i).toJSON());
        }
        jsonString.append("]}");
        localDataSource.save(jsonString.toString());
    }

    @Override
    public void loadFavorites() {
        String jsonString = localDataSource.load();

        favorites.clear();
        try {
            JSONArray jsonArray = new JSONObject(jsonString).getJSONArray("meals");
            Log.e("HERE", "jsonArray.toString()");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                URL imageUrl = new URL(jsonObject.getString("strMealThumb"));
                LoadImage loadImage = new LoadImage(imageUrl);
                loadImage.start();
                loadImage.join();

                RecipeModel newRecipe = new RecipeModel(
                        jsonObject.getString("strMeal"),
                        jsonObject.getString("strInstructions"),
                        jsonObject.getString("strMealThumb"),
                        loadImage.image);

                favorites.add(newRecipe);
            }
        } catch (Exception exception) {
            exception.getStackTrace();

        }

    }
}
