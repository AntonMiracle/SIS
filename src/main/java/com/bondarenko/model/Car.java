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

@SuppressWarnings ("serial")
@Entity
public class Car implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "CAR_ID")
	private Long id;
	@ManyToOne (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "USER_ID")
	@JsonBackReference
	private User user;
	@Column (name = "NUMBER", nullable = false, unique = true, length = 100)
	private String number;
	@Column (name = "MODEL", nullable = false, length = 100)
	private String model;
	@Column (name = "MARK", nullable = false, length = 100)
	private String mark;
	@Column (name = "DESCRIPTION", nullable = false, length = 100)
	private String description;
	@Column (name = "CREATE_DATE")
	private Timestamp createDate;
	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn (name = "CAR_ID", referencedColumnName = "CAR_ID")
	@JsonBackReference
	private Set<Proposal> proposals = new HashSet<>();

	@Override
	public String toString() {
		return "Car [id=" + id + ", user=" + user + ", number=" + number + ", model=" + model + ", mark=" + mark
				+ ", description=" + description + ", createDate=" + createDate + ", proposals=" + proposals + "]";
	}

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

	public Set<Proposal> getProposals() {
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

	public void setProposals(Set<Proposal> proposals) {
		this.proposals = proposals;
	}

}
