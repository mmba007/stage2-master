package com.enit.usercrud.events;


import com.enit.usercrud.model.EventName;

import java.util.Set;

public class UpdateUserEvent extends Event {



	private String username;

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getRole() {
		return role;
	}



	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	private String email;

	private Set<String> role;
    private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String firstName;



	private String lastName;


	public UpdateUserEvent(String username, String email, Set<String> role,  String firstName, String lastName,String password) {
		super(EventName.UPDATE_USER);
		this.username = username;
		this.email = email;
		this.password=password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public UpdateUserEvent() {

	}
}
