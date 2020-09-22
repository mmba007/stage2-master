package com.enit.usercrud.message;

import java.util.List;

public class ListPreferences {
    private List<String> newPreferences;

    public List<String> getNewPreferences() {
        return newPreferences;
    }

    public ListPreferences() {
    }

    public void setNewPreferences(List<String> newPreferences) {
        this.newPreferences = newPreferences;
    }
}
