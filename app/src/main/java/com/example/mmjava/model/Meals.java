package com.example.mmjava.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Meals extends RealmObject {
    @PrimaryKey
    private String strMeal;
    private String strMealThumb;
    private boolean isCompleted = false;

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
