package com.bondarenko.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.UserDao;
import com.bondarenko.dao.UserInformationDao;
import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.Role;
import com.bondarenko.model.Task;
import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;
import com.bondarenko.service.RoleService;
import com.bondarenko.service.UserService;
import com.bondarenko.util.DaoUtil;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInformationDao userInformationDao;
	@Autowired
	private RoleService roleService;

	@Override
	@Transactional
	public User save(User user) throws RuntimeException {
		if (checkNewUserFields(user)) {
			userDao.save(user);
			user = getById(user.getId());
		}
		return user;
	}

	@Override
	@Transactional
	public boolean delete(User user) throws RuntimeException {
		return userDao.delete(user);
	}

	@Override
	@Transactional
	public User update(User user) throws RuntimeException {
		return userDao.update(user);
	}

	@Override
	@Transactional
	public boolean delete(Long userId) throws RuntimeException {
		User user = getById(userId);
		if (user != null) {
			return delete(user);
		}
		return false;
	}

	@Override
	@Transactional
	public List<User> getUsers() throws RuntimeException {
		return userDao.getAll() == null ? new ArrayList<User>() : userDao.getAll();
	}

	@Override
	@Transactional
	public List<User> getByRolename(String name) throws RuntimeException {
		Role role = roleService.getByName(name);
		return role.getUsers() == null ? new ArrayList<User>() : role.getUsers();
	}

	@Override
	@Transactional
	public User getByPhone(String phone) throws RuntimeException {
		User user = null;
		if (userInformationDao.getByPhone(phone) != null) {
			user = userInformationDao.getByPhone(phone).getUser();
		}
		if (user != null) {
			nullFilter(user);
		}
		return user;
	}

	@Override
	@Transactional
	public User getById(Long id) throws RuntimeException {
		User user = userDao.getById(id);
		if (user != null) {
			nullFilter(user);
		}
		return user;
	}

	@Override
	@Transactional
	public User getByUsername(String username) throws RuntimeException {
		User user = userDao.getByUsername(username);
		if (user != null) {
			nullFilter(user);
		}
		return user;
	}

	@Override
	@Transactional
	public boolean checkNewUserFields(User user) throws RuntimeException {
		UserInformation ui = user.getUserInformation();
		String userName = user.getUsername();
		String pass = user.getPassword();
		if (userName != null && userName.length() > 0 && pass != null && pass.length() > 0 && ui != null
				&& ui.getPhone() != null && ui.getPhone().length() > 0) {
			ui.setMail(ui.getMail() == null ? "" : ui.getMail());
			ui.setName(ui.getName() == null ? "" : ui.getName());
			ui.setSurname(ui.getSurname() == null ? "" : ui.getSurname());
			ui.setCreateDate(ui.getCreateDate() == null ? Timestamp.valueOf(LocalDateTime.now()) : ui.getCreateDate());
			if (user.getRoles() == null || user.getRoles().size() == 0) {
				List<Role> roles = new ArrayList<>();
				roles.add(roleService.getByName(DaoUtil.ROLE_CLIENT));
				user.setRoles(roles);
			}
			nullFilter(user);
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean isUsernameUnique(String username) throws RuntimeException {
		return userDao.isUsernameUnique(username);
	}

	@Override
	@Transactional
	public boolean isPhoneUnique(String phone) throws RuntimeException {
		return userInformationDao.isPhoneUnique(phone);
	}

	@Override
	@Transactional
	public boolean isAuthenticationCorrect(String username, String password) throws RuntimeException {
		if (password != null && password.length() > 0 && userDao.getByUsername(username) != null
				&& userDao.getByUsername(username).getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public User nullFilter(User user) throws RuntimeException {
		user.setCars(user.getCars() == null ? new ArrayList<Car>() : user.getCars());
		user.setTasks(user.getTasks() == null ? new ArrayList<Task>() : user.getTasks());
		user.setProposals(user.getProposals() == null ? new ArrayList<Proposal>() : user.getProposals());
		return user;
	}

	@Override
	@Transactional
	public User addRoleByName(Long id, String name) throws RuntimeException {
		User user = getById(id);
		Role role = roleService.getByName(name);
		if (user != null && role != null) {
			user.getRoles().add(role);
			user = update(user);
		}
		return user;
	}

	@Override
	@Transactional
	public User removeRoleByName(Long id, String name) throws RuntimeException {
		User user = getById(id);
		Role role = roleService.getByName(name);
		if (user != null && role != null && user.getRoles() != null && user.getRoles().size() > 1) {
			for (int i = 0; i < user.getRoles().size(); i++) {
				if (user.getRoles().get(i).getName().equals(name)) {
					user.getRoles().remove(i);
					break;
				}
			}
			user = update(user);
		}
		return user;
	}

	@Override
	@Transactional
	public boolean isHasRole(Long userId, String name) throws RuntimeException {
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
	}

}
