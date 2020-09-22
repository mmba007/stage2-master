package com.enit.monitoringRec.entity;

import java.io.Serializable;

public class Recommandation implements Serializable {

    private Ad ad;
    private String recommandationAlgorithmId;
//    private String username;

    public Recommandation() {
    }

    public Recommandation(Ad ad) {
        this.ad = ad;
    }

    public Recommandation(Ad ad, String idAlgo) {
        this.ad = ad;
        this.recommandationAlgorithmId = idAlgo;
    }

//    public String getUsername() {
//        return username;
//    }

    public Ad getAd() {
        return ad;
    }

    public String getRecommandationAlgorithmId() {
        return recommandationAlgorithmId;
    }



//    public Recommandation(Ad ad, String username) {
//        this.ad = ad;
//        this.username =username;
//    }
//

}
