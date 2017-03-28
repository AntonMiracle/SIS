package com.bondarenko.service;

import java.util.List;

import com.bondarenko.model.Role;

public interface RoleService {

	public Role save(Role role) throws RuntimeException;// test -ok

	public Role save(String roleName) throws RuntimeException;// test -ok

	public boolean delete(Role role) throws RuntimeException;// test-ok

	public boolean delete(String roleName) throws RuntimeException;// test-ok

	public boolean delete(Long roleId) throws RuntimeException;// test-ok

	public Role update(Role role) throws RuntimeException;// test-ok

	public Role updateName(String currentName, String newName) throws RuntimeException;// test-ok

	public Role updateName(Long id, String newName) throws RuntimeException;// test-ok

	public Role getByName(String name) throws RuntimeException;// test-ok

	public Role getById(Long id) throws RuntimeException;// test-ok

	public boolean isNameUnique(String name) throws RuntimeException;// test-ok

	public boolean switchRoles(String currentName, String existName) throws RuntimeException;// test-ok

	public List<Role> getRoles() throws RuntimeException;// test-ok

	public Role nullFilter(Role role) throws RuntimeException;// test-ok(inside-test)

}
