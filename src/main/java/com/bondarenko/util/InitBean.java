package com.bondarenko.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bondarenko.service.CarService;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.TaskService;
import com.bondarenko.service.UserService;

@Component
public class InitBean implements InitializingBean {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private CarService carService;
	@Autowired
	private ProposalService proposalService;

	@Override
	public void afterPropertiesSet() throws Exception {
		new DataBaseInitialized().initializedDatabase(roleService, statusService, taskService, carService,
				proposalService, userService);
	}

}
