package com.enit.usercrud.events;


import com.enit.usercrud.model.EventName;

import java.util.List;
import java.util.Set;

public class UpdateUserEvent extends Event {



	private String username;
	private List<String> preferences;
	private List<String> impPreferences;

	public List<String> getPreferences() {
		return preferences;
	}

	public List<String> getImpPreferences() {
		return impPreferences;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}




	public UpdateUserEvent(String username,List<String> preferences,List<String> impPreferences) {
		super(EventName.UPDATE_USER);
		this.username = username;
		this.preferences=preferences;
		this.impPreferences=impPreferences;

	}

	public UpdateUserEvent() {

	}
}
