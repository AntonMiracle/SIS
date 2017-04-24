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

import com.bondarenko.model.Task;
import com.bondarenko.model.User;

@WebAppConfiguration
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {DBUnitConfig.contextConfigurationLocation})
@TestExecutionListeners ({DependencyInjectionTestExecutionListener.class})
public class TaskServiceTest extends DBUnitConfig {
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private StatusService statusService;

	@Test
	public void shouldGetAllUsers() throws Exception {
		int countTasks = 5;
		Set<Task> tasks = taskService.getTasks();
		Assert.assertEquals(countTasks, tasks.size());
	}

	@Test
	public void shouldSaveTask() throws Exception {
		int countTasks = taskService.getTasks().size();
		User user = userService.getById(1L);
		String description = "some task descritption";
		Task task = new Task();
		task.setUser(user);
		task.setConclusion("");
		task.setDescription(description);
		task = taskService.save(task);
		Assert.assertNotNull(task);
		Assert.assertNotNull(task.getId());
		Assert.assertEquals(countTasks + 1, taskService.getTasks().size());
	}

	@Test
	public void shouldDeleteTaskById() throws Exception {
		int countTasks = taskService.getTasks().size();
		Long taskId = new Long(1);
		taskService.delete(taskId);
		Assert.assertEquals(countTasks - 1, taskService.getTasks().size());
		Assert.assertNull(taskService.getById(taskId));
	}

	@Test
	public void shouldUpdateTask() throws Exception {
		Long taskId = new Long(1);
		Task task = taskService.getById(taskId);
		String newDescription = "new description";
		Long newUserId = new Long(3);
		Long newStatusId = new Long(2);
		Assert.assertNotEquals(newDescription, task.getDescription());
		Assert.assertNotEquals(newUserId, task.getUser().getId());
		Assert.assertNotEquals(newStatusId, task.getStatus().getId());
		task.setUser(userService.getById(newUserId));
		task.setDescription(newDescription);
		task.setStatus(statusService.getById(newStatusId));
		task = taskService.update(task);
		Assert.assertEquals(newDescription, task.getDescription());
		Assert.assertEquals(newUserId, task.getUser().getId());
		Assert.assertEquals(newStatusId, task.getStatus().getId());
	}

	@Test
	public void shouldGetTaskById() throws Exception {
		Long taskId = new Long(1);
		Task task = taskService.getById(taskId);
		Assert.assertNotNull(task);
		Assert.assertEquals(taskId, task.getId());
		Assert.assertNotNull(task.getUser());
		Assert.assertNotNull(task.getUser().getId());
		Assert.assertNotNull(task.getCreateDate());
		Assert.assertNotNull(task.getDescription());
		Assert.assertNotNull(task.getConclusion());
		Assert.assertNotNull(task.getId());
		Assert.assertNotNull(task.getProposal());
		Assert.assertNotNull(task.getStatus());
	}

}
