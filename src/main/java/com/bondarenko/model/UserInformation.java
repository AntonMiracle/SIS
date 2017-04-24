package com.bondarenko.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings ("serial")
@Entity
public class UserInformation implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "USER_INFORMATION_ID")
	private Long id;
	@Column (name = "PHONE", nullable = false, unique = true, length = 100)
	private String phone;
	@Column (name = "NAME", nullable = false, length = 100)
	private String name;
	@Column (name = "SURNAME", nullable = false, length = 100)
	private String surname;
	@Column (name = "MAIL", nullable = false, length = 100)
	private String mail;
	@Column (name = "CREATE_DATE")
	private Timestamp createDate;

	public Long getId() {
		return id;
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
