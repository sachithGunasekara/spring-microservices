package com.photo.api.user.ui.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.photo.api.user.ui.model.dto.user.UserDto;
import com.photo.api.user.ui.model.request.LoginRequestModel;
import com.photo.api.user.ui.service.user.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UserService userService;
	private Environment env;
	private AuthenticationManager authenticationManager;
	
	
	public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.env = env;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginRequestModel cred = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((User)authResult.getPrincipal()).getUsername();
		UserDto userDto = userService.getUserDetailsByEmail(username);
		
		String token =Jwts.builder().setSubject(userDto.getUserId())
		.setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(env.getProperty("token.expiration_time"))))
		.signWith(SignatureAlgorithm.HS256, env.getProperty("token.secret")).compact();
		
		response.addHeader("token", token);
		response.addHeader("userId", userDto.getUserId());
	}

	
}
