package com.bondarenko.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@SuppressWarnings ("serial")
@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "USER_ID")
	private Long id;
	@Column (name = "USERNAME", nullable = false, unique = true, length = 100)
	private String username;
	@Column (name = "PASSWORD", nullable = false, length = 100)
	private String password;
	@OneToOne (cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_INFORMATION_ID")
	private UserInformation userInformation;
	@ManyToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable (name = "USER_ROLE", joinColumns = {@JoinColumn (name = "USER_ID")}, inverseJoinColumns = {
			@JoinColumn (name = "ROLE_ID")})
	private List<Role> roles;
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID", referencedColumnName = "USER_ID")
	private List<Proposal> proposals;
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID", referencedColumnName = "USER_ID")
	private List<Car> cars;
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID", referencedColumnName = "USER_ID")
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
