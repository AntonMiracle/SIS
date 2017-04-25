package com.bondarenko.model.dto;

public class RestNewUserDto {
	public Long id;
	public String username;
	public String password;
	public String phone;
	public String name;
	public String surname;
	public String mail;

	@Override
	public String toString() {
		return "RestUserDto [id=" + id + ", username=" + username + ", password=" + password + ", phone=" + phone
				+ ", name=" + name + ", surname=" + surname + ", mail=" + mail + "]";
	}

}
