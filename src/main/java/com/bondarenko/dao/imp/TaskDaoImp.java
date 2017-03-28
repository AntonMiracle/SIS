package com.bondarenko.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bondarenko.dao.TaskDao;
import com.bondarenko.model.Task;

@Service
public class TaskDaoImp implements TaskDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Task save(Task task) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.save(task);
		return getById(task.getId());
	}

	@Override
	public boolean delete(Task task) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(task);
		session.flush();
		return true;
	}

	@Override
	public Task update(Task task) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(task);
		session.flush();
		return getById(task.getId());
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Task> getAll() throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Task> tasks = session.createQuery("from Task").list();
		return tasks;
	}

	@Override
	public Task getById(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Task as task where task.id = '" + id.toString() + "'");
		return (Task) query.uniqueResult();
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Task> getByUserId(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Task> tasks = session.createQuery("from Task as task where task.user.id = '" + id.toString() + "'").list();
		return tasks;
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Task> getByStatusId(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Task> tasks = session.createQuery("from Task as task where task.status.id = '" + id.toString() + "'")
				.list();
		return tasks;
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Task> getByProposalId(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Task> tasks = session.createQuery("from Task as task where task.proposal.id = '" + id.toString() + "'")
				.list();
		return tasks;
	}

}
