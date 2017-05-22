package com.bondarenko.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.bondarenko.model.dto.RestNewUserDto;
import com.bondarenko.model.dto.RestProposalDto;
import com.bondarenko.model.dto.RestUserDto;

public interface RestServiceController {

	public ResponseEntity<Set<RestUserDto>> getUsers() throws RuntimeException;

	public ResponseEntity<Set<RestUserDto>> getClients() throws RuntimeException;

	public ResponseEntity<Set<RestProposalDto>> getProposals() throws RuntimeException;

	public ResponseEntity<RestNewUserDto> makeNewClient(RestNewUserDto dto) throws RuntimeException;

	public ResponseEntity<RestUserDto> getById(Long id) throws RuntimeException;	

	public ResponseEntity<Boolean> checkUsernameAndPassword(RestUserDto DTO) throws RuntimeException;

}
