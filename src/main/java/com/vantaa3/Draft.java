/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Draft {

	@Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
 
	@Lob
    private String text;
    
	private String owner;
	
	
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

	@Override
	public String toString() {
		return "Draft [id=" + id + ", createDate=" + createDate + ", text="
				+ trim(text) + ", owner=" + owner + "]";
	}
	
	private String trim(String t) {
		final int max = 40;
		if (t == null || t.length() <= max) {
			return t;
		} else {
			return t.substring(0, max) + "...";
		}
	}
}