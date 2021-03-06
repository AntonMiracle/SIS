package com.bondarenko.service.imp;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.StatusDao;
import com.bondarenko.model.Status;
import com.bondarenko.service.StatusService;

@Service
public class StatusServiceImp implements StatusService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private StatusDao statusDao;

	@Override
	@Transactional
	public Status save(Status status) throws RuntimeException {
		try {
			return statusDao.save(status);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long statusId) throws RuntimeException {
		try {
			return statusDao.delete(getById(statusId));
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status update(Status status) throws RuntimeException {
		try {
			return statusDao.update(status);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status getByName(String name) throws RuntimeException {
		try {
			return statusDao.getByName(name);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status getById(Long id) throws RuntimeException {
		try {
			return statusDao.getById(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean isNameUnique(String name) throws RuntimeException {
		try {
			return statusDao.isNameUnique(name);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Set<Status> getStatuses() throws RuntimeException {
		try {
			Set<Status> statuses = new HashSet<>();
			for (Status status : statusDao.getAll()) {
				statuses.add(status);
			}
			return statuses;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

}
