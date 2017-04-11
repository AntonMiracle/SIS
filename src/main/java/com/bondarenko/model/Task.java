package com.bondarenko.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings ("serial")
@Entity
public class Task implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "TASK_ID")
	private Long id;
	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "STATUS_ID")
	@JsonManagedReference
	private Status status;
	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID")
	@JsonIgnore
	private User user;
	@Column (name = "DESCRIPTION", nullable = false, length = 2048)
	private String description = new String();
	@Column (name = "CONCLUSION", nullable = false, length = 2048)
	private String conclusion= new String();
	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "PROPOSAL_ID")
	@JsonIgnore
	private Proposal proposal;
	@Column (name = "CREATE_DATE")
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

	public String getConclusion() {
		return conclusion;
	}

	public Proposal getProposal() {
		return proposal;
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

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", status=" + status.getName() + ", userId=" + user.getId() + ", description=" + description
				+ ", conclusion=" + conclusion + ", proposalId=" + proposal.getId() + ", createDate=" + createDate + "]";
	}

}
