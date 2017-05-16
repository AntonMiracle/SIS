package com.bondarenko.service.imp;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.UserDao;
import com.bondarenko.model.Role;
import com.bondarenko.model.User;
import com.bondarenko.service.UserInformationService;
import com.bondarenko.service.UserService;

@Service
public class UserServiceImp implements UserService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInformationService uiService;

	@Override
	@Transactional
	public User save(User user) throws RuntimeException {
		try {
			user.setUserInformation(uiService.save(user.getUserInformation()));
			return userDao.save(user);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public User update(User user) throws RuntimeException {
		try {
			return userDao.update(user);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long userId) throws RuntimeException {
		try {
			return userDao.delete(getById(userId));
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public Set<User> getUsers() throws RuntimeException {
		try {
			Set<User> users = new HashSet<>();
			for (User user : userDao.getAll()) {
				users.add(user);
			}
			return users;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public User getByPhone(String phone) throws RuntimeException {
		try {
			return userDao.getByUserInformationId(uiService.getByPhone(phone).getId());
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public User getById(Long id) throws RuntimeException {
		try {
			return userDao.getById(id);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public User getByUsername(String username) throws RuntimeException {
		try {
			return userDao.getByUsername(username);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean isUsernameUnique(String username) throws RuntimeException {
		try {
			return userDao.isUsernameUnique(username);
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean isAuthenticationCorrect(String username, String password) throws RuntimeException {
		try {
			if (password != null && password.length() > 0 && userDao.getByUsername(username) != null
					&& userDao.getByUsername(username).getPassword().equals(password)) {
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
	public boolean isHasRole(Long userId, String name) throws RuntimeException {
		try {
			User user = getById(userId);
			for (Role role : user.getRoles()) {
				if (role.getName().equals(name)) {
					return true;
				}
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}
}
