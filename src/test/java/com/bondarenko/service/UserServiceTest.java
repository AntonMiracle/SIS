package com.bondarenko.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;

@WebAppConfiguration
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {DBUnitConfig.contextConfigurationLocation})
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class UserServiceTest extends DBUnitConfig {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Test
	public void shouldGetAllUsers() throws Exception {
		int countUsers = 7;
		List<User> users = userService.getUsers();
		Assert.assertEquals(countUsers, users.size());
	}

	@Test
	public void shouldGetUserById() throws Exception {
		String phone = "phone2";
		String surname = "surname2";
		String mail = "mail2";
		String name = "name2";
		String username = "username2";
		String password = "pass2";
		Long userId = new Long(2);
		User user = userService.getById(userId);
		UserInformation ui = user.getUserInformation();
		Assert.assertNotNull(user);
		Assert.assertNotNull(ui);
		Assert.assertNotNull(ui.getCreateDate());
		Assert.assertEquals(phone, ui.getPhone());
		Assert.assertEquals(name, ui.getName());
		Assert.assertEquals(surname, ui.getSurname());
		Assert.assertEquals(mail, ui.getMail());
		Assert.assertEquals(userId, ui.getId());
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals(username, user.getUsername());
		Assert.assertEquals(password, user.getPassword());
		Assert.assertEquals(Timestamp.valueOf("2016-12-1 00:00:00.0"), ui.getCreateDate());
	}

	@Test
	public void shouldGetUserByUsername() throws Exception {
		String phone = "phone2";
		String surname = "surname2";
		String mail = "mail2";
		String name = "name2";
		String username = "username2";
		String password = "pass2";
		User user = userService.getByUsername(username);
		UserInformation ui = user.getUserInformation();
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertNotNull(ui);
		Assert.assertNotNull(ui.getId());
		Assert.assertNotNull(ui.getCreateDate());
		Assert.assertEquals(phone, ui.getPhone());
		Assert.assertEquals(name, ui.getName());
		Assert.assertEquals(surname, ui.getSurname());
		Assert.assertEquals(mail, ui.getMail());
		Assert.assertEquals(username, user.getUsername());
		Assert.assertEquals(password, user.getPassword());
		Assert.assertEquals(Timestamp.valueOf("2016-12-1 00:00:00.0"), ui.getCreateDate());
	}

	@Test
	public void shouldGetUserInformation() throws Exception {
		Long userId = new Long(1);
		Long userInformationId = new Long(1);
		User user = userService.getById(userId);
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals(userInformationId, user.getUserInformation().getId());
		Assert.assertEquals("phone1", user.getUserInformation().getPhone());
		Assert.assertEquals("name1", user.getUserInformation().getName());
		Assert.assertEquals("mail1", user.getUserInformation().getMail());
		Assert.assertEquals("surname1", user.getUserInformation().getSurname());
		Assert.assertEquals(Timestamp.valueOf("2016-12-1 00:00:00.0"), user.getUserInformation().getCreateDate());
	}

	@Test
	public void shouldGetUserByPhone() throws Exception {
		String phone = "phone1";
		String surname = "surname1";
		String mail = "mail1";
		String name = "name1";
		String username = "username1";
		String password = "pass1";
		Long userId = new Long(1);
		User user = userService.getByPhone(phone);
		UserInformation ui = user.getUserInformation();
		Assert.assertNotNull(user);
		Assert.assertNotNull(ui);
		Assert.assertNotNull(ui.getCreateDate());
		Assert.assertEquals(phone, ui.getPhone());
		Assert.assertEquals(name, ui.getName());
		Assert.assertEquals(surname, ui.getSurname());
		Assert.assertEquals(mail, ui.getMail());
		Assert.assertEquals(userId, user.getId());
		Assert.assertEquals(userId, ui.getId());
		Assert.assertEquals(username, user.getUsername());
		Assert.assertEquals(password, user.getPassword());
		Assert.assertEquals(Timestamp.valueOf("2016-12-1 00:00:00.0"), ui.getCreateDate());
	}

	@Test
	public void shouldDeleteUser() throws Exception {
		Long userId = new Long(7);
		int countUsers = userService.getUsers().size();
		User user = userService.getById(userId);
		Assert.assertTrue(userService.delete(user));
		Assert.assertEquals(countUsers - 1, userService.getUsers().size());
		Assert.assertNull(userService.getById(userId));

	}

	@Test
	public void shouldDeleteUserById() throws Exception {
		Long userId = new Long(7);
		int countUsers = userService.getUsers().size();
		Assert.assertTrue(userService.delete(userId));
		Assert.assertEquals(countUsers - 1, userService.getUsers().size());
		Assert.assertNull(userService.getById(userId));

	}

	@Test
	public void shouldGetUsersByRoleName() throws Exception {
		String roleName = "role1";
		int countUsersWithRoleName = 3;
		List<User> users = userService.getByRolename(roleName);
		Assert.assertNotNull(users);
		Assert.assertEquals(countUsersWithRoleName, users.size());
	}

	@Test
	public void shouldAuthenticationByUsernameAndPassword() throws Exception {
		String username = "username1";
		String password = "pass1";
		Assert.assertNotNull(userService.getByUsername(username));
		Assert.assertEquals(password, userService.getByUsername(username).getPassword());
		Assert.assertTrue(userService.isAuthenticationCorrect(username, password));
	}

	@Test
	public void shouldCheckPhoneForUnique() throws Exception {
		String phone1 = "phone1";
		String phone2 = "phone122";
		Assert.assertNotNull(userService.getByPhone(phone1));
		Assert.assertFalse(userService.isPhoneUnique(phone1));
		Assert.assertNull(userService.getByPhone(phone2));
		Assert.assertTrue(userService.isPhoneUnique(phone2));
	}

	@Test
	public void shouldCheckUsernameForUnique() throws Exception {
		String username1 = "username1";
		String username2 = "username122";
		Assert.assertNotNull(userService.getByUsername(username1));
		Assert.assertFalse(userService.isUsernameUnique(username1));
		Assert.assertNull(userService.getByUsername(username2));
		Assert.assertTrue(userService.isUsernameUnique(username2));
	}

	@Test
	public void shouldSaveUser() throws Exception {
		int countUsers = userService.getUsers().size();
		String username = "username";
		String password = "pass";
		String phone = "phone";
		UserInformation ui = new UserInformation();
		ui.setPhone(phone);
		User user = new User();
		user.setUserInformation(ui);
		user.setUsername(username);
		user.setPassword(password);
		Assert.assertNull(user.getId());
		Assert.assertNull(user.getUserInformation().getId());
		user = userService.save(user);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertNotNull(user.getUserInformation());
		Assert.assertNotNull(user.getUserInformation().getId());
		Assert.assertNotNull(user.getUserInformation().getPhone());
		Assert.assertNotNull(user.getUserInformation().getUser().getId());
		Assert.assertNotNull(userService.getByPhone(phone));
		Assert.assertNotNull(userService.getByPhone(phone).getUserInformation().getUser().getId());
		Assert.assertNotNull(userService.getByUsername(username));
		Assert.assertNotNull(userService.getByUsername(username).getUserInformation().getUser().getId());
		Assert.assertNotNull(userService.getById(user.getId()));
		Assert.assertNotNull(userService.getById(user.getId()).getUserInformation().getUser().getId());
		Assert.assertEquals(phone, user.getUserInformation().getPhone());
		Assert.assertEquals(countUsers + 1, userService.getUsers().size());
	}

	@Test
	public void shouldUpdateUser() throws Exception {
		int countUsers = userService.getUsers().size();
		String username = "username1";
		String newUsername = "new_username1";
		String newPhone = "new_phone1";
		User user = userService.getByUsername(username);
		user.setUsername(newUsername);
		user.getUserInformation().setPhone(newPhone);
		user = userService.update(user);
		Assert.assertNull(userService.getByUsername(username));
		Assert.assertNotNull(user);
		Assert.assertEquals(countUsers, userService.getUsers().size());
		Assert.assertEquals(newUsername, user.getUsername());
		Assert.assertEquals(newPhone, user.getUserInformation().getPhone());
		Assert.assertEquals(user.getId(), userService.getByPhone(newPhone).getId());

	}

	@Test
	public void shouldFillUserNullFieldsAfterGetById() throws Exception {
		String username = "username3344";
		String password = "pass";
		String phone = "phone22344";
		UserInformation ui = new UserInformation();
		ui.setPhone(phone);
		User user = new User();
		user.setUserInformation(ui);
		user.setUsername(username);
		user.setPassword(password);
		Assert.assertNull(user.getId());
		Assert.assertNotNull(user.getRoles());		
		Assert.assertNull(user.getUserInformation().getId());
		Assert.assertNull(user.getUserInformation().getCreateDate());
		user = userService.save(user);
		Assert.assertNotNull(user.getId());
		Assert.assertNotNull(user.getTasks());
		Assert.assertNotNull(user.getCars());
		Assert.assertNotNull(user.getProposals());
		Assert.assertNotNull(user.getRoles());
		Assert.assertNotNull(user.getUserInformation());
		Assert.assertNotNull(user.getUserInformation().getMail());
		Assert.assertNotNull(user.getUserInformation().getName());
		Assert.assertNotNull(user.getUserInformation().getSurname());
		Assert.assertNotNull(user.getUserInformation().getId());
		Assert.assertNotNull(user.getUserInformation().getCreateDate());
	}

	@Test
	public void shouldCheckNewUserFieldsAndFillDefault() throws Exception {
		String username = "username";
		String password = "pass";
		String phone = "phone";
		String roleName = "role6";
		UserInformation ui = new UserInformation();
		List<Role> roles = new ArrayList<>();
		roles.add( roleService.getByName(roleName));
		ui.setPhone(phone);
		User user = new User();
		user.setUserInformation(ui);
		user.setUsername(username);
		user.setPassword(password);		
		user.setRoles(roles);		
		Assert.assertNull(user.getId());		
		Assert.assertNull(user.getUserInformation().getId());
		Assert.assertNull(user.getUserInformation().getCreateDate());
		Assert.assertTrue(userService.checkNewUserFields(user));
		Assert.assertNotNull(user.getRoles());
		Assert.assertEquals(1, user.getRoles().size());		
		Assert.assertTrue(userService.checkNewUserFields(user));
		Assert.assertNotNull(user.getUserInformation().getMail());
		Assert.assertNotNull(user.getUserInformation().getName());
		Assert.assertNotNull(user.getUserInformation().getSurname());
		Assert.assertNotNull(user.getUserInformation().getCreateDate());
		
		
		
	}

	@Test
	public void shouldSaveUserWithRequiredFields() throws Exception {
		String username = "username";
		String password = "pass";
		String phone = "phone";
		UserInformation ui = new UserInformation();
		User user = new User();
		Assert.assertNull(userService.save(user).getId());
		user.setUserInformation(ui);
		ui.setPhone(phone);
		Assert.assertNull(userService.save(user).getId());
		user.setUsername(username);
		Assert.assertNull(userService.save(user).getId());
		user.setPassword(password);
		Assert.assertNotNull(userService.save(user).getId());
	}

	@SuppressWarnings ("deprecation")
	@Test
	public void shouldSetCreateDateOnSave() throws Exception {
		String username = "username";
		String password = "pass";
		String phone = "phone";
		UserInformation ui = new UserInformation();
		ui.setPhone(phone);
		User user = new User();
		user.setUserInformation(ui);
		user.setUsername(username);
		user.setPassword(password);
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Timestamp create = userService.save(user).getUserInformation().getCreateDate();
		Assert.assertEquals(now.getDate(), create.getDate());
		Assert.assertEquals(now.getMonth(), create.getMonth());
		Assert.assertEquals(now.getYear(), create.getYear());
		Assert.assertEquals(now.getHours(), create.getHours());
		Assert.assertEquals(now.getMinutes(), create.getMinutes());
	}

	@Test
	public void shouldAddRole() throws Exception {
		String roleName = "role3";
		Long userId = new Long(1);
		User user = userService.getById(userId);
		int coutUserRolesBeforeAdd = user.getRoles().size();
		Assert.assertNotNull(user);
		Assert.assertNotNull(roleService.getByName(roleName));
		Assert.assertFalse(userService.isHasRole(user.getId(), roleName));
		user = userService.addRoleByName(userId, roleName);
		Assert.assertEquals(coutUserRolesBeforeAdd + 1, user.getRoles().size());
		Assert.assertTrue(userService.isHasRole(user.getId(), roleName));
	}

	@Test
	public void shouldCheckIsUserHasRole() throws Exception {
		String roleName = "role1";
		Long userId = new Long(1);
		Assert.assertTrue(userService.isHasRole(userId, roleName));
	}

	@Test
	public void shouldRemoveRole() throws Exception {
		String roleName1 = "role1";
		String roleName2 = "role4";
		Long userId1 = new Long(1);
		Long userId2 = new Long(3);
		User user1 = userService.getById(userId1);// has 2 roles
		User user2 = userService.getById(userId2);// has 1 role
		Assert.assertNotNull(user1);
		Assert.assertNotNull(user2);
		int countRoles1 = user1.getRoles().size();
		int countRoles2 = user2.getRoles().size();
		Assert.assertTrue(userService.isHasRole(userId1, roleName1));
		Assert.assertTrue(userService.isHasRole(userId2, roleName2));
		user1 = userService.removeRoleByName(userId1, roleName1);
		user2 = userService.removeRoleByName(userId2, roleName2);
		Assert.assertEquals(countRoles1 - 1, user1.getRoles().size());
		Assert.assertEquals(countRoles2, user2.getRoles().size());
	}

}
