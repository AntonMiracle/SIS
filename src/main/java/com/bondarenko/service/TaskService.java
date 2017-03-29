package com.bondarenko.service;

import java.util.List;

import com.bondarenko.model.Task;

public interface TaskService {
	public Task save(Task task) throws RuntimeException;// test-ok

	public Task save(Long userId, String description) throws RuntimeException;// test-ok

	public boolean delete(Task task) throws RuntimeException;// test-ok

	public boolean delete(Long taskId) throws RuntimeException;// test-ok

	public Task update(Task task) throws RuntimeException;// test-ok

	public List<Task> getTasks() throws RuntimeException;// test-ok

	public Task getById(Long id) throws RuntimeException;// test-ok

	public List<Task> getByUserId(Long id) throws RuntimeException;// test-ok

	public List<Task> getByStatusId(Long id) throws RuntimeException;// test ok

	public List<Task> getByProposalId(Long id) throws RuntimeException;// test-ok

	public boolean checkNewTaskFields(Task task) throws RuntimeException;// test-ok

	public Task setStatusByName(Long id, String name) throws RuntimeException;// test-ok

	public Task setUserById(Long taskId, Long userId) throws RuntimeException;// test-ok

	public Task setConclusion(Long id, String conclusion) throws RuntimeException;// test-ok

	public Task setDescription(Long id, String description) throws RuntimeException;// test-ok
}
