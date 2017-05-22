package com.bondarenko.service.imp;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bondarenko.maker.data.UserMaker;
import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Role;
import com.bondarenko.model.Task;
import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;
import com.bondarenko.model.dto.RestCarDto;
import com.bondarenko.model.dto.RestNewUserDto;
import com.bondarenko.model.dto.RestProposalDto;
import com.bondarenko.model.dto.RestUserDto;
import com.bondarenko.service.CarService;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.RestService;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.UserInformationService;
import com.bondarenko.service.UserService;

@Service
public class RestServiceImp implements RestService {
	@Autowired
	private UserService userService;
	@Autowired
	private UserInformationService userInformationService;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private CarService carService;
	@Autowired
	private RoleService roleService;

	@Override
	public Set<RestUserDto> getRestClientsDto() throws RuntimeException {
		Set<RestUserDto> clients = new TreeSet<>();
		for (User user : userService.getUsers()) {
			if(roleService.isClient(user.getId())){
				clients.add(convertUser(user));
			}			
		}		
		return clients;
	}
	
	@Override
	public Set<RestUserDto> getRestUsersDto() throws RuntimeException {
		Set<RestUserDto> users = new HashSet<>();
		for (User user : userService.getUsers()) {
			users.add(convertUser(user));
		}
		return users;
	}

	@Override
	public Set<RestProposalDto> getRestProposalsDto() throws RuntimeException {
		Set<RestProposalDto> proposals = new HashSet<>();
		for (Proposal proposal : proposalService.getProposals()) {
			proposals.add(convertProposal(proposal));
		}
		return proposals;
	}

	@Override
	public Set<RestCarDto> getRestCarsDto() throws RuntimeException {
		Set<RestCarDto> cars = new HashSet<>();
		for (Car car : carService.getCars()) {
			cars.add(convertCar(car));
		}
		return cars;
	}

	@SuppressWarnings ("deprecation")
	@Override
	public RestUserDto convertUser(User user) throws RuntimeException {
		UserInformation ui = user.getUserInformation();
		RestUserDto dto = new RestUserDto();
		dto.id = user.getId();
		dto.username = user.getUsername();
		dto.password = user.getPassword();
		dto.createDate = ui.getCreateDate().toGMTString();
		dto.mail = ui.getMail();
		dto.name = ui.getName();
		dto.phone = ui.getPhone();
		dto.surname = ui.getSurname();
		for (Role role : user.getRoles()) {
			dto.roles.add(role.getName());
		}
		for (Proposal proposal : user.getProposals()) {
			dto.proposals.add(convertProposal(proposal));
		}
		for (Car car : user.getCars()) {
			dto.cars.add(convertCar(car));
		}
		for (Task task : user.getTasks()) {
			dto.tasksId.add(task.getId());
		}
		return dto;
	}

	@SuppressWarnings ("deprecation")
	@Override
	public RestProposalDto convertProposal(Proposal proposal) throws RuntimeException {
		RestProposalDto dto = new RestProposalDto();
		dto.id = proposal.getId();
		dto.status = proposal.getStatus().getName();
		dto.carNumber = proposal.getCar().getNumber();
		dto.description = proposal.getDescription();
		dto.createDate = proposal.getCreateDate().toGMTString();
		for (Task task : proposal.getTasks()) {
			dto.tasksId.add(task.getId());
		}
		return dto;
	}

	@Override
	public RestNewUserDto makeNewClient(RestNewUserDto dto) throws RuntimeException {		
		return new UserMaker().makeNewClient(dto, userService, roleService, userInformationService);				
	}

	@SuppressWarnings ("deprecation")
	@Override
	public RestCarDto convertCar(Car car) throws RuntimeException {
		RestCarDto dto = new RestCarDto();
		dto.id = car.getId();
		dto.number = car.getNumber();
		dto.model = car.getModel();
		dto.mark = car.getMark();
		dto.description = car.getDescription();
		dto.createDate = car.getCreateDate().toGMTString();
		return dto;
	}


}
