package com.photo.app.api.gateway.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private Environment env;

	public AuthorizationFilter(AuthenticationManager authenticationManager, Environment env) {
		super(authenticationManager);
		this.env = env;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authHeader = request.getHeader(env.getProperty("authorization.header"));

		if (authHeader == null || !authHeader.startsWith(env.getProperty("authorization.header.prefix"))) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String authHeader = request.getHeader(env.getProperty("authorization.header"));

		if (authHeader == null)
			return null;

		String token = authHeader.replace(env.getProperty("authorization.header.prefix"), "");
		String userId = Jwts.parser().setSigningKey(env.getProperty("authorization.token.sign.key")).parseClaimsJws(token).getBody().getSubject();

		if (userId == null)
			return null;
		
		return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
	}

}
