package com.enit.usercrud.events;


import com.enit.usercrud.model.EventName;

public class DeleteUserEvent extends Event {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public DeleteUserEvent(String username) {
		super(EventName.DELETE_USER);
		this.username = username;

	}

	public DeleteUserEvent() {

	}
}
