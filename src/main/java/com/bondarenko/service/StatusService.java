package com.bondarenko.service;

import java.util.List;

import com.bondarenko.model.Status;

public interface StatusService {

	public Status save(Status status) throws RuntimeException;// test-ok

	public Status save(String name) throws RuntimeException;// test-ok

	public boolean delete(Status status) throws RuntimeException;// test-ok

	public boolean delete(String name) throws RuntimeException;// test-ok

	public boolean delete(Long statusId) throws RuntimeException;// test-ok

	public Status update(Status status) throws RuntimeException;// test-ok

	public Status updateName(Long statusId, String newName) throws RuntimeException;// test-ok

	public Status updateName(String currentName, String newName) throws RuntimeException;// test-ok

	public Status getByName(String name) throws RuntimeException;// test-ok

	public Status getById(Long id) throws RuntimeException;// test-ok

	public boolean isNameUnique(String name) throws RuntimeException;// test-ok

	public boolean switchProposalsStatus(String currentName, String existName) throws RuntimeException;// test-ok

	public boolean switchTasksStatus(String currentName, String existName) throws RuntimeException;// test-ok

	public Status nullFilter(Status status) throws RuntimeException;// test-ok

	public List<Status> getStatuses() throws RuntimeException;// test-ok
}
