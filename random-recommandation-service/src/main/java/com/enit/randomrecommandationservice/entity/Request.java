package com.enit.randomrecommandationservice.entity;

public class Request {
    private String requestId;;
    private String username;
    private Double lon ;
    private Double lar;

    public Double getLon() {
        return lon;
    }

    public Double getLar() {
        return lar;
    }

    public Request(String requestId, String username, Double lan, Double lar) {
        this.requestId = requestId;
        this.username = username;
        this.lon = lon;
        this.lar=lar;
    }



    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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
                "requestId='" + requestId + '\'' +
                ", username='" + username + '\'' +
                ", lon=" + lon +
                ", lar=" + lar +
                '}';
    }

    public Request() {
    }

    public Request(String request_id, String username) {
        this.requestId = request_id;
        this.username = username;
    }
}
