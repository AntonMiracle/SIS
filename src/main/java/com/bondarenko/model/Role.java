package com.bondarenko.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings ("serial")
public class Role implements Serializable {
	private Long id;
	private String name;
	private List<User> users;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
