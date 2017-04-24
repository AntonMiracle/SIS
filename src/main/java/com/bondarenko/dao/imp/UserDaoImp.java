package com.bondarenko.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bondarenko.dao.UserDao;
import com.bondarenko.model.User;

@Service
public class UserDaoImp implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User save(User user) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		session.flush();
		return getById(user.getId());
	}

	@Override
	public boolean delete(User user) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
		session.flush();
		return true;
	}

	@Override
	public User update(User user) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		session.flush();
		return getById(user.getId());
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<User> getAll() throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery("from User").list();
		return users;
	}

	@Override
	public User getByUsername(String username) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User as user where user.username = '" + username + "'");
		return (User) query.uniqueResult();
	}

	@Override
	public User getById(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User as user where user.id = '" + id.toString() + "'");
		return (User) query.uniqueResult();
	}

	@Override
	public boolean isUsernameUnique(String username) throws RuntimeException {
		return getByUsername(username) == null ? true : false;
	}
	
	@Override
	public User getByUserInformationId(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User as user where user.userInformation.id = '" + id.toString() + "'");
		return (User) query.uniqueResult();
	}

}
