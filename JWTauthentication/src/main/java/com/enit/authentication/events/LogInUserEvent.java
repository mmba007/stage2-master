package com.enit.authentication.events;

import com.enit.authentication.model.EventName;


public class LogInUserEvent extends Event {
    private String username;
    private double latitude;
    private double longitude;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LogInUserEvent(String username,double lat,double lon)
    {
            super(EventName.LOGGED_IN_USER);
            this.username = username;
            this.latitude=lat;
            this.longitude=lon;
    }
            
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LogInUserEvent() {

    }
}
