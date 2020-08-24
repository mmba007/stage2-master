package com.enit.servingservice.entity;

import java.io.Serializable;

public class ConsumerRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String requestId;
	private String username;
	private Double lar;
	private Double lon;


	public ConsumerRequest() {
		super();
	}

	public Double getLar() {
		return lar;
	}

	public Double getLon() {
		return lon;
	}

	public String getUsername() {
		return username;
	}

	public ConsumerRequest(String requestId, String username, Double lar, Double lon) {
		this.requestId = requestId;
		this.username = username;
		this.lar = lar;
		this.lon = lon;
	}

	public ConsumerRequest(String user_id, String requestId, Double[] location) {

		super();
		this.requestId = requestId;
		this.username = user_id;


	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "{ \"user_id\":\"" + username + "\", \"requestId\":" + requestId + "}";
	}

	public String getRequestId() {
		return requestId;
	}





}
