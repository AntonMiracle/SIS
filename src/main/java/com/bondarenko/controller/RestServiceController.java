package com.bondarenko.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bondarenko.model.User;
import com.bondarneko.dto.NewUserDto;

public interface RestServiceController {

//	public ResponseEntity<List<User>> getUsers() throws RuntimeException;
	public ResponseEntity<User[]> getUsers() throws RuntimeException;
	
	public ResponseEntity<User> saveUser(NewUserDto dto) throws RuntimeException;

	public ResponseEntity<User> getById(Long id) throws RuntimeException;

	public ResponseEntity<Boolean> isUsernameUnique(String username) throws RuntimeException;

	public ResponseEntity<Boolean> isPhoneUnique(String phone) throws RuntimeException;
	
	public ResponseEntity<Boolean> checkUsernameAndPassword(String username, String password) throws RuntimeException;	

}
