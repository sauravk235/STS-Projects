package com.form.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Files used => SecurityConfig, FormUserDetailService , FormUserDetails

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// interface => UserDetailsService , userdetails() -> method ,
	// FormUserDetailService -> user define class
	// fetch username and password from DB
	@Bean
	public UserDetailsService userdetails() {
		return new FormUserDetailService();
	}

	// password encode/decode,
	// BCryptPasswordEncoder- class
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Authenticate username, password
	// DaoAuthenticationProvider - class
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userdetails());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	// Authorize to use list of URL
	// SecurityFilterChain -Interface
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/registeruser", "/process_register").permitAll().
		anyRequest().authenticated()
		.and().formLogin().usernameParameter("username").loginPage("/login").defaultSuccessUrl("/home", true).permitAll()
		.and().logout().logoutUrl("/logout").permitAll();

		return http.build();
	}

}
