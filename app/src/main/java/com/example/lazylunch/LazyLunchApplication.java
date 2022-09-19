package com.example.lazylunch;

import android.app.Application;
import android.content.Context;

public class LazyLunchApplication extends Application {
    public static final String APP_PREFERENCES = "settings";

    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource;
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        remoteDataSource = new RemoteDataSource();
        localDataSource = new LocalDataSource(getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE));
        repository = new Repository(remoteDataSource, localDataSource);
        repository.loadNextRecipe();
        repository.loadFavorites();
    }
}
