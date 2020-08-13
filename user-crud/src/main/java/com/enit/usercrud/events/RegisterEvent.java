package com.enit.usercrud.events;

import java.util.List;

public class RegisterEvent extends Event {
	private String type;
	private String username;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RegisterEvent(String username, String type) {
		this.type=type;
		this.username = username;
	}

	public RegisterEvent() {

	}

	@Override
	public String toString() {
		return "RegisterEvent{" +
				"type='" + type + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}
