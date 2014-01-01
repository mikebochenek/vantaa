/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class XingContact {

	@Id
    @GeneratedValue
    private Long id;

	private String xingId;
	private String displayName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getXingId() {
		return xingId;
	}
	public void setXingId(String xingId) {
		this.xingId = xingId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
