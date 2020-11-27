package com.enit.randomrecommandationservice.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UsersProfiles")
public class UserProfile {

	@Id
	private String username;

	@GeoSpatialIndexed
	private double[] location;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double[] getLocation() {
		return this.location;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public UserProfile(String username, double[] location) {
		this.location = location;
		this.username = username;
	}

	public UserProfile() {

	}

	@Override
	public String toString() {
		return "UserProfile [username=" + username + ", location=" + location + "]";
	}



}