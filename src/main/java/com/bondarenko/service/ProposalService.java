package com.bondarenko.service;

import java.util.List;

import com.bondarenko.model.Proposal;

public interface ProposalService {

	public Proposal save(Proposal proposal) throws RuntimeException;//test-ok
	
	public Proposal save(Long userId, Long carId, String description) throws RuntimeException;//test-ok

	public boolean delete(Proposal proposal) throws RuntimeException;//test-ok
	
	public boolean delete(Long proposalId) throws RuntimeException;

	public Proposal update(Proposal proposal) throws RuntimeException;//test-ok

	public List<Proposal> getProposals() throws RuntimeException;//test-ok

	public List<Proposal> getByUserId(Long id) throws RuntimeException;//test-ok

	public Proposal getById(Long id) throws RuntimeException;//test-ok

	public List<Proposal> getByStatusId(Long id) throws RuntimeException;//test-ok

	public List<Proposal> getByCarId(Long id) throws RuntimeException;//test-ok

	public boolean checkNewProposalFields(Proposal proposal) throws RuntimeException;//test-ok

	public Proposal setStatusByName(Long proposalId, String name) throws RuntimeException;	//test-ok
}
