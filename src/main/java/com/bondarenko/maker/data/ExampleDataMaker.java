package com.bondarenko.maker.data;

import com.bondarenko.model.User;
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
			for (User user : us.getUsers()) {
//				System.out.print(" User ID : " + user.getId());
//				System.out.print(" roles : " + user.getRoles().size());
//				System.out.print(" cars : " + user.getCars().size());
//				System.out.print(" proposals : " + user.getProposals().size());
//				System.out.println(" tasks : " + user.getTasks().size());
			}
//			System.out.println("Number of cars : " + cs.getCars().size());
//			System.out.println("Number of users : " + us.getUsers().size());
//			System.out.println("Number of roles : " + rs.getRoles().size());
//			System.out.println("Number of proposals : " + ps.getProposals().size());
//			System.out.println("Number of tasks : " + ts.getTasks().size());
			isExampleDataInitialized = true;
		}
	}

	public void generateExampleData(UserService us, TaskService ts, CarService cs, ProposalService ps, StatusService ss,
			RoleService rs) {
		int numberOfManagers = 2;
		int numberOfClients = 7;
		int numberOfWorkers = 2;
		int maxCarsByClient = 3;
		int numberOfProposals = 15;
		int maxTasksByProposal = 4;
		UserMaker um = new UserMaker();
		CarMaker cm = new CarMaker();
		ProposalMaker pm = new ProposalMaker();
		TaskMaker tm = new TaskMaker();
		um.generateExampleData(numberOfClients, numberOfManagers, numberOfWorkers, rs, us);
		cm.generateExampleData(maxCarsByClient, cs, us);
		pm.generateExampleData(numberOfProposals, cs, us, ss, ps);
		tm.generateExampleData(maxTasksByProposal, rs, ss, ts, ps);
	}

}
