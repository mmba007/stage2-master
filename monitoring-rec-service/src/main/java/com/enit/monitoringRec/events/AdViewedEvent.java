package com.enit.monitoringRec.events;


import java.util.Date;

public class AdViewedEvent extends Event {

	private String username;

	private String adId;

	private Date timeOfView;




	@Override
	public String toString() {
		return "AdViewedEvent [username=" + username + ", adId=" + adId + ", timeOfView=" + timeOfView + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public Date getTimeOfView() {
		return timeOfView;
	}

	public void setTimeOfView(Date time) {
		this.timeOfView = time;
	}

	public AdViewedEvent() {
	}

	public AdViewedEvent(String username, String adId){
		this.username = username;
		this.adId=adId;
		this.timeOfView=new Date();

	}
}
