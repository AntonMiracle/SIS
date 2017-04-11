package com.bondarenko.maker.data;

import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.UserService;

public class UserMaker {
	public static boolean isInitilized = false;

	public void initializedUsers(UserService userService, RoleService roleService) {
		if (!isInitilized) {
			initializedAdministration(userService, roleService);
			isInitilized = true;
		}
	}

	private void initializedAdministration(UserService userService, RoleService roleService) {
		if (userService.isUsernameUnique("admin") && userService.isPhoneUnique("admin")) {
			User admin = new User();
			admin.setUserInformation(new UserInformation());
			admin.getUserInformation().setPhone("admin");
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin.getRoles().add(roleService.getByName(RoleMaker.ROLE_ADMIN));
			userService.save(admin);
		}
	}
}
