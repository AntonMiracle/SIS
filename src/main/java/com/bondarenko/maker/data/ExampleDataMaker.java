package com.bondarenko.maker.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Task;
import com.bondarenko.model.User;
import com.bondarenko.service.CarService;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.TaskService;
import com.bondarenko.service.UserService;

public class ExampleDataMaker {
	public static boolean isExampleDataInitialized = false;
	int numberOfClients = 30;
	int numberOfWorkers = 10;
	int numberOfManager = 3;
	int maxCarsByOneUser = 3;
	int numberOfOpenProposals = 10;
	int numberOfCloseProposals = 10;
	int numberOfAcceptedProposals = 10;
	private User[] users;
	private User[] workers = new User[numberOfWorkers];
	private User[] managers = new User[numberOfManager];
	private Car[] cars;
	private Proposal[] proposals;
	private StatusMaker sm = new StatusMaker();
	private RoleMaker rm = new RoleMaker();
	private UserMaker um = new UserMaker();
	private CarMaker cm = new CarMaker();
	private ProposalMaker pm = new ProposalMaker();
	private TaskMaker tm = new TaskMaker();
	private Random rnd = new Random();

	private void setUsersRole(UserService us, RoleService rs) {
		users = um.generateUsers(numberOfClients + numberOfManager + numberOfWorkers);
		for (int i = 0, w = 0, m = 0; i < users.length; i++) {
			if (w < workers.length) {
				users[i].getRoles().add(rm.generateWorker(rs));
				users[i] = us.save(users[i]);
				workers[w++] = users[i++];
			}
			if (m < managers.length) {
				users[i].getRoles().add(rm.generateManager(rs));
				users[i] = us.save(users[i]);
				managers[m++] = users[i++];
			}
			users[i] = us.save(users[i]);
		}
	}

	private void setCarsUser(CarService cs) {
		cars = cm.generateCars(users.length * maxCarsByOneUser);
		int count = 0;
		for (int i = 0, j = 0; i < cars.length && j < users.length; j++) {
			j = j < users.length? j : 0;
			int numberOfCars = rnd.nextInt(maxCarsByOneUser) + 1;
			for(int k = 0; k < numberOfCars; k++){
				cars[i].setUser(users[j]);
				cars[i] = cs.save(cars[i]);
				count = cars[i].getId() != null ? count + 1 : count;
				i++;				
			}			
		}	
		Car[] newCars = new Car[count];
		for (int i = 0; i < newCars.length; i++) {
			newCars[i] = cars[i];
		}
		cars = newCars;
	}

	private void setProposals(ProposalService ps, StatusService ss) {
		proposals = pm.generateProposals(numberOfOpenProposals + numberOfCloseProposals + numberOfAcceptedProposals);
		for (int i = 0, j = 0; i < proposals.length; i++, j++) {
			j = j < cars.length ? j : 0;
			proposals[i].setUser(cars[j].getUser());
			proposals[i].setCar(cars[j]);
			proposals[i] = ps.save(proposals[i]);
			if (i < numberOfOpenProposals) {				
				proposals[i].setStatus(sm.generateOpen(ss));
			}
			if (i >= numberOfOpenProposals && i < (numberOfOpenProposals + numberOfCloseProposals)) {
				proposals[i].setStatus(sm.generateClose(ss));
			}
			if (i >= (numberOfOpenProposals + numberOfCloseProposals)) {
				proposals[i].setStatus(sm.generateAccepted(ss));
			}
			proposals[i] = ps.update(proposals[i]);
		}
	}

	private void setTasks(TaskService ts) {		
		for (int i = 0; i < proposals.length; i++) {			
			User manager = managers[rnd.nextInt(managers.length)];
			List<Task> tasks = tm.generateTasks();
			List<Task> proTasks = new ArrayList<>();
			for (int j = 0; j < tasks.size(); j++) {
				tasks.get(j).setProposal(proposals[i]);
				tasks.get(j).setUser(manager);
				Task task = ts.save(tasks.get(j));
				proTasks.add(task);
			}
			proposals[i].setTasks(proTasks);
		}
	}

	private void setTaskStatus(TaskService ts, StatusService ss) {
		for (int i = 0; i < proposals.length; i++) {
			Proposal prop = proposals[i];
			for (int j = 0; j < prop.getTasks().size(); j++) {
				User worker = workers[rnd.nextInt(workers.length)];
				Task task = prop.getTasks().get(j);
				if (prop.getStatus().getName().equals(StatusMaker.STATUS_CLOSE)) {
					task.setUser(worker);
					task.setStatus(sm.generateClose(ss));
					task.setConclusion(tm.generateConclusion());
				}
				if (prop.getStatus().getName().equals(StatusMaker.STATUS_ACCEPTED)) {					
					switch (rnd.nextInt(2)) {
						case 0: {
							task.setUser(worker);
							task.setStatus(sm.generateAccepted(ss));
							break;
						}
						case 1: {
							task.setUser(worker);
							task.setStatus(sm.generateClose(ss));
							task.setConclusion(tm.generateConclusion());
							break;
						}
					}
				}
				ts.update(task);
			}
		}
	}

	public void generateExampleData(UserService us, TaskService ts, CarService cs, ProposalService ps, StatusService ss, RoleService rs) {
		if (!isExampleDataInitialized) {
			setUsersRole(us, rs);
			setCarsUser(cs);
			setProposals(ps, ss);
			setTasks(ts);
			setTaskStatus(ts, ss);
			isExampleDataInitialized = true;
		}
	}

}
