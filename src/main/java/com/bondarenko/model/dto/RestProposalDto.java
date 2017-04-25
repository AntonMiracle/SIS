package com.bondarenko.model.dto;

import java.util.HashSet;
import java.util.Set;

public class RestProposalDto {
	public Long id;	
	public RestUserDto user = new RestUserDto();
	public String status;
	public String carNumber;
	public String description;
	public String createDate;
	public Set<Long> tasksId = new HashSet<>();	
	
}
