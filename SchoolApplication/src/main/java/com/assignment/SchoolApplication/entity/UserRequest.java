/**
 * 
 */
package com.assignment.SchoolApplication.entity;

/**
 * @author Akshay
 *
 */
public class UserRequest {
	
	private String username;
	private String password;
	
	//need default constructor for JSON Parsing
	public UserRequest(){}
	
	public UserRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
