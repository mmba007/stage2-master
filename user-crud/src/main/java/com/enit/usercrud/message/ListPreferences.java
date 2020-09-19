package com.enit.usercrud.message;

import java.util.List;

public class ListPreferences {
    private List<Category> newPreferences;

    public List<Category> getNewPreferences() {
        return newPreferences;
    }

    public ListPreferences() {
    }

    public void setNewPreferences(List<Category> newPreferences) {
        this.newPreferences = newPreferences;
    }
}
