package com.example.lazylunch;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RemoteDataSource implements RemoteDataSourceInterface {

    @Override
    public String getRandomRecipe() {
        Networking networking = new Networking();
        try {
            networking.start();
            networking.join();
        } catch (Exception exception) {
            exception.getStackTrace();
        }

        return String.valueOf(networking.content);
    }
}
