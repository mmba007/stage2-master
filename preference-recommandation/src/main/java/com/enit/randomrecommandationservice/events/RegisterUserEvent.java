package com.enit.randomrecommandationservice.events;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RegisterUserEvent extends Event {

	private String username;

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

	public RegisterUserEvent() {

		super(EventName.REGISTER_USER);}

	public RegisterUserEvent(String username, List<String>preferences,List<String> impPreferences){
		super(EventName.REGISTER_USER);
		this.username = username;
		this.preferences=preferences;
		this.impPreferences=impPreferences;

	}
}
