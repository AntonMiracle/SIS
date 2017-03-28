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

import com.bondarenko.model.Status;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = "classpath:config/sis-context.xml")
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class StatusServiceTest extends DBUnitConfig {
	@Autowired
	private StatusService statusService;

	@Test
	public void shouldGetAllStatuses() throws Exception {
		int countStatuses = 6;
		List<Status> statuses = statusService.getStatuses();
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
	public void shouldSaveStatusByName() throws Exception {
		String statusName = "newStatus";
		int countStatuses = statusService.getStatuses().size();
		Status status = statusService.save(statusName);
		Assert.assertNotNull(status);
		Assert.assertNotNull(status.getId());
		Assert.assertNotNull(status.getProposals());
		Assert.assertNotNull(status.getTasks());
		Assert.assertEquals(statusName, statusService.getByName(statusName).getName());
		Assert.assertEquals(countStatuses + 1, statusService.getStatuses().size());
	}

	@Test
	public void shouldDeleteStatus() throws Exception {
		String statusName = "status6";
		int countStatuses = statusService.getStatuses().size();
		Status status = statusService.getByName(statusName);
		Assert.assertNotNull(status);
		Assert.assertNotNull(status.getId());
		Assert.assertNotNull(status.getProposals());
		Assert.assertNotNull(status.getTasks());
		Assert.assertTrue(statusService.delete(status));
		Assert.assertNull(statusService.getByName(statusName));
		Assert.assertTrue(statusService.isNameUnique(statusName));
		Assert.assertEquals(countStatuses - 1, statusService.getStatuses().size());
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
	public void shouldNotDeleteStatusInUse() throws Exception {
		String statusName = "status1";
		int countStatuses = statusService.getStatuses().size();
		Status status = statusService.getByName(statusName);
		Assert.assertNotNull(status);
		Assert.assertNotNull(status.getId());
		Assert.assertNotNull(status.getProposals());
		Assert.assertNotNull(status.getTasks());
		Assert.assertFalse(statusService.delete(status));
		Assert.assertNotNull(statusService.getByName(statusName));
		Assert.assertFalse(statusService.isNameUnique(statusName));
		Assert.assertEquals(countStatuses, statusService.getStatuses().size());
	}

	@Test
	public void shouldDeleteStatusByName() throws Exception {
		String statusName = "status6";
		int countStatuses = statusService.getStatuses().size();
		Assert.assertNotNull(statusService.getByName(statusName));
		statusService.delete(statusName);
		Assert.assertNull(statusService.getByName(statusName));
		Assert.assertTrue(statusService.isNameUnique(statusName));
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
	public void shouldUpdateStatusNameById() throws Exception {
		String newName = "newStatus";
		Long statusId = new Long(6);
		Assert.assertNotNull(statusService.getById(statusId));
		Assert.assertNotEquals(newName, statusService.getById(statusId).getName());
		Status status = statusService.updateName(statusId, newName);
		Assert.assertNotNull(status);
		Assert.assertEquals(newName, status.getName());
	}

	@Test
	public void shouldUpdateStatusNameByName() throws Exception {
		String newName = "newStatus";
		String currentName = "status6";
		Assert.assertNotNull(statusService.getByName(currentName));
		Assert.assertNull(statusService.getByName(newName));
		Status status = statusService.updateName(currentName, newName);
		Assert.assertNotNull(status);
		Assert.assertEquals(newName, status.getName());
	}

	@Test
	public void shouldCheckIsStatusNameUnique() throws Exception {
		String name = "status1";
		Assert.assertNotNull(statusService.getByName(name));
		Assert.assertFalse(statusService.isNameUnique(name));
	}

	@Test
	public void shouldFillStatusNullFieldsAfterGet() throws Exception {
		String name = "SomeStatus";
		Status status = new Status();
		status.setName(name);
		Assert.assertNull(status.getId());
		Assert.assertNull(status.getProposals());
		Assert.assertNull(status.getTasks());
		statusService.save(status);
		status = statusService.getById(status.getId());
		Assert.assertNotNull(status.getId());
		Assert.assertNotNull(status.getProposals());
		Assert.assertNotNull(status.getTasks());
		Assert.assertNotNull(status.getName());
	}

	@Test
	public void shouldCheckNullFilter() throws Exception {
		String name = "SomeStatus";
		Status status = new Status();
		status.setName(name);
		Assert.assertNull(status.getId());
		Assert.assertNull(status.getProposals());
		Assert.assertNull(status.getTasks());
		status = statusService.nullFilter(status);
		Assert.assertNotNull(status.getProposals());
		Assert.assertNotNull(status.getTasks());
		Assert.assertNotNull(status.getName());
	}

	@Test
	public void shouldSwitchCurrentStatusOnExisting() throws Exception {
		Status current = statusService.getById(1L);
		Status exist = statusService.getById(6L);
		int currentProposals = current.getProposals().size();
		int currentTasks = current.getTasks().size();
		int existProposals = exist.getProposals().size();
		int existTasks = exist.getTasks().size();
		Assert.assertNotNull(current);
		Assert.assertNotNull(exist);
		Assert.assertTrue(statusService.switchProposalsStatus(current.getName(), exist.getName()));
		Assert.assertTrue(statusService.switchTasksStatus(current.getName(), exist.getName()));
		current = statusService.getById(1L);
		exist = statusService.getById(6L);
		Assert.assertNotNull(current);
		Assert.assertNotNull(exist);
		Assert.assertEquals(0, current.getProposals().size());
		Assert.assertEquals(0, current.getTasks().size());
		Assert.assertEquals(currentProposals + existProposals, exist.getProposals().size());
		Assert.assertEquals(currentTasks + existTasks, exist.getTasks().size());
	}

}
