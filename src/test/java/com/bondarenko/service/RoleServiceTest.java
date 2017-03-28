package com.bondarenko.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bondarenko.model.Role;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "classpath:config/sis-context.xml")
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class RoleServiceTest extends DBUnitConfig {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@Test
	public void shouldGetAllRoles() throws Exception {
		int countRoles = 6;
		List<Role> roles = roleService.getRoles();
		Assert.assertEquals(countRoles, roles.size());
	}

	@Test
	public void shouldSaveRole() throws Exception {
		int countRoles = roleService.getRoles().size();
		String newName = "some new Role";
		Role role = new Role();
		role.setName(newName);
		Assert.assertNull(role.getId());
		Assert.assertNull(role.getUsers());
		role = roleService.save(role);
		Assert.assertEquals(countRoles + 1, roleService.getRoles().size());
		Assert.assertNotNull(role.getId());
		Assert.assertNotNull(role.getUsers());
	}

	@Test
	public void shouldSaveRoleByName() throws Exception {
		int countRoles = roleService.getRoles().size();
		String newName = "some new Role";
		Role role = roleService.save(newName);
		Assert.assertEquals(countRoles + 1, roleService.getRoles().size());
		Assert.assertNotNull(role.getId());
		Assert.assertNotNull(role.getUsers());
	}

	@Test
	public void shouldDeleteRole() throws Exception {
		int countRoles = roleService.getRoles().size();
		Long roleid = new Long(6);
		Role role = roleService.getById(roleid);
		Assert.assertNotNull(role.getId());
		Assert.assertNotNull(role.getUsers());
		Assert.assertEquals(0, role.getUsers().size());
		Assert.assertTrue(roleService.delete(role));
		Assert.assertEquals(countRoles - 1, roleService.getRoles().size());
		Assert.assertNull(roleService.getById(roleid));
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
	public void shouldDeleteRoleByName() throws Exception {
		int countRoles = roleService.getRoles().size();
		String roleName = "role6";
		Assert.assertNotNull(roleService.getByName(roleName));
		Assert.assertEquals(0, roleService.getByName(roleName).getUsers().size());
		Assert.assertTrue(roleService.delete(roleName));
		Assert.assertEquals(countRoles - 1, roleService.getRoles().size());
		Assert.assertNull(roleService.getByName(roleName));
	}

	@Test
	public void shouldNotDeleteRoleInUse() throws Exception {
		int countRoles = roleService.getRoles().size();
		Long roleId1 = new Long(1);
		Long roleId2 = new Long(2);
		String roleName = "role3";
		Role role = roleService.getById(roleId1);
		Assert.assertFalse(roleService.delete(role));
		Assert.assertEquals(countRoles, roleService.getRoles().size());
		Assert.assertFalse(roleService.delete(roleName));
		Assert.assertEquals(countRoles, roleService.getRoles().size());
		Assert.assertFalse(roleService.delete(roleId2));
		Assert.assertEquals(countRoles, roleService.getRoles().size());
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
	public void shouldUpdateRoleNameById() throws Exception {
		Long roleId = new Long(6);
		String newName = "someName";
		Role role = roleService.getById(roleId);
		Assert.assertNull(roleService.getByName(newName));
		Assert.assertNotNull(roleService.getById(roleId));
		Assert.assertNotEquals(newName, roleService.getById(roleId).getName());
		role = roleService.updateName(roleId, newName);
		Assert.assertNotNull(roleService.getByName(newName));
		Assert.assertEquals(newName, role.getName());
	}

	@Test
	public void shouldUpdateRoleNameByName() throws Exception {
		String currentName = "role6";
		String newName = "someName";
		Assert.assertNull(roleService.getByName(newName));
		Assert.assertNotNull(roleService.getByName(currentName));
		Assert.assertNotEquals(newName, currentName);
		Role role = roleService.updateName(currentName, newName);
		Assert.assertNotNull(roleService.getByName(newName));
		Assert.assertNull(roleService.getByName(currentName));
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

	@Test
	public void shouldSwitchCurrentRoleOnExistingByRoleName() throws Exception {
		String currentName1 = "role1";
		String currentName2 = "role2";
		String existName1 = "role4";
		String existName2 = "role5";
		Role current1 = roleService.getByName(currentName1);
		Role current2 = roleService.getByName(currentName2);
		Role exist1 = roleService.getByName(existName1);
		Role exist2 = roleService.getByName(existName2);
		int countUsersOfCurrent1 = current1.getUsers().size();
		int countUsersOfCurrent2 = current2.getUsers().size();
		int countUsersOfExist1 = exist1.getUsers().size();
		int countUsersOfExist2 = exist2.getUsers().size();
		Assert.assertNotNull(current1);
		Assert.assertNotNull(current2);
		Assert.assertNotNull(exist1);
		Assert.assertNotNull(exist2);
		Assert.assertNotEquals(0, countUsersOfCurrent1);
		Assert.assertNotEquals(0, countUsersOfCurrent2);
		Assert.assertNotEquals(0, countUsersOfExist1);
		Assert.assertEquals(0, countUsersOfExist2);
		roleService.switchRoles(currentName1, existName1);
		roleService.switchRoles(currentName2, existName2);
		current1 = roleService.getByName(currentName1);
		current2 = roleService.getByName(currentName2);
		exist1 = roleService.getByName(existName1);
		exist2 = roleService.getByName(existName2);
		Assert.assertEquals(0, current1.getUsers().size());
		Assert.assertEquals(0, current2.getUsers().size());
		Assert.assertEquals(userService.getByRolename(existName1).size(), exist1.getUsers().size());
		Assert.assertEquals(userService.getByRolename(existName2).size(), exist2.getUsers().size());
	}

}
