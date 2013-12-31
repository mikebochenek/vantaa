/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing.api.pojos;

public class Contacts {

	private int total;
	private User[] users;
	
	public Contacts() {
		// no-args constructor
	}

	public int getTotal() {
		return total;
	}

	public User[] getUsers() {
		return users;
	}
}
