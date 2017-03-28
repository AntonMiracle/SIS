package com.bondarenko.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bondarenko.dao.RoleDao;
import com.bondarenko.model.Role;
import com.bondarenko.model.User;
import com.bondarenko.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public Role save(Role role) throws RuntimeException {
		if (isNameUnique(role.getName())) {
			role = nullFilter(roleDao.save(role));
		} else {
			role = getByName(role.getName());
		}
		return role;
	}

	@Override
	@Transactional
	public Role save(String roleName) throws RuntimeException {
		Role role = null;
		if (isNameUnique(roleName)) {
			role = new Role();
			role.setName(roleName);
			role = save(role);
		} else {
			role = getByName(roleName);
		}
		return role;
	}

	@Override
	@Transactional
	public boolean delete(Role role) throws RuntimeException {
		if (role != null && role.getUsers().size() == 0) {
			return roleDao.delete(role);
		}
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long roleId) throws RuntimeException {
		Role role = getById(roleId);
		if (role != null) {
			return delete(role);
		}
		return false;
	}

	@Override
	@Transactional
	public Role update(Role role) throws RuntimeException {
		return roleDao.update(role);
	}

	@Override
	@Transactional
	public Role getByName(String name) throws RuntimeException {
		Role role = roleDao.getByName(name);
		if (role != null) {
			role = nullFilter(role);
		}
		return role;
	}

	@Override
	@Transactional
	public Role getById(Long id) throws RuntimeException {
		Role role = roleDao.getById(id);
		if (role != null && id != null && !id.equals(0L)) {
			role = nullFilter(role);
		}
		return role;
	}

	@Override
	@Transactional
	public boolean isNameUnique(String name) throws RuntimeException {
		return roleDao.isNameUnique(name);
	}

	@Override
	@Transactional
	public List<Role> getRoles() throws RuntimeException {
		return roleDao.getAll();
	}

	@Override
	@Transactional
	public Role nullFilter(Role role) throws RuntimeException {
		role.setUsers(role.getUsers() == null ? new ArrayList<User>() : role.getUsers());
		return role;
	}

	@Override
	@Transactional
	public boolean delete(String roleName) throws RuntimeException {
		if (!isNameUnique(roleName)) {
			return delete(getByName(roleName));
		}
		return false;
	}

	@Override
	@Transactional
	public Role updateName(String currentName, String newName) throws RuntimeException {
		Role role = null;
		if (isNameUnique(newName) && !isNameUnique(currentName)) {
			role = updateName(getByName(currentName).getId(), newName);
		}
		return role;
	}

	@Override
	@Transactional
	public Role updateName(Long id, String newName) throws RuntimeException {
		Role role = getById(id);
		if (isNameUnique(newName) && role != null) {
			role.setName(newName);
			role = update(role);
		}
		return role;
	}

	@Override
	@Transactional
	public boolean switchRoles(String currentName, String existName) throws RuntimeException {
		if (!isNameUnique(currentName) && !isNameUnique(existName) && !currentName.equals(existName)) {
			Role current = getByName(currentName);
			Role exist = getByName(existName);
			for (int i = 0; i < current.getUsers().size(); i++) {
				User user = current.getUsers().get(i);
				if (exist.getUsers().size() == 0) {
					exist.getUsers().add(user);
				} else {
					boolean isContainUser = false;
					for (int j = 0; j < exist.getUsers().size(); j++) {
						if (exist.getUsers().get(j).getId().equals(user.getId())) {
							isContainUser = true;
							break;
						}
					}
					if (!isContainUser) {
						exist.getUsers().add(user);
					}
				}
			}
			current.setUsers(new ArrayList<User>());
			update(current);
			update(exist);
			return true;
		}
		return false;
	}

}
