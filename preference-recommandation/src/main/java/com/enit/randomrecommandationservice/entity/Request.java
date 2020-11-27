package com.enit.randomrecommandationservice.entity;

import java.util.List;

public class Request {
    private List<String> preferences;
    private String username;
    private Double lon ;
    private Double lar;
    private  String requestID;

    public Request(List<String> preferences, String username, Double lon, Double lar, List<String> imPreferences,String requestID) {
        this.preferences = preferences;
        this.username = username;
        this.lon = lon;
        this.lar = lar;
        this.imPreferences = imPreferences;
        this.requestID=requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setImPreferences(List<String> imPreferences) {
        this.imPreferences = imPreferences;
    }
    private List<String> imPreferences;


    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public List<String> getImPreferences() {
        return imPreferences;
    }

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
