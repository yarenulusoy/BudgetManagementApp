package com.app.budgetmanagementapp.model;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("categoryName")
    private String categoryName;

    @SerializedName("id")
    private String id;

    public String getCategoryName() {
        return categoryName;
    }

    public String getId() {
        return id;
    }
}