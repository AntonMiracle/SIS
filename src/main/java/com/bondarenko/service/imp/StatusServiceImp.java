package com.bondarenko.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.StatusDao;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Status;
import com.bondarenko.model.Task;
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
			if (isNameUnique(status.getName())) {
				statusDao.save(status);
				nullFilter(status);
			} else {
				status = getByName(status.getName());
			}
			return status;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status save(String name) throws RuntimeException {
		try {
			Status status = null;
			if (isNameUnique(name)) {
				status = new Status();
				status.setName(name);
				status = save(status);
			} else {
				status = getByName(name);
			}
			return status;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Status status) throws RuntimeException {
		try {
			if (status != null && status.getProposals().size() == 0 && status.getTasks().size() == 0) {
				return statusDao.delete(status);
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(String name) throws RuntimeException {
		try {
			return isNameUnique(name) ? false : delete(statusDao.getByName(name));
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long statusId) throws RuntimeException {
		try {
			Status status = getById(statusId);
			if (status != null) {
				return delete(status);
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status update(Status status) throws RuntimeException {
		try {
			if (status != null) {
				status = nullFilter(statusDao.update(status));
			}
			return status;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status updateName(Long statusId, String newName) throws RuntimeException {
		try {
			Status status = getById(statusId);
			if (status != null) {
				status.setName(newName);
				status = save(status);
				nullFilter(status);
			}
			return status;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status updateName(String currentName, String newName) throws RuntimeException {
		try {
			Status status = getByName(currentName);
			if (status != null) {
				status.setName(newName);
				status = save(status);
				nullFilter(status);
			}
			return status;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status getByName(String name) throws RuntimeException {
		try {
			Status status = statusDao.getByName(name);
			if (status != null) {
				nullFilter(status);
			}
			return status;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status getById(Long id) throws RuntimeException {
		try {
			Status status = statusDao.getById(id);
			if (status != null) {
				nullFilter(status);
			}
			return status;
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
	public List<Status> getStatuses() throws RuntimeException {
		try {
			return statusDao.getAll();
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Status nullFilter(Status status) throws RuntimeException {
		try {
			status.setProposals(status.getProposals() == null ? new ArrayList<Proposal>() : status.getProposals());
			status.setTasks(status.getTasks() == null ? new ArrayList<Task>() : status.getTasks());
			return status;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean switchProposalsStatus(String currentName, String existName) throws RuntimeException {
		try {
			if (!isNameUnique(currentName) && !isNameUnique(existName)) {
				Status current = statusDao.getByName(currentName);
				Status exist = statusDao.getByName(existName);
				for (int i = 0; i < current.getProposals().size(); i++) {
					exist.getProposals().add(current.getProposals().get(i));
				}
				current.setProposals(new ArrayList<Proposal>());
				update(current);
				update(exist);
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
	public boolean switchTasksStatus(String currentName, String existName) throws RuntimeException {
		try {
			if (!isNameUnique(currentName) && !isNameUnique(existName)) {
				Status current = statusDao.getByName(currentName);
				Status exist = statusDao.getByName(existName);
				for (int i = 0; i < current.getTasks().size(); i++) {
					exist.getTasks().add(current.getTasks().get(i));
				}
				current.setTasks(new ArrayList<Task>());
				update(current);
				update(exist);
				return true;
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

}
