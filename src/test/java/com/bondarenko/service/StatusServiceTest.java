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

import com.bondarenko.model.Status;

@WebAppConfiguration
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {DBUnitConfig.contextConfigurationLocation})
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class StatusServiceTest extends DBUnitConfig {
	@Autowired
	private StatusService statusService;

	@Test
	public void shouldGetAllStatuses() throws Exception {
		int countStatuses = 6;
		Set<Status> statuses = statusService.getStatuses();
		Assert.assertEquals(countStatuses, statuses.size());
	}

	@Test
	public void shouldSaveStatus() throws Exception {
		String statusName = "newStatus";
		Status status = new Status();
		status.setName(statusName);
		int countStatuses = statusService.getStatuses().size();
		status = statusService.save(status);
		Assert.assertNotNull(status);
		Assert.assertNotNull(status.getId());
		Assert.assertNotNull(status.getProposals());
		Assert.assertNotNull(status.getTasks());
		Assert.assertEquals(statusName, statusService.getByName(statusName).getName());
		Assert.assertEquals(countStatuses + 1, statusService.getStatuses().size());
	}

	@Test
	public void shouldDeleteStatusById() throws Exception {
		Long statusId = new Long(6);
		int countStatuses = statusService.getStatuses().size();
		Assert.assertTrue(statusService.delete(statusId));
		Assert.assertNull(statusService.getById(statusId));
		Assert.assertEquals(countStatuses - 1, statusService.getStatuses().size());
	}

	@Test
	public void shouldGetStatusByName() throws Exception {
		String statusName = "status1";
		Status status = statusService.getByName(statusName);
		Assert.assertNotNull(status);
		Assert.assertNotNull(status.getName());
		Assert.assertNotNull(status.getId());
		Assert.assertNotNull(status.getProposals());
		Assert.assertNotNull(status.getTasks());
		Assert.assertEquals(statusName, status.getName());
	}

	@Test
	public void shouldGetStatusById() throws Exception {
		Long statusId = new Long(1);
		Status status = statusService.getById(statusId);
		Assert.assertNotNull(status);
		Assert.assertNotNull(status.getName());
		Assert.assertNotNull(status.getId());
		Assert.assertNotNull(status.getProposals());
		Assert.assertNotNull(status.getTasks());
		Assert.assertEquals(statusId, status.getId());
	}

	@Test
	public void shouldUpdateStatus() throws Exception {
		String newName = "newStatus";
		Long statusId = new Long(6);
		Status status = statusService.getById(statusId);
		Assert.assertNotNull(status);
		Assert.assertNotEquals(newName, status.getName());
		status.setName(newName);
		status = statusService.update(status);
		Assert.assertNotNull(status);
		Assert.assertEquals(newName, status.getName());

	}

	@Test
	public void shouldCheckIsStatusNameUnique() throws Exception {
		String name = "status1";
		Assert.assertNotNull(statusService.getByName(name));
		Assert.assertFalse(statusService.isNameUnique(name));
	}

}
