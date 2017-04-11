package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.UserDao;
import com.bondarenko.dao.UserInformationDao;
import com.bondarenko.maker.data.RoleMaker;
import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Role;
import com.bondarenko.model.Task;
import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.UserService;

@Service
public class UserServiceImp implements UserService {
	private static final Logger LOG = LogManager.getLogger();
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInformationDao userInformationDao;
	@Autowired
	private RoleService roleService;

	@Override
	@Transactional
	public User save(User user) throws RuntimeException {
		try {
			if (checkNewUserFields(user)) {				
				user = userDao.save(user);	
				nullFilter(user);
			}
			return user;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean delete(User user) throws RuntimeException {
		try {
			return userDao.delete(user);
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
			User user = getById(userId);
			if (user != null) {
				return delete(user);
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<User> getUsers() throws RuntimeException {
		try {
			return userDao.getAll() == null ? new ArrayList<User>() : userDao.getAll();
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public List<User> getByRolename(String name) throws RuntimeException {
		try {
			Role role = roleService.getByName(name);
			return role.getUsers() == null ? new ArrayList<User>() : role.getUsers();
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public User getByPhone(String phone) throws RuntimeException {
		try {
			User user = null;
			if (userInformationDao.getByPhone(phone) != null && userInformationDao.getByPhone(phone).getUser() != null) {
				user = getById(userInformationDao.getByPhone(phone).getUser().getId());				
			}			
			return user;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public User getById(Long id) throws RuntimeException {
		try {
			User user = userDao.getById(id);
			nullFilter(user);
			return user;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public User getByUsername(String username) throws RuntimeException {
		try {
			User user = userDao.getByUsername(username);
			if(user != null){
				user = getById(user.getId());
			}
			return user;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public boolean checkNewUserFields(User user) throws RuntimeException {
		try {
			if (user != null && user.getId() == null) {
				UserInformation ui = user.getUserInformation();
				String username = user.getUsername();
				String pass = user.getPassword();
				if (username != null && username.length() > 0 && pass != null && pass.length() > 0 
						&& ui.getPhone() != null && ui.getPhone().length() > 0 && isUsernameUnique(username)) {					
					ui.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
					ui.setUser(user);
					if(user.getRoles().size() == 0){
						user.getRoles().add(roleService.getByName(RoleMaker.ROLE_CLIENT));
					}			
					return true;
				}				
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}


	@Override
	public User nullFilter(User user) throws RuntimeException {
		try {
			if (user != null) {
				user.setCars(user.getCars() == null ? new ArrayList<Car>() : user.getCars());
				user.setTasks(user.getTasks() == null ? new ArrayList<Task>() : user.getTasks());
				user.setProposals(user.getProposals() == null ? new ArrayList<Proposal>() : user.getProposals());				
			}
			return user;
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
	public boolean isPhoneUnique(String phone) throws RuntimeException {
		try {
			return userInformationDao.isPhoneUnique(phone);
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
	public User addRoleByName(Long id, String name) throws RuntimeException {
		try {
			User user = getById(id);
			Role role = roleService.getByName(name);
			if (user != null && role != null && !isHasRole(id, name)) {
				user.getRoles().add(role);
				user = update(user);
			}
			return user;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}

	@Override
	@Transactional
	public User removeRoleByName(Long id, String name) throws RuntimeException {
		try {
			User user = getById(id);
			Role role = roleService.getByName(name);
			if (user != null && role != null && user.getRoles() != null && user.getRoles().size() > 1 && isHasRole(id, name)) {
				for (int i = 0; i < user.getRoles().size(); i++) {
					if (user.getRoles().get(i).getName().equals(name)) {
						user.getRoles().remove(i);
						break;
					}
				}
				user = update(user);
			}
			return user;
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
			Role role = roleService.getByName(name);
			if (user != null && role != null && user.getRoles() != null) {
				for (Role r : user.getRoles()) {
					if (r.getName().equals(name)) {
						return true;
					}
				}
			}
			return false;
		} catch (RuntimeException ex) {
			LOG.error(ex, ex);
			throw ex;
		}
	}
}
