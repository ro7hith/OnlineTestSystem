package com.microservices.auth_service.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservices.auth_service.Model.Pojo.OurUsers;

public class OurUserDetails implements UserDetails {

	@Autowired
	PasswordEncoder passwordEncoder;

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private List<GrantedAuthority> usertype; 

	public OurUserDetails(OurUsers ourusers) {
		System.out.println("OurUserDetails Constructor");
		this.email = ourusers.getEmail();
		this.password = ourusers.getPassword();
		this.usertype = Arrays.stream(ourusers.getUsertype().split(",")) 
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.usertype; 
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
