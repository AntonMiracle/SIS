package com.bondarenko.maker.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.User;
import com.bondarenko.service.CarService;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.UserService;

public class ProposalMaker {

	public void generateExampleData(int numberOfProposals, CarService cs, UserService us, StatusService ss,
			ProposalService ps) {
		List<Car> cars = new ArrayList<>();
		for (Car car : cs.getCars()) {
			cars.add(car);
		}
		int i = 0;
		for (int j = 0; j < numberOfProposals; i++, j++) {
			i = i == cars.size() ? 0 : i;
			Car car = cars.get(i);
			User user = car.getUser();
			if (us.isHasRole(user.getId(), RoleMaker.ROLE_CLIENT)) {
				Proposal proposal = new Proposal();
				proposal.setUser(user);
				proposal.setCar(car);
				proposal.setDescription("proposalDescription");
				switch (new Random().nextInt(3)) {
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
				ps.save(proposal);
				System.out.println("numberOfProposal : " + numberOfProposals + " | i : " + i);
			}
		}
	}
}
