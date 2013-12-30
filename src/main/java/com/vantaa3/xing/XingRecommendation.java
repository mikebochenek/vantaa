/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class XingRecommendation {

	@Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
 
	@Lob
    private String text;
    
	private String owner;

	private String subject;
	
    public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	
	private String trim(String t) {
		final int max = 40;
		if (t == null || t.length() <= max) {
			return t;
		} else {
			return t.substring(0, max) + "...";
		}
	}

	@Override
	public String toString() {
		return "XingRecommendation [id=" + id + ", createDate=" + createDate
				+ ", text=" + trim(text) + ", owner=" + owner + ", subject="
				+ subject + "]";
	}
}