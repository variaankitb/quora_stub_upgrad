package org.upgrad.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4173245873193222104L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "content")
	private String content;

	@Column(name = "date")
	private Date date;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "answer_id")
	private long answerId;

	@Column(name = "modified_on")
	private Date modifiedOn;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Long id, String content, Date date, long userId, long answerId, Date modifiedOn) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
		this.userId = userId;
		this.answerId = answerId;
		this.modifiedOn = modifiedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
