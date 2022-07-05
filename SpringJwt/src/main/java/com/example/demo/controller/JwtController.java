package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Jwtutil.JwtUtils;
import com.example.demo.Service.MyUserDetailsService;
import com.example.demoEntity.AuthenticationRequest;

@RestController
public class JwtController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
    @GetMapping("/hello")
    public String getValue(){
        return "Hello Programmer";
    }
    @PostMapping("/authenticate")
    public ResponseEntity save(@RequestBody AuthenticationRequest authenticationReqest) throws Exception{
    	try {
    		authenticationManager.authenticate(
        			new UsernamePasswordAuthenticationToken(authenticationReqest.getUsername(), authenticationReqest.getPassword()));
  		} catch (Exception e) {
			throw new Exception("Incorrect username or password");
		}
    	final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationReqest.getUsername());
    	final String jwt=jwtUtil.generateTocken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

}
