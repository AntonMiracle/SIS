package com.bondarenko.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings ("serial")
public class Car implements Serializable {
	private Long id;
	private User user;
	private String number;
	private String model;
	private String mark;
	private String description;
	private Timestamp createDate;
	private List<Proposal> proposals;

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getNumber() {
		return number;
	}

	public String getModel() {
		return model;
	}

	public String getMark() {
		return mark;
	}

	public String getDescription() {
		return description;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public List<Proposal> getProposals() {
		return proposals;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}

}
