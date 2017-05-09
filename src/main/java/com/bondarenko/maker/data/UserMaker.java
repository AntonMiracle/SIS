package com.bondarenko.maker.data;

import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.UserInformationService;
import com.bondarenko.service.UserService;

public class UserMaker {
	public static boolean isInitilized = false;

	public void initializedUsers(UserService userService, RoleService roleService, UserInformationService uiService) {
		if (!isInitilized) {
			initializedAdministration(userService, roleService, uiService);
			initializedTestAccount(userService, roleService, uiService);
			isInitilized = true;
		}
	}

	private void initializedAdministration(UserService userService, RoleService roleService,
			UserInformationService uiService) {
		if (userService.isUsernameUnique("admin") && uiService.isPhoneUnique("admin")) {
			User admin = new User();
			admin.setUserInformation(new UserInformation());
			admin.getUserInformation().setPhone("admin");
			admin.getUserInformation().setMail("admin");
			admin.getUserInformation().setName("admin");
			admin.getUserInformation().setSurname("admin");
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin.getRoles().add(roleService.getByName(RoleMaker.ROLE_ADMIN));
			userService.save(admin);
		}

	}

	public void generateExampleData(int numberOfClients, int numberOfManagers, int numberOfWorkers, RoleService rs,
			UserService us) {
		for (int i = 0; i < (numberOfClients + numberOfManagers + numberOfWorkers); i++) {
			User user = new User();
			user.setUsername("username" + String.valueOf(i));
			user.setPassword("password" + String.valueOf(i));
			user.getUserInformation().setPhone("phone" + String.valueOf(i));
			user.getUserInformation().setMail("mail@" + String.valueOf(i));
			user.getUserInformation().setName("name" + String.valueOf(i));
			user.getUserInformation().setSurname("surname" + String.valueOf(i));
			if (i < numberOfClients) {
				user.getRoles().add(rs.getByName(RoleMaker.ROLE_CLIENT));
			}
			if (i >= numberOfClients && i < (numberOfClients + numberOfManagers)) {
				user.getRoles().add(rs.getByName(RoleMaker.ROLE_MANAGER));
			}
			if (i >= (numberOfClients + numberOfManagers)) {
				user.getRoles().add(rs.getByName(RoleMaker.ROLE_WORKER));
			}
			us.save(user);
		}
	}

	private void initializedTestAccount(UserService userService, RoleService roleService,
			UserInformationService uiService) {
		if (userService.isUsernameUnique("a") && uiService.isPhoneUnique("a")) {
			User client = new User();
			client.setUserInformation(new UserInformation());
			client.getUserInformation().setPhone("a");
			client.getUserInformation().setMail("a");
			client.getUserInformation().setName("a");
			client.getUserInformation().setSurname("a");
			client.setUsername("a");
			client.setPassword("a");
			client.getRoles().add(roleService.getByName(RoleMaker.ROLE_CLIENT));
			userService.save(client);
		}
	}
}
