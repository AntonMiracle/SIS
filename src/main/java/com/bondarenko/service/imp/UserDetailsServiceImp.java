package com.bondarenko.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.model.Role;
import com.bondarenko.model.User;
import com.bondarenko.service.UserService;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {			
		User user = userService.getByUsername(username);
		if(user == null){
			throw new RuntimeException("USER === NULL");
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		for (Role role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);		
		return userDetails;
		}
	}
