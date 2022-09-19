package com.example.lazylunch;

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
