package com.bondarenko.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bondarenko.dao.UserInformationDao;
import com.bondarenko.model.UserInformation;

@Service
public class UserInformationDaoImp implements UserInformationDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserInformation save(UserInformation userInformation) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.save(userInformation);
		return getById(userInformation.getId());
	}

	@Override
	public boolean delete(UserInformation userInformation) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(userInformation);
		session.flush();
		return true;
	}

	@Override
	public UserInformation update(UserInformation userInformation) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userInformation);
		session.flush();
		return getById(userInformation.getId());
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<UserInformation> getAll() throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<UserInformation> users = session.createQuery("from UserInformation").list();
		return users;
	}

	@Override
	public UserInformation getByPhone(String phone) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserInformation as ui where ui.phone = '" + phone + "'");
		return (UserInformation) query.uniqueResult();
	}

	@Override
	public boolean isPhoneUnique(String phone) throws RuntimeException {
		return getByPhone(phone) == null ? true : false;
	}

	@Override
	public UserInformation getById(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserInformation as ui where ui.id = '" + id.toString() + "'");
		return (UserInformation) query.uniqueResult();
	}

	@Override
	public UserInformation getByUserId(Long userId) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserInformation as ui where ui.user.id = '" + userId.toString() + "'");
		return (UserInformation) query.uniqueResult();
	}

}
