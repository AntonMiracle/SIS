package com.bondarenko.model.dto;

import java.util.HashSet;
import java.util.Set;

public class RestCarDto {
	public Long id;
	public Long userId;
	public String number;
	public String model;
	public String mark;
	public String description;
	public String createDate;
	public Set<Long> proposalsId = new HashSet<>();
}
