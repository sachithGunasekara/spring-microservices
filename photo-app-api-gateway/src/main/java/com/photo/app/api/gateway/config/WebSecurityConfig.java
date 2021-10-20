package com.photo.app.api.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.photo.app.api.gateway.filter.AuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private Environment env;
	
	@Autowired
	public WebSecurityConfig(Environment env) {
		this.env = env;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/user-ws/users/login").permitAll()
				.antMatchers(HttpMethod.POST, "/user-ws/users").permitAll().antMatchers("/user-ws/h2-console/**")
				.permitAll().anyRequest().authenticated()
				.and().addFilter(new AuthorizationFilter(authenticationManager(), env));

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
