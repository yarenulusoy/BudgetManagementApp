package com.app.budgetmanagementapp.helpers;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public interface ExchangeService {
    String JSON_FILE_PATH = "investment.json";

    default <T> List<T> getDataList(Class<T> clazz) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(JSON_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }

            Gson gson = new Gson();
            MockDataWrapper<T> dataWrapper = gson.fromJson(jsonStringBuilder.toString(), MockDataWrapper.class);

            return dataWrapper.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
class MockDataWrapper<T> {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}