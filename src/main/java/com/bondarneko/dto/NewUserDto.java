package com.bondarneko.dto;

public class NewUserDto {
	private Long id;
	private String username;
	private String password;
	private String phone;
	private String name;
	private String surname;
	private String mail;
	
	@Override
	public String toString() {
		return "RestUserDto [id=" + id + ", username=" + username + ", password=" + password + ", phone=" + phone
				+ ", name=" + name + ", surname=" + surname + ", mail=" + mail + "]";
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	
}
