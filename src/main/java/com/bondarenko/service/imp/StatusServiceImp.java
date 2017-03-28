package com.bondarenko.service.imp;

import java.util.ArrayList;
import java.util.List;

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
	@Autowired
	private StatusDao statusDao;

	@Override
	@Transactional
	public Status save(Status status) throws RuntimeException {
		if (isNameUnique(status.getName())) {
			statusDao.save(status);
			nullFilter(status);
		} else {
			status = getByName(status.getName());
		}
		return status;
	}

	@Override
	@Transactional
	public Status save(String name) throws RuntimeException {
		Status status = null;
		if (isNameUnique(name)) {
			status = new Status();
			status.setName(name);
			status = save(status);
		} else {
			status = getByName(name);
		}
		return status;
	}

	@Override
	@Transactional
	public boolean delete(Status status) throws RuntimeException {
		if (status != null && status.getProposals().size() == 0 && status.getTasks().size() == 0) {
			return statusDao.delete(status);
		}
		return false;
	}

	@Override
	@Transactional
	public boolean delete(String name) throws RuntimeException {
		return isNameUnique(name) ? false : delete(statusDao.getByName(name));
	}

	@Override
	@Transactional
	public boolean delete(Long statusId) throws RuntimeException {
		Status status = getById(statusId);
		if (status != null) {
			return delete(status);
		}
		return false;
	}

	@Override
	@Transactional
	public Status update(Status status) throws RuntimeException {
		if (status != null) {
			status = nullFilter(statusDao.update(status));
		}
		return status;
	}

	@Override
	@Transactional
	public Status updateName(Long statusId, String newName) throws RuntimeException {
		Status status = getById(statusId);
		if (status != null) {
			status.setName(newName);
			status = save(status);
			nullFilter(status);
		}
		return status;
	}

	@Override
	@Transactional
	public Status updateName(String currentName, String newName) throws RuntimeException {
		Status status = getByName(currentName);
		if (status != null) {
			status.setName(newName);
			status = save(status);
			nullFilter(status);
		}
		return status;
	}

	@Override
	@Transactional
	public Status getByName(String name) throws RuntimeException {
		Status status = statusDao.getByName(name);
		if (status != null) {
			nullFilter(status);
		}
		return status;
	}

	@Override
	@Transactional
	public Status getById(Long id) throws RuntimeException {
		Status status = statusDao.getById(id);
		if (status != null) {
			nullFilter(status);
		}
		return status;
	}

	@Override
	@Transactional
	public boolean isNameUnique(String name) throws RuntimeException {
		return statusDao.isNameUnique(name);
	}

	@Override
	@Transactional
	public List<Status> getStatuses() throws RuntimeException {
		return statusDao.getAll();
	}

	@Override
	@Transactional
	public Status nullFilter(Status status) throws RuntimeException {
		status.setProposals(status.getProposals() == null ? new ArrayList<Proposal>() : status.getProposals());
		status.setTasks(status.getTasks() == null ? new ArrayList<Task>() : status.getTasks());
		return status;
	}

	@Override
	@Transactional
	public boolean switchProposalsStatus(String currentName, String existName) throws RuntimeException {
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
	}

	@Override
	@Transactional
	public boolean switchTasksStatus(String currentName, String existName) throws RuntimeException {
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
	}

}
