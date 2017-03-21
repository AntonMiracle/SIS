package com.bondarenko.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings ("serial")
public class UserInformation implements Serializable {
	private Long id;
	private User user;
	private String phone;
	private String name;
	private String surname;
	private String mail;
	private Timestamp createDate;

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
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

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
