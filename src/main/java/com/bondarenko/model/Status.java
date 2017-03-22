package com.bondarenko.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@SuppressWarnings ("serial")
@Entity
public class Status implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "STATUS_ID")
	private Long id;
	@Column (name = "NAME", nullable = false, unique = true, length = 100)
	private String name;
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "STATUS_ID", referencedColumnName = "STATUS_ID")
	private List<Proposal> proposals;
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "STATUS_ID", referencedColumnName = "STATUS_ID")
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
