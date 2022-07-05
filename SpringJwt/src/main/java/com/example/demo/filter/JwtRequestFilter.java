package com.example.demo.filter;

import java.io.IOError;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Jwtutil.JwtUtils;
import com.example.demo.Service.MyUserDetailsService;

import io.jsonwebtoken.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private JwtUtils jwtUtils;
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) 
			throws SecurityException,IOException, java.io.IOException, ServletException
	{
		final String authorizationHeader=request.getHeader("Authorization");
		String username=null;
		String jwt=null;
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt=authorizationHeader.substring(7);
			username=jwtUtils.extractUsername(jwt);
		}
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=this.myUserDetailsService.loadUserByUsername(username);
			if(jwtUtils.validateTocken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken
						(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		chain.doFilter(request, response);
		
	}
	

}
