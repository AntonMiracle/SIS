package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.ProposalDao;
import com.bondarenko.maker.data.StatusMaker;
import com.bondarenko.model.Proposal;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.StatusService;

@Service
public class ProposalServiceImp implements ProposalService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private ProposalDao proposalDao;
	@Autowired
	private StatusService statusService;

	@Override
	@Transactional
	public Proposal save(Proposal proposal) throws RuntimeException {
		try {
			proposal.setStatus(statusService.getByName(StatusMaker.STATUS_OPEN));
			proposal.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			return proposalDao.save(proposal);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long proposalId) throws RuntimeException {
		try {
			return proposalDao.delete(getById(proposalId));
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
	public Set<Proposal> getProposals() throws RuntimeException {
		try {
			Set<Proposal> proposals = new HashSet<>();
			for (Proposal proposal : proposalDao.getAll()) {
				proposals.add(proposal);
			}
			return proposals;
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

}
