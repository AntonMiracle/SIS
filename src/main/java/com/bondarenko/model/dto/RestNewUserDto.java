package com.bondarenko.model.dto;

public class RestNewUserDto {
	public boolean isSave = false;
	public Long id;
	public String username;
	public String password;
	public String confirmPassword;
	public String phone;
	public String name;
	public String surname;
	public String mail;

	@Override
	public String toString() {
		return "RestUserDto [id=" + id + ", isSave=" + isSave + ", username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword + ", phone=" + phone
				+ ", name=" + name + ", surname=" + surname + ", mail=" + mail + "]";
	}

}
