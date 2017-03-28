package com.bondarenko.dao;

import java.util.List;

import com.bondarenko.model.Proposal;

public interface ProposalDao {
	public Proposal save(Proposal proposal) throws RuntimeException;

	public boolean delete(Proposal proposal) throws RuntimeException;

	public Proposal update(Proposal proposal) throws RuntimeException;

	public List<Proposal> getAll() throws RuntimeException;

	public Proposal getById(Long id) throws RuntimeException;

	public List<Proposal> getAllByUserId(Long id) throws RuntimeException;

	public List<Proposal> getAllByStatusId(Long id) throws RuntimeException;

	public List<Proposal> getAllByCarId(Long id) throws RuntimeException;

}
