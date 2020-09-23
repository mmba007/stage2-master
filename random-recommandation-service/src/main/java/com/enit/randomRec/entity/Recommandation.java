package com.enit.randomRec.entity;

import java.io.Serializable;

public class Recommandation implements Serializable {

	private Ad ad;
//	private String algoId;

//    public void setUsername(String username) {
//        this.username = username;
//    }

	public Recommandation(Ad ad) {
//		this.algoId="Random-Recommandation";
		this.ad = ad;
	}

//	public Recommandation(Ad ad, String algoId) {
//		this.ad = ad;
//		this.algoId = algoId;
//	}

//    public String getUsername() {
//        return username;
//    }

	public Ad getAd() {
		return ad;
	}
//
//	public String getAlgoId() {
//		return this.algoId;
//	}

	public Recommandation() {
	}

//    public Recommandation(Ad ad, String username) {
//        this.ad = ad;
//        this.username =username;
//    }

}
