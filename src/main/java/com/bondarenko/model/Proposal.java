package com.bondarenko.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings ("serial")
public class Proposal implements Serializable {
	private Long id;
	private User user;
	private Status status;
	private Car car;
	private String description;
	private Timestamp createDate;

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Status getStatus() {
		return status;
	}

	public Car getCar() {
		return car;
	}

	public String getDescription() {
		return description;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
