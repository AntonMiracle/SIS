package com.bondarenko.maker.data;

import java.util.Random;

import com.bondarenko.model.Proposal;
import com.bondarenko.model.Role;
import com.bondarenko.model.Task;
import com.bondarenko.model.User;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.TaskService;

public class TaskMaker {

	public void generateExampleData(int maxTasksByProposal, RoleService rs, StatusService ss, TaskService ts,
			ProposalService ps) {
		Random rnd = new Random();
		int i = 0;
		for (Proposal proposal : ps.getProposals()) {
			i++;
			int numberOfTasks = rnd.nextInt(maxTasksByProposal) + 1;
			Role managers = rs.getByName(RoleMaker.ROLE_MANAGER);
			Role workers = rs.getByName(RoleMaker.ROLE_WORKER);
			int someManager = rnd.nextInt(managers.getUsers().size());
			User manager = null;
			User worker = null;
			for (User user : managers.getUsers()) {
				if (someManager-- == 0) {
					manager = user;
					break;
				}
			}
			for (int k = 0; k < numberOfTasks; k++) {
				int someWorker = rnd.nextInt(workers.getUsers().size());
				for (User user : managers.getUsers()) {
					if (someWorker-- == 0) {
						worker = user;
						break;
					}
				}
				Task task = new Task();
				task.setDescription("description" + String.valueOf(i) + String.valueOf(k));
				task.setConclusion("");
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
	}
}
