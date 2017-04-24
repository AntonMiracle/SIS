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

import com.bondarenko.dao.TaskDao;
import com.bondarenko.model.Task;
import com.bondarenko.service.TaskService;

@Service
public class TaskServiceImp implements TaskService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private TaskDao taskDao;

	@Override
	@Transactional
	public Task save(Task task) throws RuntimeException {
		try {
			task.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
			return taskDao.save(task);
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
			return taskDao.delete(getById(taskId));
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Set<Task> getTasks() throws RuntimeException {
		try {
			Set<Task> tasks = new HashSet<>();
			for (Task task : taskDao.getAll()) {
				tasks.add(task);
			}
			return tasks;
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

}
