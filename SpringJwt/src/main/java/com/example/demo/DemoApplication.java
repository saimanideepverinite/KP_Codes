package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Jwtutil.JwtUtils;
import com.example.demo.filter.JwtRequestFilter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public JwtUtils getJWT() 
	{
		return new JwtUtils();
	}
	@Bean
	public JwtRequestFilter getJWTFilter() 
	{
		return new JwtRequestFilter();
	}
}
