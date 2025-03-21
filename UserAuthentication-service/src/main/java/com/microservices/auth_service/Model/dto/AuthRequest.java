package com.microservices.auth_service.Model.dto;

public class AuthRequest {
	private String name;
	private String email;
	private String password;
	private String usertype;

	public AuthRequest() {
		super();
	}

	public AuthRequest(String name, String email, String password, String usertype) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}
