package com.microservices.API_Gateway.Filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
@Component
public class RoutingValidator {
	public static final List<String> openApiEndPoints = List.of("/auth/register", "/auth/token","/auth/validate","/auth/forgot-password","/auth/reset-password");

	public Predicate<ServerHttpRequest> isSecured = 
			i -> openApiEndPoints.stream()
			.noneMatch(uri -> i.getURI().getPath().contains(uri));

}
