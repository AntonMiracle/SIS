package com.bondarenko.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@OneToOne (cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_INFORMATION_ID")
	private UserInformation userInformation = new UserInformation();
	@ManyToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable (name = "USER_ROLE", joinColumns = {@JoinColumn (name = "USER_ID")}, inverseJoinColumns = {
			@JoinColumn (name = "ROLE_ID")})
	@JsonManagedReference
	private Set<Role> roles = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID", referencedColumnName = "USER_ID")
	@JsonManagedReference
	private Set<Proposal> proposals = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID", referencedColumnName = "USER_ID")
	@JsonManagedReference
	private Set<Car> cars = new HashSet<>();
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID", referencedColumnName = "USER_ID")
	@JsonManagedReference
	private Set<Task> tasks = new HashSet<>();

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userInformation="
				+ userInformation + ", roles=" + roles + ", proposals=" + proposals + ", cars=" + cars + ", tasks="
				+ tasks + "]";
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public Set<Proposal> getProposals() {
		return proposals;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public Set<Task> getTasks() {
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

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setProposals(Set<Proposal> proposals) {
		this.proposals = proposals;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}
