package com.bondarenko.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings ("serial")
public class Task implements Serializable {
	private Long id;
	private Status status;
	private User user;
	private String description;
	private List<Proposal> proposals;
	private Timestamp createDate;

	public Long getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}

	public String getDescription() {
		return description;
	}

	public List<Proposal> getProposals() {
		return proposals;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
