package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.ProposalDao;
import com.bondarenko.maker.data.StatusMaker;
import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Status;
import com.bondarenko.model.User;
import com.bondarenko.service.CarService;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.UserService;

@Service
public class ProposalServiceImp implements ProposalService {
	private static final Logger LOG = LogManager.getLogger();
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
		try {
			if (checkNewProposalFields(proposal)) {
				proposalDao.save(proposal);
			}
			return proposal;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Proposal save(Long userId, Long carId, String description) throws RuntimeException {
		try {
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
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Proposal proposal) throws RuntimeException {
		try {
			return proposalDao.delete(proposal);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long proposalId) throws RuntimeException {
		try {
			Proposal proposal = getById(proposalId);
			if (proposal != null) {
				return delete(proposal);
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Proposal update(Proposal proposal) throws RuntimeException {
		try {
			return proposalDao.update(proposal);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Proposal> getProposals() throws RuntimeException {
		try {
			return proposalDao.getAll();
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Proposal> getByUserId(Long id) throws RuntimeException {
		try {
			return proposalDao.getByUserId(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Proposal getById(Long id) throws RuntimeException {
		try {
			return proposalDao.getById(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Proposal> getByStatusId(Long id) throws RuntimeException {
		try {
			return proposalDao.getByStatusId(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Proposal> getByCarId(Long id) throws RuntimeException {
		try {
			return proposalDao.getByCarId(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean checkNewProposalFields(Proposal proposal) throws RuntimeException {
		try {
			User user = proposal.getUser();
			Car car = proposal.getCar();
			if (user != null && user.getId() != null && car != null && car.getId() != null
					&& proposal.getDescription().length() > 0) {
				proposal.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
				proposal.setStatus(statusService.getByName(StatusMaker.STATUS_OPEN));
				return true;
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Proposal setStatusByName(Long proposalId, String name) throws RuntimeException {
		try {
			Status status = statusService.getByName(name);
			Proposal proposal = getById(proposalId);
			if (proposal != null && status != null) {
				proposal.setStatus(status);
				proposal = update(proposal);
			}
			return proposal;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

}
