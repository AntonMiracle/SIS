package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.Status;

public interface StatusService {

	public Status save(Status status) throws RuntimeException;// test-ok

	public boolean delete(Long statusId) throws RuntimeException;// test-ok

	public Status update(Status status) throws RuntimeException;// test-ok

	public Status getByName(String name) throws RuntimeException;// test-ok

	public Status getById(Long id) throws RuntimeException;// test-ok

	public boolean isNameUnique(String name) throws RuntimeException;// test-ok

	public Set<Status> getStatuses() throws RuntimeException;// test-ok
}
