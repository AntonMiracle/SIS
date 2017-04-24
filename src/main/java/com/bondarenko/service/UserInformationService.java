package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.UserInformation;

public interface UserInformationService {

	public UserInformation save(UserInformation ui) throws RuntimeException;//test-ok

	public UserInformation update(UserInformation ui) throws RuntimeException;//test-ok

	public Set<UserInformation> getUserInformations() throws RuntimeException;// test-ok

	public UserInformation getByPhone(String phone) throws RuntimeException;//test-ok

	public UserInformation getById(Long id) throws RuntimeException;//test-ok

	public boolean isPhoneUnique(String phone) throws RuntimeException;//test-ok

}
