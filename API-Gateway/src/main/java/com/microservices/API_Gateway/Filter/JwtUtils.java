package com.microservices.API_Gateway.Filter;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	private Key getSignKey()
	{
		byte[] keyBytes=Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	public void validatetoken(String token)
	{
	Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);	
	}

}
