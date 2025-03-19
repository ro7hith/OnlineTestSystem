package com.microservices.API_Gateway.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class AppConfig {

	@Bean
	public RestTemplate template()
	{
		return new RestTemplate();
	}
		

}