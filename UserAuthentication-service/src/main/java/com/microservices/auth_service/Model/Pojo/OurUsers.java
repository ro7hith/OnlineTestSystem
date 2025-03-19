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

	public OurUsers() {
		super();
	}

	public OurUsers(String name, String email, String password, String usertype) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
	}

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
}
