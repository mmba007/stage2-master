package com.enit.usercrud.events;


import com.enit.usercrud.model.EventName;

import java.util.Set;

public class RegisterUserEvent extends Event {

	private String username;
	private String email;
	private Set<String> role;
	private String password;
	private String firstName;
	private String lastName;

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

	public void setPassword(String password) {
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public RegisterUserEvent(String username, String email, Set<String> role, String password, String firstName, String lastName) {
		super(EventName.REGISTER_USER);
		this.username = username;
		this.email = email;
		this.role = role;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}



	public RegisterUserEvent(String username) {
		super(EventName.REGISTER_USER);
		this.username = username;

	}

	public RegisterUserEvent() {

	}
}
