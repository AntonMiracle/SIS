package com.bondarenko.maker.data;

import java.util.ArrayList;

import com.bondarenko.model.Role;
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

	public User[] generateUsers(int quantity, UserService us) {
		UserInformation[] uis = new UserInformationMaker().generateUserInformation(quantity);
		User[] users = new User[uis.length];
		for (int i = 0; i < users.length; i++) {
			User user = new User();
			user.setUsername("tu" + String.valueOf(i));
			user.setPassword("tp" + String.valueOf(i));
			user.setUserInformation(uis[i]);
//			user.setRoles(new ArrayList<Role>());
//			users[i] = user;
			us.save(user);
		}
		return users;
	}

	private void initializedAdministration(UserService userService, RoleService roleService) {
		if (userService.isUsernameUnique("admin") && userService.isPhoneUnique("admin")) {
			User admin = new User();
			admin.setUserInformation(new UserInformation());
			admin.setRoles(new ArrayList<Role>());
			admin.getUserInformation().setPhone("admin");
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin.getRoles().add(roleService.getByName(RoleMaker.ROLE_ADMIN));
			userService.save(admin);
		}
	}
}
