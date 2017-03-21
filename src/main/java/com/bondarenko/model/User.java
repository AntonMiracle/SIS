package com.bondarenko.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings ("serial")
public class User implements Serializable {
	private Long id;
	private String username;
	private String password;
	private UserInformation userInformation;
	private List<Role> roles;
	private List<Proposal> proposals;
	private List<Car> cars;
	private List<Task> tasks;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public List<Proposal> getProposals() {
		return proposals;
	}

	public List<Car> getCars() {
		return cars;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
