package com.bondarenko.service;

import java.util.Set;

import com.bondarenko.model.Car;
import com.bondarenko.model.Proposal;
import com.bondarenko.model.User;
import com.bondarenko.model.dto.RestCarDto;
import com.bondarenko.model.dto.RestNewUserDto;
import com.bondarenko.model.dto.RestProposalDto;
import com.bondarenko.model.dto.RestUserDto;

public interface RestService {
	
	public Set<RestUserDto> getRestUsersDto() throws RuntimeException;
	
	public Set<RestUserDto> getRestClientsDto() throws RuntimeException;

	public Set<RestProposalDto> getRestProposalsDto() throws RuntimeException;

	public Set<RestCarDto> getRestCarsDto() throws RuntimeException;

	public RestUserDto convertUser(User user) throws RuntimeException;

	public RestProposalDto convertProposal(Proposal proposal) throws RuntimeException;

	public void saveNewUserDto(RestNewUserDto dto) throws RuntimeException;

	public RestCarDto convertCar(Car car) throws RuntimeException;

}
