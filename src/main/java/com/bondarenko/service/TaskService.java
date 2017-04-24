package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.Task;

public interface TaskService {
	public Task save(Task task) throws RuntimeException;// test-ok

	public boolean delete(Long taskId) throws RuntimeException;// test-ok

	public Task update(Task task) throws RuntimeException;// test-ok

	public Set<Task> getTasks() throws RuntimeException;// test-ok

	public Task getById(Long id) throws RuntimeException;// test-ok
}
