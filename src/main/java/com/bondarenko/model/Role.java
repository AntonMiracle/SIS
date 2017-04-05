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

import com.fasterxml.jackson.annotation.JsonBackReference;

@SuppressWarnings ("serial")
@Entity
public class Role implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "ROLE_ID")
	private Long id;
	@Column (name = "NAME", nullable = false, unique = true, length = 100)
	private String name;
	@ManyToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable (name = "USER_ROLE", joinColumns = {@JoinColumn (name = "ROLE_ID")}, inverseJoinColumns = {
			@JoinColumn (name = "USER_ID")})
	@JsonBackReference
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
