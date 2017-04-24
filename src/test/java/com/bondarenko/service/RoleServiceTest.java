package com.bondarenko.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bondarenko.model.Role;

@WebAppConfiguration
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {DBUnitConfig.contextConfigurationLocation})
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class RoleServiceTest extends DBUnitConfig {
	@Autowired
	private RoleService roleService;	

	@Test
	public void shouldGetAllRoles() throws Exception {
		int countRoles = 7;
		Set<Role> roles = roleService.getRoles();
		Assert.assertEquals(countRoles, roles.size());
	}

	@Test
	public void shouldSaveRole() throws Exception {
		int countRoles = roleService.getRoles().size();
		String newName = "some new Role";
		Role role = new Role();
		role.setName(newName);
		Assert.assertNull(role.getId());
		role = roleService.save(role);
		Assert.assertEquals(countRoles + 1, roleService.getRoles().size());
		Assert.assertNotNull(role.getId());
		Assert.assertNotNull(role.getUsers());
	}

	@Test
	public void shouldDeleteRoleById() throws Exception {
		int countRoles = roleService.getRoles().size();
		Long roleId = new Long(6);
		Assert.assertTrue(roleService.delete(roleId));
		Assert.assertEquals(countRoles - 1, roleService.getRoles().size());
		Assert.assertNull(roleService.getById(roleId));
	}

	@Test
	public void shouldUpdateRole() throws Exception {
		Long roleId = new Long(6);
		String newName = "someName";
		Role role = roleService.getById(roleId);
		Assert.assertNull(roleService.getByName(newName));
		Assert.assertNotNull(role);
		Assert.assertNotEquals(newName, role.getName());
		role.setName(newName);
		role = roleService.update(role);
		Assert.assertEquals(newName, role.getName());
	}

	@Test
	public void shouldGetRoleByName() throws Exception {
		String roleName = "role6";
		Long roleId = new Long(6);
		Role role = roleService.getByName(roleName);
		Assert.assertNotNull(role);
		Assert.assertNotNull(role.getId());
		Assert.assertNotNull(role.getUsers());
		Assert.assertEquals(roleId, role.getId());
		Assert.assertEquals(roleName, role.getName());
	}

	@Test
	public void shouldGetRoleById() throws Exception {
		String roleName = "role6";
		Long roleId = new Long(6);
		Role role = roleService.getById(roleId);
		Assert.assertNotNull(role);
		Assert.assertNotNull(role.getId());
		Assert.assertNotNull(role.getUsers());
		Assert.assertEquals(roleId, role.getId());
		Assert.assertEquals(roleName, role.getName());
	}

	@Test
	public void shouldCheckRoleNameForUnique() throws Exception {
		String newName = "role666";
		String existName = "role6";
		Assert.assertTrue(roleService.isNameUnique(newName));
		Assert.assertFalse(roleService.isNameUnique(existName));
	}

}
