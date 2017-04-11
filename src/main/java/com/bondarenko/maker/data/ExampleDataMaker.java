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
	private int numberOfManagers = 1;
	private int numberOfClients = 1;
	private int numberOfWorkers = 1;
	private int maxCarsByUser = 2;
	private int numberOfProposals = 5;
	private int maxTasksByProposal = 3;
	private Random rnd = new Random();

	public void initializedExampleData(UserService us, TaskService ts, CarService cs, ProposalService ps,
			StatusService ss, RoleService rs) {
		if (!isExampleDataInitialized) {
			generateExampleData(us, ts, cs, ps, ss, rs);
			for (User user : us.getUsers()) {
				System.out.println("User ID : " + user.getId());
				System.out.println("roles : " + user.getRoles().size());
				System.out.println("cars : " + user.getCars().size());
				System.out.println("proposals : " + user.getProposals().size());
				System.out.println("tasks : " + user.getTasks().size());
			}
			isExampleDataInitialized = true;
		}
	}

	public void generateExampleData(UserService us, TaskService ts, CarService cs, ProposalService ps, StatusService ss,
			RoleService rs) {

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
			user = us.save(user);
			List<Car> cars = new ArrayList<>();
			int numberOfCars = rnd.nextInt(maxCarsByUser) + 1;
			for (int j = 0; j < numberOfCars; j++) {
				Car car = new Car();
				car.setUser(user);
				car.setNumber("number" + String.valueOf(i) + String.valueOf(j));
				car.setMark("mark" + String.valueOf(i + j));
				car.setModel("model" + String.valueOf(i + j));
				car.setDescription("description + description");
				car = cs.save(car);
				System.out.println("User ID : " + user.getId());
				System.out.println("Car ID : " + car.getId());
				cars.add(car);
			}
		}
		List<User> users = us.getUsers();
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getCars().size() > 0 && us.isHasRole(user.getId(), RoleMaker.ROLE_CLIENT)
					&& numberOfProposals-- > 0) {
				Car car = user.getCars().get(rnd.nextInt(user.getCars().size()));
				Proposal proposal = new Proposal();
				proposal.setUser(user);
				proposal.setCar(car);
				proposal.setDescription("proposalDescription");
				switch (rnd.nextInt(3)) {
					case 0: {
						proposal.setStatus(ss.getByName(StatusMaker.STATUS_OPEN));
						break;
					}
					case 1: {
						proposal.setStatus(ss.getByName(StatusMaker.STATUS_ACCEPTED));
						break;
					}
					case 2: {
						proposal.setStatus(ss.getByName(StatusMaker.STATUS_CLOSE));
						break;
					}
				}
				proposal = ps.save(proposal);

				int numberOfTasks = rnd.nextInt(maxTasksByProposal) + 1;
				User manager = us.getByRolename(RoleMaker.ROLE_MANAGER)
						.get(rnd.nextInt(us.getByRolename(RoleMaker.ROLE_MANAGER).size()));
				for (int k = 0; k < numberOfTasks; k++) {
					User worker = us.getByRolename(RoleMaker.ROLE_WORKER)
							.get(rnd.nextInt(us.getByRolename(RoleMaker.ROLE_WORKER).size()));
					Task task = new Task();
					task.setDescription("description" + String.valueOf(i) + String.valueOf(k));
					if (proposal.getStatus().getName().equals(StatusMaker.STATUS_OPEN)) {
						task.setUser(manager);
						task.setStatus(ss.getByName(StatusMaker.STATUS_OPEN));
					}
					if (proposal.getStatus().getName().equals(StatusMaker.STATUS_ACCEPTED)) {
						task.setUser(worker);
						if (rnd.nextInt(2) == 0) {
							task.setConclusion("Conclusion" + String.valueOf(i) + String.valueOf(k));
							task.setStatus(ss.getByName(StatusMaker.STATUS_CLOSE));
						} else {
							task.setStatus(ss.getByName(StatusMaker.STATUS_ACCEPTED));
						}
					}
					if (proposal.getStatus().getName().equals(StatusMaker.STATUS_CLOSE)) {
						task.setUser(worker);
						task.setConclusion("Conclusion" + String.valueOf(i) + String.valueOf(k));
						task.setStatus(ss.getByName(StatusMaker.STATUS_CLOSE));
					}
					ts.save(task);
				}
			}
			i = numberOfProposals > 0 && i == users.size() - 1 ? i = -1 : i;
		}
	}

}
