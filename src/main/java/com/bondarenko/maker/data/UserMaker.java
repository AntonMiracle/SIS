package com.bondarenko.maker.data;

import java.util.HashSet;
import java.util.Set;

import com.bondarenko.maker.validator.UserValidator;
import com.bondarenko.model.Role;
import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;
import com.bondarenko.model.dto.RestNewUserDto;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.UserInformationService;
import com.bondarenko.service.UserService;

public class UserMaker {
	public static boolean isInitilized = false;

	public void makeUsers(UserService userService, RoleService roleService, UserInformationService uiService) {
		if (!isInitilized) {
			makeAdminAccount(userService, roleService, uiService);
			makeTestAccount(userService, roleService, uiService);
			isInitilized = true;
		}
	}

	private void makeAdminAccount(UserService userService, RoleService roleService,
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

	public void makeExampleData(int numberOfClients, int numberOfManagers, int numberOfWorkers, RoleService rs,
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

	private void makeTestAccount(UserService userService, RoleService roleService,
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
	
	public RestNewUserDto makeNewClient(RestNewUserDto dto, UserService us, RoleService rs, UserInformationService uis){
		UserValidator validator = new UserValidator();
		int count = 0;
		if(!validator.usernameValidation(dto.username, us)){
			dto.username = "";
			count++;
		}		
		if(!dto.confirmPassword.equals(dto.password)){
			dto.password="";
			count++;
		}
		if(!validator.checkFieldLength(dto.password)){
			dto.password="";
			count++;
		}
		if(!validator.phoneValidation(dto.phone, uis)){
			dto.phone="";
			count++;
		}
		if(!validator.checkFieldLength(dto.name)){
			dto.name = "";
			count++;
		}
		if(count == 0){
			Set<Role> roles = new HashSet<>();
			roles.add(rs.getByName(RoleMaker.ROLE_CLIENT));
			User user = new User();
			UserInformation ui = new UserInformation();
			user.setUserInformation(ui);
			user.setUsername(dto.username);
			user.setPassword(dto.password);			
			user.setRoles(roles);
			ui.setPhone(dto.phone);
			ui.setMail(dto.mail);
			ui.setName(dto.name);
			ui.setSurname(dto.surname);
			user = us.save(user);
			dto.isSave=true;
		}
		return dto;		
	}
	
	
}
