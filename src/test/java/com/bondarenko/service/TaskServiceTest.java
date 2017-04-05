package com.bondarenko.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

import com.bondarenko.model.Task;
import com.bondarenko.model.User;
import com.bondarenko.util.DaoUtil;

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
	@Autowired
	private ProposalService proposalService;

	@Test
	public void shouldGetAllUsers() throws Exception {
		int countTasks = 5;
		List<Task> tasks = taskService.getTasks();
		Assert.assertEquals(countTasks, tasks.size());
	}

	@Test
	public void shouldSaveTask() throws Exception {
		int countTasks = taskService.getTasks().size();
		User user = userService.getById(1L);
		String description = "some task descritption";
		Task task = new Task();
		task.setUser(user);
		task.setDescription(description);
		task = taskService.save(task);
		Assert.assertNotNull(task);
		Assert.assertNotNull(task.getId());
		Assert.assertEquals(countTasks + 1, taskService.getTasks().size());
	}

	@Test
	public void shouldDeleteTask() throws Exception {
		int countTasks = taskService.getTasks().size();
		Long taskId = new Long(1);
		Task task = taskService.getById(taskId);
		Long userId = task.getUser().getId();
		taskService.delete(task);
		Assert.assertEquals(countTasks - 1, taskService.getTasks().size());
		Assert.assertNotNull(userService.getById(userId));
		Assert.assertNull(taskService.getById(taskId));
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

	@Test
	public void shouldGetTasksByUserId() throws Exception {
		Long userId = new Long(1);
		int countTasksExpect = 3;
		Assert.assertNotNull(userService.getById(userId));
		List<Task> tasks = taskService.getByUserId(userId);
		Assert.assertNotNull(tasks);
		Assert.assertEquals(countTasksExpect, tasks.size());
	}

	@Test
	public void shouldGetTasksByStatusId() throws Exception {
		Long statusId = new Long(1);
		int countTasksExpect = 3;
		Assert.assertNotNull(statusService.getById(statusId));
		List<Task> tasks = taskService.getByStatusId(statusId);
		Assert.assertNotNull(tasks);
		Assert.assertEquals(countTasksExpect, tasks.size());
	}

	@Test
	public void shouldGetTasksByProposalId() throws Exception {
		Long proposalId = new Long(1);
		int countTasksExpect = 2;
		Assert.assertNotNull(proposalService.getById(proposalId));
		List<Task> tasks = taskService.getByProposalId(proposalId);
		Assert.assertNotNull(tasks);
		Assert.assertEquals(countTasksExpect, tasks.size());
	}

	@SuppressWarnings ("deprecation")
	@Test
	public void shouldCheckNewTaskFields() throws Exception {
		Long userId = new Long(1);
		String description = "some description";
		User user = userService.getById(userId);
		Task task = new Task();
		task.setUser(user);
		task.setDescription(description);
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		Assert.assertNotNull(user);
		Assert.assertNotNull(task);
		Assert.assertNull(task.getConclusion());
		Assert.assertNotNull(statusService.getByName(DaoUtil.STATUS_OPEN));
		Assert.assertTrue(taskService.checkNewTaskFields(task));
		Assert.assertNotNull(task.getConclusion());
		Assert.assertNotNull(task.getCreateDate());
		Assert.assertEquals(now.getHours(), task.getCreateDate().getHours());
		Assert.assertEquals(now.getMinutes(), task.getCreateDate().getMinutes());
		Assert.assertEquals(now.getYear(), task.getCreateDate().getYear());
		Assert.assertEquals(now.getMonth(), task.getCreateDate().getMonth());
		Assert.assertEquals(now.getDate(), task.getCreateDate().getDate());
		Assert.assertEquals(DaoUtil.STATUS_OPEN, task.getStatus().getName());
	}

	@Test
	public void shouldSetNewTaskStatus() throws Exception {
		Long taskId = new Long(1);
		String newStatusName = "status4";
		Task task = taskService.getById(taskId);
		Assert.assertNotNull(task);
		Assert.assertNotNull(statusService.getByName(newStatusName));
		task = taskService.setStatusByName(task.getId(), newStatusName);
		Assert.assertEquals(newStatusName, task.getStatus().getName());
	}

	@Test
	public void shouldSetNewTaskUser() throws Exception {
		Long taskId = new Long(1);
		Long userId = new Long(4);
		Task task = taskService.getById(taskId);
		User user = userService.getById(userId);
		Assert.assertNotNull(task);
		Assert.assertNotNull(user);
		Assert.assertNotEquals(userId, task.getUser().getId());
		task = taskService.setUserById(taskId, userId);
		Assert.assertEquals(userId, task.getUser().getId());
	}

	@Test
	public void shouldSetNewTaskConclusion() throws Exception {
		Long taskId = new Long(1);
		String conclusion = "some conclusion";
		Task task = taskService.getById(taskId);
		Assert.assertNotNull(task);
		Assert.assertNotEquals(conclusion, task.getConclusion());
		task = taskService.setConclusion(taskId, conclusion);
		Assert.assertEquals(conclusion, task.getConclusion());
	}

	@Test
	public void shouldSetNewTaskDescription() throws Exception {
		Long taskId = new Long(1);
		String description = "some description";
		Task task = taskService.getById(taskId);
		Assert.assertNotNull(task);
		Assert.assertNotEquals(description, task.getDescription());
		task = taskService.setDescription(taskId, description);
		Assert.assertEquals(description, task.getDescription());
	}

	@Test
	public void shouldSaveTaskByUserIdAndDescription() throws Exception {
		Long userId = new Long(1);
		String description = "some description";
		int countTasks = taskService.getTasks().size();
		taskService.save(userId, description);
		Assert.assertEquals(countTasks + 1, taskService.getTasks().size());
	}	
}
