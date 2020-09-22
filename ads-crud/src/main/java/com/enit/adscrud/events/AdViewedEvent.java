package com.enit.adscrud.events;


import java.util.Date;

public class AdViewedEvent extends Event {

	private String username;

	private String adId;

	private Date timeOfView;




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
		super(EventName.VIEWED_AD);
	}

	public AdViewedEvent(String username, String adId){
		super(EventName.VIEWED_AD);
		this.username = username;
		this.adId=adId;
		this.timeOfView=new Date();

	}
}
