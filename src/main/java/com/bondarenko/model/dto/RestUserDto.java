package com.bondarenko.model.dto;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class RestUserDto implements Comparator<RestUserDto>, Comparable<RestUserDto> {
	public Long id;
	public String username;
	public String password;
	public String phone;
	public String name;
	public String surname;
	public String mail;
	public String createDate;
	public Set<String> roles = new HashSet<>();;
	public Set<Long> proposalsId = new HashSet<>();;
	public Set<Long> carsId = new HashSet<>();;
	public Set<Long> tasksId = new HashSet<>();

	@Override
	public int compare(RestUserDto o1, RestUserDto o2) {		
		return (int)(o1.id - o2.id);
	}

	@Override
	public int compareTo(RestUserDto o) {
		return 0;
	}

}
