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

import com.bondarenko.model.UserInformation;

@WebAppConfiguration
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {DBUnitConfig.contextConfigurationLocation})
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class UserInformationServiceTest extends DBUnitConfig {
	@Autowired
	private UserInformationService uiService;

	@Test
	public void shouldGetAllUsers() throws Exception {
		int countUsers = 7;
		Set<UserInformation> users = uiService.getUserInformations();
		Assert.assertEquals(countUsers, users.size());
	}

	@Test
	public void shouldGetUserInformationById() throws Exception {
		Long uiId = new Long(1);
		UserInformation ui = uiService.getById(uiId);
		Assert.assertNotNull(ui);
		Assert.assertEquals("name1", ui.getName());
		Assert.assertEquals("surname1", ui.getSurname());
		Assert.assertEquals(uiId, ui.getId());
	}
	
	@Test
	public void shouldGetUserInformationByPhone() throws Exception {
		String phone = "phone1";
		UserInformation ui = uiService.getByPhone(phone);
		Assert.assertNotNull(ui);
		Assert.assertEquals("name1", ui.getName());
		Assert.assertEquals("surname1", ui.getSurname());
		Assert.assertEquals(phone, ui.getPhone());
	}

	@Test
	public void shouldSaveUserInformation() throws Exception{
		UserInformation ui = new UserInformation();
		ui.setMail("some mail");
		ui.setName("some name");
		ui.setPhone("some phone");
		ui.setSurname("some surname");
		Assert.assertNull(ui.getId());
		ui = uiService.save(ui);
		Assert.assertNotNull(ui.getId());
		Assert.assertNotNull(uiService.getByPhone("some phone"));		
	}
	
	@Test
	public void shouldCheckIsPhoneNumberUnique() throws Exception{
		String existPhone = "phone1";
		String uniquePhone = "phone666";
		Assert.assertTrue(uiService.isPhoneUnique(uniquePhone));
		Assert.assertFalse(uiService.isPhoneUnique(existPhone));
	}
	
	@Test
	public void shouldUpdateUserInformation() throws Exception{
		String existPhone = "phone1";
		String uniquePhone = "phone666";
		UserInformation ui = uiService.getByPhone(existPhone);
		Assert.assertNotNull(ui);
		Assert.assertEquals(existPhone, ui.getPhone());
		ui.setPhone(uniquePhone);
		ui = uiService.update(ui);
		Assert.assertNull(uiService.getByPhone(existPhone));
		Assert.assertNotEquals(existPhone, ui.getPhone());
		Assert.assertEquals(uniquePhone, ui.getPhone());
	}
}
