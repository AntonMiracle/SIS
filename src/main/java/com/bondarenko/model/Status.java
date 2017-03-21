package com.bondarenko.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings ("serial")
public class Status implements Serializable {
	private Long id;
	private String name;
	private List<Proposal> proposals;
	private List<Task> tasks;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Proposal> getProposals() {
		return proposals;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
