package com.bondarenko.util;

import com.bondarenko.maker.data.ExampleDataMaker;
import com.bondarenko.maker.data.RoleMaker;
import com.bondarenko.maker.data.StatusMaker;
import com.bondarenko.maker.data.UserMaker;
import com.bondarenko.service.CarService;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.TaskService;
import com.bondarenko.service.UserInformationService;
import com.bondarenko.service.UserService;

public class DataBaseInitialized {
	public static boolean isDatabaseFill = false;

	public void initializedDatabase(RoleService roleService, StatusService statusService, TaskService taskService,
			CarService carService, ProposalService proposalService, UserService userService, UserInformationService uiService) {
		if (!isDatabaseFill) {			
			new RoleMaker().initialized(roleService);
			new StatusMaker().initialized(statusService);
			new UserMaker().initializedUsers(userService, roleService,uiService);
			new ExampleDataMaker().initializedExampleData(userService, taskService, carService, proposalService, statusService, roleService);
			System.out.println("Database initialized complete");
			isDatabaseFill = true;
		}
	}
}
