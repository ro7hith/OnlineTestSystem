package com.microservices.API_Gateway.Filter;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RoutingValidator validator;

    @Autowired
    private RestTemplate template;

    @Autowired
    private JwtUtils jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.out.println("Processing Request: " + exchange.getRequest().getURI());

            if (validator.isSecured.test(exchange.getRequest())) {
                // Check if Authorization header exists
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing Authorization Header");
                }

                // Extract the token from the header
                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                System.out.println("Authorization Header: " + authHeader);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                } else {
                    throw new RuntimeException("Invalid Authorization Format");
                }

                try {
                    // Validate token using JWT Utils
                    jwtUtil.validatetoken(authHeader);

                    // Optional: If you need to validate via AUTH-SERVICE, uncomment this
                    // template.getForObject("http://AUTH-SERVICE/validate?token=" + authHeader, String.class);

                } catch (Exception e) {
                    System.out.println("Invalid Token!");
                    throw new RuntimeException("Unauthorized Access");
                }
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {}
}
