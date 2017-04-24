package com.bondarenko.maker.data;

import com.bondarenko.model.Role;
import com.bondarenko.service.RoleService;

public class RoleMaker {
	public static boolean isInitilized = false;
	public static final String ROLE_CLIENT = "client";
	public static final String ROLE_WORKER = "worker";
	public static final String ROLE_MANAGER = "manager";
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_BOSS = "boss";

	public void initialized(RoleService roleService) {		
		if (!isInitilized) {
			if (roleService.isNameUnique(ROLE_ADMIN)) {
				Role role = new Role();
				role.setName(ROLE_ADMIN);
				roleService.save(role);
			}
			if (roleService.isNameUnique(ROLE_WORKER)) {
				Role role = new Role();
				role.setName(ROLE_WORKER);
				roleService.save(role);
			}
			if (roleService.isNameUnique(ROLE_MANAGER)) {
				Role role = new Role();
				role.setName(ROLE_MANAGER);
				roleService.save(role);
			}
			if (roleService.isNameUnique(ROLE_CLIENT)) {
				Role role = new Role();
				role.setName(ROLE_CLIENT);
				roleService.save(role);
			}
			if (roleService.isNameUnique(ROLE_BOSS)) {
				Role role = new Role();
				role.setName(ROLE_BOSS);
				roleService.save(role);
			}
			isInitilized = true;
		}
	}	

}
