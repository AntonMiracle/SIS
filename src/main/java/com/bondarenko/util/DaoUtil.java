package com.bondarenko.util;

import java.util.ArrayList;

import com.bondarenko.model.Role;
import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.UserService;

public class DaoUtil {
	public static boolean isDatabaseFill = false;
	public static final String ROLE_CLIENT = "client";
	public static final String ROLE_WORKER = "worker";
	public static final String ROLE_MANAGER = "manager";
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_BOSS = "boss";
	public static final String STATUS_OPEN = "open";
	public static final String STATUS_CLOSE = "close";
	public static final String STATUS_ACCEPTED = "accepted";

	public static void initializedDatabase(RoleService roleService, StatusService statusService, UserService userService) {
		if(!isDatabaseFill){
			initializedRoles(roleService);
			initializedStatuses(statusService);
			initializedUsers(userService, roleService);
			System.out.println("Database initialized complete");
			isDatabaseFill = true;
		}		
	}
	
	public static void initializedRoles(RoleService roleService){
		roleService.save(ROLE_ADMIN);
		roleService.save(ROLE_BOSS);
		roleService.save(ROLE_CLIENT);
		roleService.save(ROLE_MANAGER);
		roleService.save(ROLE_WORKER);
	}
	public static void initializedStatuses(StatusService statusService){
		statusService.save(STATUS_ACCEPTED);
		statusService.save(STATUS_CLOSE);
		statusService.save(STATUS_OPEN);
	}
	
	public static void initializedUsers(UserService userService, RoleService roleService){
		User admin = new User();
		admin.setUserInformation(new UserInformation());
		admin.setRoles(new ArrayList<Role>());
		admin.getUserInformation().setPhone("admin");
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.getRoles().add(roleService.getByName(DaoUtil.ROLE_ADMIN));
		userService.save(admin);		
	}
}
