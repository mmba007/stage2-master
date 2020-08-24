package com.enit.usercrud.events;



public class DeleteUserAccount  extends Event{
          
   
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public DeleteUserAccount(String username) {
		super("user account deleted");
		this.username = username;
	}

	public DeleteUserAccount() {

	}
	
}
