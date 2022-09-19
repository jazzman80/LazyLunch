package com.example.lazylunch;

public interface LocalDataSourceInterface {
    void save(String stringToSave);

    String load();
}
