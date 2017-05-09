package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.Role;

public interface RoleService {

	public Role save(Role role) throws RuntimeException;// test-ok

	public boolean delete(Long roleId) throws RuntimeException;// test-ok

	public Role update(Role role) throws RuntimeException;// test-ok

	public Role getByName(String name) throws RuntimeException;// test-ok

	public Role getById(Long id) throws RuntimeException;// test-ok

	public boolean isNameUnique(String name) throws RuntimeException;// test-ok
	
	public boolean isClient(Long userId) throws RuntimeException;

	public Set<Role> getRoles() throws RuntimeException;// test-ok

}
