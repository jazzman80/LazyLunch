package com.example.lazylunch;

import android.app.Application;

public class LazyLunchApplication extends Application {
    RemoteDataSource remoteDataSource;
    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        remoteDataSource = new RemoteDataSource();
        repository = new Repository(remoteDataSource);
        repository.loadNextRecipe();
    }
}
