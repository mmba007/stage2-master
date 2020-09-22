package com.enit.serving2.entity;

import java.io.Serializable;

public class Recommandation implements Serializable {

    private Ad ad;
//    private String username;



    public Recommandation(Ad ad) {
        this.ad = ad;
    }

//    public String getUsername() {
//        return username;
//    }

    public Ad getAd() {
        return ad;
    }

    public Recommandation() {
    }

//    public Recommandation(Ad ad, String username) {
//        this.ad = ad;
//        this.username =username;
//    }
//

}
