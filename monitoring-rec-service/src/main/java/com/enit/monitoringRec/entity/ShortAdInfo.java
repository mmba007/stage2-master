package com.enit.monitoringRec.entity;

import java.io.Serializable;
import java.util.Date;

public class ShortAdInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adId;
	private Date timeOfRecommandation;
	private String algoId;
	private String username;

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public Date getTimeOfRecommandation() {
		return timeOfRecommandation;
	}

	public void setTimeOfRecommandation(Date timeOfRecommandation) {
		this.timeOfRecommandation = timeOfRecommandation;
	}

	public String getAlgoId() {
		return algoId;
	}

	public void setAlgoId(String algoId) {
		this.algoId = algoId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ShortAdInfo [adId=" + adId + ", timeOfRecommandation=" + timeOfRecommandation + ", algoId=" + algoId
				+ ", username=" + username + "]";
	}

	public ShortAdInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShortAdInfo(String adId, String algoId, String username) {
		super();
		this.adId = adId;
		this.timeOfRecommandation = new Date();
		this.algoId = algoId;
		this.username = username;
	}

}
