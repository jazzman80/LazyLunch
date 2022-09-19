package com.example.lazylunch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Networking extends Thread {
    StringBuilder content;

    @Override
    public void run() {
        content = new StringBuilder();

        try {
            URL url = new URL("https://www.themealdb.com/api/json/v1/1/random.php");

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
        } catch (Exception exception) {

            exception.getStackTrace();
        }


    }
}
