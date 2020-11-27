package com.enit.randomrecommandationservice.entity;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

import java.util.List;


@Document(collection = "consumer")
public class User {
	


	@Id
	private String username;

	public User(String username) {
		this.username = username;
	}

	private List<String> preferences = new ArrayList<String>();

	private List<String> impPreferences = new ArrayList<String>();

	public void setImpPreferences(List<String> impPreferences) {
		this.impPreferences = impPreferences;
	}

	public List<String> getImpPreferences() {
		return impPreferences;
	}

	public List<String> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<String> preferences) {
		this.preferences = preferences;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User() {
	}

	public User(String username, List<String>preferences,List<String> impPreferences){
		this.username = username;
		this.preferences=preferences;
		this.impPreferences=impPreferences;

	}







}