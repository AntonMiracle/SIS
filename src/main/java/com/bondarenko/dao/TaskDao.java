package com.bondarenko.dao;

import java.util.List;

import com.bondarenko.model.Task;

public interface TaskDao {
	public Task save(Task task) throws RuntimeException;

	public boolean delete(Task task) throws RuntimeException;

	public Task update(Task task) throws RuntimeException;

	public List<Task> getAll() throws RuntimeException;

	public Task getById(Long id) throws RuntimeException;

	public List<Task> getByUserId(Long id) throws RuntimeException;

	public List<Task> getByStatusId(Long id) throws RuntimeException;

	public List<Task> getByProposalId(Long id) throws RuntimeException;

}
