package com.bondarenko.model.dto;

import java.util.HashSet;
import java.util.Set;

public class RestUserDto {
	private Long id;
	private String username;
	private String password;
	private Long userInformationId;
	private String phone;
	private String name; 
	private String surname;
	private String mail;
	private String createDate;
	private Set<Long> rolesId = new HashSet<>();;
	private Set<Long> proposalsId = new HashSet<>();;
	private Set<Long> carsId = new HashSet<>();;
	private Set<Long> tasksId = new HashSet<>();
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Long getUserInformationId() {
		return userInformationId;
	}
	public String getPhone() {
		return phone;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getMail() {
		return mail;
	}
	public String getCreateDate() {
		return createDate;
	}
	public Set<Long> getRolesId() {
		return rolesId;
	}
	public Set<Long> getProposalsId() {
		return proposalsId;
	}
	public Set<Long> getCarsId() {
		return carsId;
	}
	public Set<Long> getTasksId() {
		return tasksId;
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
	public void setUserInformationId(Long userInformationId) {
		this.userInformationId = userInformationId;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setRolesId(Set<Long> rolesId) {
		this.rolesId = rolesId;
	}
	public void setProposalsId(Set<Long> proposalsId) {
		this.proposalsId = proposalsId;
	}
	public void setCarsId(Set<Long> carsId) {
		this.carsId = carsId;
	}
	public void setTasksId(Set<Long> tasksId) {
		this.tasksId = tasksId;
	};

	

}
