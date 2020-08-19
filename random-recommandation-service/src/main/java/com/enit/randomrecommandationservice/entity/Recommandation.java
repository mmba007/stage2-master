package com.enit.randomrecommandationservice.entity;

public class Recommandation {

    private Ad ad;

    public String getRequest_id() {
        return request_id;
    }

    private String recommandation_id;

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    private String request_id;
    public Ad getAd() {
        return ad;
    }

    public Recommandation(Ad ad) {
        this.ad = ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Recommandation() {
    }

    public Recommandation(Ad ad, String recommandation_id) {
        this.ad = ad;
        this.recommandation_id = recommandation_id;
    }

    public void setRecommandation_id(String recommandation_id) {
        this.recommandation_id = recommandation_id;
    }

    public String getRecommandation_id() {
        return recommandation_id;
    }
}
