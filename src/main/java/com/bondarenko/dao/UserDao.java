package com.bondarenko.dao;

import java.util.List;

import com.bondarenko.model.User;

public interface UserDao {

	public User save(User user) throws RuntimeException;

	public boolean delete(User user) throws RuntimeException;

	public User update(User user) throws RuntimeException;

	public List<User> getAll() throws RuntimeException;
	
	public User getByUsername(String username) throws RuntimeException;
	
	public User getById(Long id) throws RuntimeException;
	
	public boolean isUsernameUnique(String username) throws RuntimeException;
}
