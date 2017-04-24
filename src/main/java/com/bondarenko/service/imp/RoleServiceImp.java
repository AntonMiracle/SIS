package com.bondarenko.service.imp;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.RoleDao;
import com.bondarenko.model.Role;
import com.bondarenko.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public Role save(Role role) throws RuntimeException {
		try {
			return roleDao.save(role);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long roleId) throws RuntimeException {
		try {
			return roleDao.delete(getById(roleId));
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Role update(Role role) throws RuntimeException {
		try {
			return roleDao.update(role);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Role getByName(String name) throws RuntimeException {
		try {
			return roleDao.getByName(name);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Role getById(Long id) throws RuntimeException {
		try {
			return roleDao.getById(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean isNameUnique(String name) throws RuntimeException {
		try {
			return roleDao.isNameUnique(name);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Set<Role> getRoles() throws RuntimeException {
		try {
			Set<Role> roles = new HashSet<>();
			for (Role role : roleDao.getAll()) {
				roles.add(role);
			}
			return roles;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

}
