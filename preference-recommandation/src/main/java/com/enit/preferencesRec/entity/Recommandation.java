package com.enit.preferencesRec.entity;

import java.io.Serializable;

public class Recommandation implements Serializable {

	private Ad ad;

//	private String algoId;

//	public Recommandation(Ad ad, String algoId) {
//		super();
//		this.ad = ad;
//		this.algoId = algoId;
//	}

//    public void setUsername(String username) {
//        this.username = username;
//    }

//	public String getAlgoId() {
//		return algoId;
//	}
//
	public Recommandation(Ad ad) {
//		this.algoId="Preferences-Recommandation";
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

}
