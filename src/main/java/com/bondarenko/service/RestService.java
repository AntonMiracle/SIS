package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.Proposal;
import com.bondarenko.model.User;
import com.bondarenko.model.dto.RestNewUserDto;
import com.bondarenko.model.dto.RestProposalDto;
import com.bondarenko.model.dto.RestUserDto;

public interface RestService {
	public Set<RestUserDto> getRestUsersDto() throws RuntimeException;
	public Set<RestProposalDto> getRestProposalsDto() throws RuntimeException;
	public RestUserDto convertUser(User user) throws RuntimeException;
	public RestProposalDto convertProposal(Proposal proposal) throws RuntimeException;
	public User convertNewUserDto(RestNewUserDto dto) throws RuntimeException;
	
}
