package com.enit.authentication.events;

import com.enit.authentication.model.EventName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
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

	public String getLastName() {
		return lastName;
	}

	private String email;

	private Set<String> role;


	private String password;


	private String firstName;



	private String lastName;


	public UpdateUserEvent(String username, String email, Set<String> role,  String firstName, String lastNam,String password) {
		super(EventName.UPDATE_USER);
		this.username = username;
		this.email = email;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password=password;
	}

	public UpdateUserEvent() {

	}
}
