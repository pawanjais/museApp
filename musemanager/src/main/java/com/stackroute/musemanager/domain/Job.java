package com.stackroute.musemanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="job",uniqueConstraints= @UniqueConstraint(columnNames = {"j_id", "user_id"}))
public class Job {

	public Job() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="r_id")
	private int rId;
	
	@Column(name="j_id")
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="date")
	private String publication_date;
	
	@Column(name="alert")
	private String alert;

	@Column(name="user_id")
	private String userId;

	@Override
	public String toString() {
		return "Job [rId=" + rId + ", id=" + id + ", name=" + name + ", publication_date=" + publication_date
				+ ", alert=" + alert + ", userId=" + userId + "]";
	}

	public Job(int rId, String id, String name, String publication_date, String alert, String userId) {
		super();
		this.rId = rId;
		this.id = id;
		this.name = name;
		this.publication_date = publication_date;
		this.alert = alert;
		this.userId = userId;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
	
	