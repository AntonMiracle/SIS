package com.bondarenko.dao;

import java.util.List;

import com.bondarenko.model.Status;

public interface StatusDao {
	public Status save(Status status) throws RuntimeException;

	public boolean delete(Status status) throws RuntimeException;

	public Status update(Status status) throws RuntimeException;

	public List<Status> getAll() throws RuntimeException;

	public Status getByName(String name) throws RuntimeException;

	public Status getById(Long id) throws RuntimeException;

	public boolean isNameUnique(String name) throws RuntimeException;
}
