package com.microservices.auth_service.Model.Pojo;

import jakarta.persistence.*;

@Entity
public class OurUsers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;

	@Column(length = 50)
	private String name;

	@Column(length = 30, unique = true)
	private String email;

	@Column(length = 60)
	private String password;

	@Column(length = 20)
	private String usertype;

	@Column(length = 100)
	private String securityQuestion1;

	@Column(length = 100)
	private String securityQuestion2;

	@Column(length = 100)
	private String answer1;

	@Column(length = 100)
	private String answer2;

	public OurUsers() {
	}

	public OurUsers(String name, String email, String password, String usertype) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
	}

	// Getters and Setters
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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
