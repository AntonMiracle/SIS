package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.User;

public interface UserService {

	public User save(User user) throws RuntimeException;//test-ok

	public boolean delete(Long userId) throws RuntimeException;// test-ok

	public User update(User user) throws RuntimeException;// test-ok

	public Set<User> getUsers() throws RuntimeException;// test-ok

	public User getById(Long id) throws RuntimeException;//test-ok

	public User getByUsername(String username) throws RuntimeException;// test-ok

	public boolean isUsernameUnique(String username) throws RuntimeException;//test-ok

	public boolean isAuthenticationCorrect(String username, String password) throws RuntimeException;// test-ok

	public boolean isHasRole(Long userId, String name) throws RuntimeException;// test-ok

	public User getByPhone(String phone) throws RuntimeException;// test-ok

}
