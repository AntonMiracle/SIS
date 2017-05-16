package com.bondarenko.maker.validator;

import com.bondarenko.service.UserInformationService;
import com.bondarenko.service.UserService;

public class UserValidator {

	public boolean usernameValidation(String username, UserService us) {
		if(checkFieldLength(username)){
			return us.isUsernameUnique(username);
		}
		return false;
	}

	public boolean checkFieldLength(String fieldValue) {
		if (fieldValue.trim().length() > 16 || fieldValue.trim().length() < 4) {
			return false;
		}
		return true;
	}
	
	public boolean phoneValidation(String phone, UserInformationService uis){
		if(checkFieldLength(phone)){
			return uis.isPhoneUnique(phone);
		}
		return false;
	}	
	
}
