package com.bondarenko.maker.data;

import com.bondarenko.service.CarService;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.TaskService;
import com.bondarenko.service.UserService;

public class ExampleDataMaker {
	public static boolean isExampleDataInitialized = false;

	public void initializedExampleData(UserService us, TaskService ts, CarService cs, ProposalService ps,
			StatusService ss, RoleService rs) {
		if (!isExampleDataInitialized) {
			generateExampleData(us, ts, cs, ps, ss, rs);
			isExampleDataInitialized = true;
		}
	}

	public void generateExampleData(UserService us, TaskService ts, CarService cs, ProposalService ps, StatusService ss,
			RoleService rs) {
		int numberOfManagers = 1;
		int numberOfClients = 5;
		int numberOfWorkers = 1;
		int maxCarsByClient = 2;
		int numberOfProposals = 7;
		int maxTasksByProposal = 3;
		UserMaker um = new UserMaker();
		CarMaker cm = new CarMaker();
		ProposalMaker pm = new ProposalMaker();
		TaskMaker tm = new TaskMaker();
		um.makeExampleData(numberOfClients, numberOfManagers, numberOfWorkers, rs, us);
		cm.generateExampleData(maxCarsByClient, cs, us);
		pm.generateExampleData(numberOfProposals, cs, us, ss, ps);
		tm.generateExampleData(maxTasksByProposal, rs, ss, ts, ps);
	}

}
