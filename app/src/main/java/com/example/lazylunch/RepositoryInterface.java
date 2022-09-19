package com.example.lazylunch;

import java.util.HashSet;
import java.util.Set;

interface RepositoryInterface {
    RecipeModel getCurrentRecipe();

    void loadNextRecipe();

    void removeFromFavorites();

    void addToFavorites();

    HashSet<RecipeModel> getFavorites();

    void setCurrentFromFavorites(int id);

    boolean isCurrentFavorite();

    void saveFavorites();

    void loadFavorites();
}
