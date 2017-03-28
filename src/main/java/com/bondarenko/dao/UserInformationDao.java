package com.bondarenko.dao;

import java.util.List;

import com.bondarenko.model.UserInformation;

public interface UserInformationDao {
	public UserInformation save(UserInformation userInformation) throws RuntimeException;

	public boolean delete(UserInformation userInformation) throws RuntimeException;

	public UserInformation update(UserInformation userInformation) throws RuntimeException;

	public List<UserInformation> getAll() throws RuntimeException;

	public UserInformation getByPhone(String phone) throws RuntimeException;

	public UserInformation getById(Long id) throws RuntimeException;

	public boolean isPhoneUnique(String phone) throws RuntimeException;

}
