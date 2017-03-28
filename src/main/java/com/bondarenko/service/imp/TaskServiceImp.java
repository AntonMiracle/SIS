package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.TaskDao;
import com.bondarenko.model.Status;
import com.bondarenko.model.Task;
import com.bondarenko.model.User;
import com.bondarenko.service.StatusService;
import com.bondarenko.service.TaskService;
import com.bondarenko.service.UserService;
import com.bondarenko.util.DaoUtil;

@Service
public class TaskServiceImp implements TaskService {
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private StatusService statusService;
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public Task save(Task task) throws RuntimeException {
		if (checkNewTaskFields(task)) {
			task = taskDao.save(task);
		}
		return task;
	}

	@Override
	@Transactional
	public Task save(Long userId, String description) throws RuntimeException {
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
	}

	@Override
	@Transactional
	public boolean delete(Task task) throws RuntimeException {
		return taskDao.delete(task);
	}

	@Override
	@Transactional
	public Task update(Task task) throws RuntimeException {
		return taskDao.update(task);
	}

	@Override
	@Transactional
	public boolean delete(Long taskId) throws RuntimeException {
		Task task = getById(taskId);
		if (task != null) {
			return delete(task);
		}
		return false;
	}

	@Override
	@Transactional
	public Task updateDescription(Long taskId, String description) throws RuntimeException {
		Task task = getById(taskId);
		if (task != null) {
			task.setDescription(description);
			task = update(task);
		}
		return task;
	}

	@Override
	@Transactional
	public Task updateConclusion(Long taskId, String conclusion) throws RuntimeException {
		Task task = getById(taskId);
		if (task != null) {
			task.setConclusion(conclusion);
			task = update(task);
		}
		return task;
	}

	@Override
	@Transactional
	public List<Task> getTasks() throws RuntimeException {
		return taskDao.getAll();
	}

	@Override
	@Transactional
	public Task getById(Long id) throws RuntimeException {
		return taskDao.getById(id);
	}

	@Override
	@Transactional
	public List<Task> getByUserId(Long id) throws RuntimeException {
		return taskDao.getByUserId(id);
	}

	@Override
	@Transactional
	public List<Task> getByStatusId(Long id) throws RuntimeException {
		return taskDao.getByStatusId(id);
	}

	@Override
	@Transactional
	public List<Task> getByProposalId(Long id) throws RuntimeException {
		return taskDao.getByProposalId(id);
	}

	@Override
	@Transactional
	public boolean checkNewTaskFields(Task task) throws RuntimeException {
		User user = task.getUser();
		if (user != null && task.getDescription() != null && task.getDescription().length() > 0) {
			task.setConclusion("");
			task.setCreateDate(
					task.getCreateDate() == null ? Timestamp.valueOf(LocalDateTime.now()) : task.getCreateDate());
			task.setStatus(task.getStatus() == null ? statusService.getByName(DaoUtil.STATUS_OPEN) : task.getStatus());
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public Task setStatusByName(Long id, String name) throws RuntimeException {
		Task task = getById(id);
		if (task != null) {
			Status status = statusService.getByName(name);
			task.setStatus(status);
			task = update(task);
		}
		return task;
	}

	@Override
	@Transactional
	public Task setUserById(Long taskId, Long userId) throws RuntimeException {
		Task task = getById(taskId);
		User user = userService.getById(userId);
		if (user != null && task != null) {
			task.setUser(user);
			task = update(task);
		}
		return task;
	}

	@Override
	@Transactional
	public Task setConclusion(Long id, String conclusion) throws RuntimeException {
		Task task = getById(id);
		if (task != null) {
			task.setConclusion(conclusion);
			task = update(task);
		}
		return task;
	}

	@Override
	@Transactional
	public Task setDescription(Long id, String description) throws RuntimeException {
		Task task = getById(id);
		if (task != null) {
			task.setDescription(description);
			task = update(task);
		}
		return task;
	}

}
