package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.TaskDao;
import com.bondarenko.maker.data.StatusMaker;
import com.bondarenko.model.Status;
import com.bondarenko.model.Task;
import com.bondarenko.model.User;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.TaskService;
import com.bondarenko.service.UserService;

@Service
public class TaskServiceImp implements TaskService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private StatusService statusService;
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public Task save(Task task) throws RuntimeException {
		try {
			if (checkNewTaskFields(task)) {
				task = taskDao.save(task);
			}
			return task;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Task save(Long userId, String description) throws RuntimeException {
		try {
			Task task = new Task();
			User user = userService.getById(userId);
			if (user != null) {
				task.setUser(user);
				task.setDescription(description);
				if (checkNewTaskFields(task)) {
					task = save(task);
				}
			}
			return task;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Task task) throws RuntimeException {
		try {
			return taskDao.delete(task);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Task update(Task task) throws RuntimeException {
		try {
			return taskDao.update(task);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long taskId) throws RuntimeException {
		try {
			Task task = getById(taskId);
			if (task != null) {
				return delete(task);
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Task> getTasks() throws RuntimeException {
		try {
			return taskDao.getAll();
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Task getById(Long id) throws RuntimeException {
		try {
			return taskDao.getById(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Task> getByUserId(Long id) throws RuntimeException {
		try {
			return taskDao.getByUserId(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Task> getByStatusId(Long id) throws RuntimeException {
		try {
			return taskDao.getByStatusId(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<Task> getByProposalId(Long id) throws RuntimeException {
		try {
			return taskDao.getByProposalId(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean checkNewTaskFields(Task task) throws RuntimeException {
		try {
			User user = task.getUser();
			if (user != null && task.getDescription().length() > 0) {				
				task.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
				task.setStatus(task.getStatus() == null ? statusService.getByName(StatusMaker.STATUS_OPEN) : task.getStatus());
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
	public Task setStatusByName(Long id, String name) throws RuntimeException {
		try {
			Task task = getById(id);
			if (task != null) {
				Status status = statusService.getByName(name);
				task.setStatus(status);
				task = update(task);
			}
			return task;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Task setUserById(Long taskId, Long userId) throws RuntimeException {
		try {
			Task task = getById(taskId);
			User user = userService.getById(userId);
			if (user != null && task != null) {
				task.setUser(user);
				task = update(task);
			}
			return task;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Task setConclusion(Long id, String conclusion) throws RuntimeException {
		try {
			Task task = getById(id);
			if (task != null) {
				task.setConclusion(conclusion);
				task = update(task);
			}
			return task;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Task setDescription(Long id, String description) throws RuntimeException {
		try {
			Task task = getById(id);
			if (task != null) {
				task.setDescription(description);
				task = update(task);
			}
			return task;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

}
