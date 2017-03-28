package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.ProposalDao;
import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Status;
import com.bondarenko.model.User;
import com.bondarenko.service.CarService;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.UserService;
import com.bondarenko.util.DaoUtil;

@Service
public class ProposalServiceImp implements ProposalService {
	@Autowired
	private ProposalDao proposalDao;
	@Autowired
	private StatusService statusService;
	@Autowired
	private UserService userService;
	@Autowired
	private CarService carService;

	@Override
	@Transactional
	public Proposal save(Proposal proposal) throws RuntimeException {
		if (checkNewProposalFields(proposal)) {
			proposalDao.save(proposal);
		}
		return proposal;
	}

	@Override
	@Transactional
	public Proposal save(Long userId, Long carId, String description) throws RuntimeException {
		Proposal proposal = null;
		User user = userService.getById(userId);
		Car car = carService.getById(carId);
		if (user != null && car != null) {
			proposal = new Proposal();
			proposal.setCar(car);
			proposal.setUser(user);
			proposal.setDescription(description);
			proposal = save(proposal);
		}
		return proposal;
	}

	@Override
	@Transactional
	public boolean delete(Proposal proposal) throws RuntimeException {
		return proposalDao.delete(proposal);
	}

	@Override
	@Transactional
	public boolean delete(Long proposalId) throws RuntimeException {
		Proposal proposal = getById(proposalId);
		if (proposal != null) {
			return delete(proposal);
		}
		return false;
	}

	@Override
	@Transactional
	public Proposal update(Proposal proposal) throws RuntimeException {
		return proposalDao.update(proposal);
	}

	@Override
	@Transactional
	public List<Proposal> getProposals() throws RuntimeException {
		return proposalDao.getAll();
	}

	@Override
	@Transactional
	public List<Proposal> getByUserId(Long id) throws RuntimeException {
		return proposalDao.getAllByUserId(id);
	}

	@Override
	@Transactional
	public Proposal getById(Long id) throws RuntimeException {
		return proposalDao.getById(id);
	}

	@Override
	@Transactional
	public List<Proposal> getByStatusId(Long id) throws RuntimeException {
		return proposalDao.getAllByStatusId(id);
	}

	@Override
	@Transactional
	public List<Proposal> getByCarId(Long id) throws RuntimeException {
		return proposalDao.getAllByCarId(id);
	}

	@Override
	@Transactional
	public boolean checkNewProposalFields(Proposal proposal) throws RuntimeException {
		User user = proposal.getUser();
		Car car = proposal.getCar();
		if (user != null && user.getId() != null && car != null && car.getId() != null
				&& proposal.getDescription() != null && proposal.getDescription().length() > 0) {
			proposal.setCreateDate(proposal.getCreateDate() == null ? Timestamp.valueOf(LocalDateTime.now())
					: proposal.getCreateDate());
			proposal.setStatus(statusService.getByName(DaoUtil.STATUS_OPEN));
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public Proposal setStatusByName(Long proposalId, String name) throws RuntimeException {
		Status status = statusService.getByName(name);
		Proposal proposal = getById(proposalId);
		if (proposal != null && status != null) {
			proposal.setStatus(status);
			proposal = update(proposal);
		}
		return proposal;
	}

}
