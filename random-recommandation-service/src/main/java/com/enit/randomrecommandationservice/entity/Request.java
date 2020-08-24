package com.enit.randomrecommandationservice.entity;

import java.util.List;

public class Request {
    private List<String> preferences;
    private String username;
    private Double lon ;
    private Double lar;

    public Double getLon() {
        return lon;
    }

    public Double getLar() {
        return lar;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public Request(List<String> preferences, String username, Double lon, Double lar) {
        this.preferences = preferences;
        this.username = username;
        this.lon = lon;
        this.lar = lar;
    }

    public Request(String requestId, String username, Double lan, Double lar) {

        this.username = username;
        this.lon = lon;
        this.lar=lar;
    }




    public void setUsername(String username) {
        this.username = username;
    }



    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Request{" +
                ", username='" + username + '\'' +
                ", lon=" + lon +
                ", lar=" + lar +
                '}';
    }

    public Request() {
    }


}
