package com.app.budgetmanagementapp.helpers;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public static List<String> getCategoriesFromJson(Context context, String jsonFileName) {
        List<String> categories = new ArrayList<>();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(jsonFileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray categoriesArray = jsonObject.getJSONArray("categories");

            for (int i = 0; i < categoriesArray.length(); i++) {
                JSONObject categoryObject = categoriesArray.getJSONObject(i);
                String categoryName = categoryObject.getString("categoryName");
                categories.add(categoryName);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return categories;
    }
}