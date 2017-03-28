package com.bondarenko.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bondarenko.dao.RoleDao;
import com.bondarenko.model.Role;

@Service
public class RoleDaoImp implements RoleDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role save(Role role) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
		return getById(role.getId());
	}

	@Override
	public boolean delete(Role role) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(role);
		session.flush();
		return true;
	}

	@Override
	public Role update(Role role) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(role);
		session.flush();
		return getById(role.getId());
	}

	@SuppressWarnings ("unchecked")
	@Override
	public List<Role> getAll() throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roles = session.createQuery("from Role").list();
		return roles;
	}

	@Override
	public Role getByName(String name) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role as role where role.name = '" + name + "'");
		return (Role) query.uniqueResult();
	}

	@Override
	public Role getById(Long id) throws RuntimeException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role as role where role.id = '" + id.toString() + "'");
		return (Role) query.uniqueResult();
	}

	@Override
	public boolean isNameUnique(String name) throws RuntimeException {
		return getByName(name) == null ? true : false;
	}

}
