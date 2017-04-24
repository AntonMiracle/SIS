package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.Proposal;

public interface ProposalService {

	public Proposal save(Proposal proposal) throws RuntimeException;// test-ok

	public boolean delete(Long proposalId) throws RuntimeException;// test-ok

	public Proposal update(Proposal proposal) throws RuntimeException;// test-ok

	public Set<Proposal> getProposals() throws RuntimeException;// test-ok

	public Proposal getById(Long id) throws RuntimeException;// test-ok

}
