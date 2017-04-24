package com.bondarenko.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings ("serial")
@Entity
public class Proposal implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "PROPOSAL_ID")
	private Long id;
	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID")
	@JsonBackReference
	private User user;
	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "STATUS_ID")
	@JsonManagedReference
	private Status status;
	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "CAR_ID")
	@JsonManagedReference
	private Car car;
	@Column (name = "DESCRIPTION", nullable = false, length = 100)
	private String description;
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "PROPOSAL_ID", referencedColumnName = "PROPOSAL_ID")
	@JsonManagedReference
	private Set<Task> tasks = new HashSet<>();
	@Column (name = "CREATE_DATE")
	private Timestamp createDate;

	@Override
	public String toString() {
		return "Proposal [id=" + id + ", user=" + user + ", status=" + status + ", carId=" + car.getId()
				+ ", description=" + description + ", tasks=" + tasks + ", createDate=" + createDate + "]";
	}

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

	public Set<Task> getTasks() {
		return tasks;
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

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
