package com.temr1.Lesson2_3_maven;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FactsFromAPI{
    private final static Gson gson = new Gson();

    public static String getFact(){
        try{
            String apiUrl = "https://catfact.ninja/fact?max_length=140";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }
                in.close();

                String json = response.toString();

                return json;
            }
            else{
                System.out.println("Запит не успішний. Код відповіді: " + responseCode);
            }

            connection.disconnect();

        }
        catch (Exception e){
            System.out.println("Вибачте, виникла помилка!");
        }
        return null;
    }

    public static CatFact readCatFactFromJson(String json) {
        return gson.fromJson(json, new TypeToken<CatFact>() {}.getType());
    }

    public static String saveCatFactToJson(CatFact catFact) {
        return gson.toJson(catFact, new TypeToken<CatFact>() {}.getType());
    }
}