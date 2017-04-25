package com.bondarenko.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.bondarenko.model.User;
import com.bondarenko.model.dto.RestNewUserDto;
import com.bondarenko.model.dto.RestProposalDto;
import com.bondarenko.model.dto.RestUserDto;

public interface RestServiceController {

	public ResponseEntity<Set<RestUserDto>> getUsers() throws RuntimeException;
	
	public ResponseEntity<Set<RestProposalDto>> getProposals() throws RuntimeException;
	
	public ResponseEntity<User> saveUser(RestNewUserDto dto) throws RuntimeException;

	public ResponseEntity<User> getById(Long id) throws RuntimeException;

	public ResponseEntity<Boolean> isUsernameUnique(String username) throws RuntimeException;

	public ResponseEntity<Boolean> isPhoneUnique(String phone) throws RuntimeException;
	
	public ResponseEntity<Boolean> checkUsernameAndPassword(String username, String password) throws RuntimeException;	

}
