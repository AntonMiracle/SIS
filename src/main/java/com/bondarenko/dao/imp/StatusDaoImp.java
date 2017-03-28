package com.bondarenko.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bondarenko.dao.StatusDao;
import com.bondarenko.model.Status;

@Service
public class StatusDaoImp implements StatusDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Status save(Status status) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.save(status);
		return getById(status.getId());
	}

	@Override
	public boolean delete(Status status) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(status);
		session.flush();
		return true;
	}

	@Override
	public Status update(Status status) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(status);
		session.flush();
		return getById(status.getId());
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Status> getAll() throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Status> statuses = session.createQuery("from Status").list();
		return statuses;
	}

	@Override
	public Status getByName(String name) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Status as status where status.name = '" + name + "'");
		return (Status) query.uniqueResult();
	}

	@Override
	public Status getById(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Status as status where status.id = '" + id.toString() + "'");
		return (Status) query.uniqueResult();
	}

	@Override
	public boolean isNameUnique(String name) throws RuntimeException {
		return getByName(name) == null ? true : false;
	}

}
