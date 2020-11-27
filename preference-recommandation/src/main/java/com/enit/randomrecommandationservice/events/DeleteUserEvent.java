package com.enit.randomrecommandationservice.events;


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
