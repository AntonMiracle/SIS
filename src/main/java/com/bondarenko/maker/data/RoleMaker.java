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
				roleService.save(ROLE_ADMIN);
			}
			if (roleService.isNameUnique(ROLE_WORKER)) {
				roleService.save(ROLE_WORKER);
			}
			if (roleService.isNameUnique(ROLE_MANAGER)) {
				roleService.save(ROLE_MANAGER);
			}
			if (roleService.isNameUnique(ROLE_CLIENT)) {
				roleService.save(ROLE_CLIENT);
			}
			if (roleService.isNameUnique(ROLE_BOSS)) {
				roleService.save(ROLE_BOSS);
			}
			isInitilized = true;
		}
	}

	public Role generateWorker(RoleService roleService) {
		return roleService.getByName(ROLE_WORKER);
	}

	public Role generateManager(RoleService roleService) {
		return roleService.getByName(ROLE_MANAGER);
	}

	public Role generateClient(RoleService roleService) {
		return roleService.getByName(ROLE_CLIENT);
	}

}
