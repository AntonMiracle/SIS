package com.bondarenko.dao;

import java.util.List;

import com.bondarenko.model.Role;

public interface RoleDao {
	public Role save(Role role) throws RuntimeException;

	public boolean delete(Role role) throws RuntimeException;

	public Role update(Role role) throws RuntimeException;

	public List<Role> getAll() throws RuntimeException;	

	public Role getByName(String name) throws RuntimeException;	

	public Role getById(Long id) throws RuntimeException;
	
	public boolean isNameUnique(String name) throws RuntimeException;
}
