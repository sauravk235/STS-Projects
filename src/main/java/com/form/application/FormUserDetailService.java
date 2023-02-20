package com.form.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.form.entity.User;
import com.form.repository.FormUserRepositry;

// implements UserDetailsService Interface
public class FormUserDetailService implements UserDetailsService {

	@Autowired
	private FormUserRepositry repo;			// 2nd repository
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByFirstName(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new FormUserDetails(user);		// User details DB se fetch krke and pass it to FormUserDetails class
	}

}
