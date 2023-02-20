package com.form.application;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.form.entity.User;

// implement UserDetails Interface
public class FormUserDetails implements UserDetails {

	private User user;
	
	// create construtor of the class and set the fields.
	public FormUserDetails(User user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;							// not change
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();				// null = user.getPassword()
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();				// null = user.getUsername()
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;							// vaule= true
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;							// vaule= true
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true	;							// vaule= true
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;							// vaule= true
	}

}
