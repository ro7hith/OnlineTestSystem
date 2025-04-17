package com.microservices.auth_service.Model.dto;

public class AuthRequest {
	private String name;
	private String email;
	private String password;
	private String usertype;
	private String securityQuestion1;
	private String securityQuestion2;
	private String answer1;
	private String answer2;

	public AuthRequest() {
		super();
	}

	public AuthRequest(String name, String email, String password, String usertype, String securityQuestion1,
			String securityQuestion2, String answer1, String answer2) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
		this.securityQuestion1 = securityQuestion1;
		this.securityQuestion2 = securityQuestion2;
		this.answer1 = answer1;
		this.answer2 = answer2;
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

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
}
