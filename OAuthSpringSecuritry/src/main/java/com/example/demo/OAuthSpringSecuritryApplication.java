package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class OAuthSpringSecuritryApplication {
	@GetMapping("/")
	public String random() {
		return "SpringSecurityOAuth2.0";
	}

	public static void main(String[] args) {
		SpringApplication.run(OAuthSpringSecuritryApplication.class, args);
	}

}
