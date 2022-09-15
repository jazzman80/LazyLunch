package com.example.lazylunch;

interface RepositoryInterface {
    RecipeModel getCurrentRecipe();
    void loadNextRecipe();

    void removeFromFavorites();

    void addToFavorites();
}
