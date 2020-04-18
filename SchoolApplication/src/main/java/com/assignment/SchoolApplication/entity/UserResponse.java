package com.assignment.SchoolApplication.entity;

public class UserResponse {
	
	private final String jwttoken;

	public UserResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
