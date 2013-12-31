/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing;

import com.google.gson.Gson;
import com.vantaa3.xing.api.pojos.ContactResponse;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

public class TestGSON {
	
	/**
	 * Shows a particular user’s profile. The data returned by this call will be checked 
	 * against and filtered on the basis of the privacy settings of the requested user.
	 * https://dev.xing.com/docs/get/users/:id
	 * @throws IOException
	 */
	@Test
	public void testXing0() throws IOException {
		readJsonFromFile("xing0.json");
	}
	
	/**
	 * https://dev.xing.com/docs/get/users/:user_id/contacts
	 */
	@Test
	public void testXing1() throws IOException {
		readJsonFromFile("xing1.json");
	}

	
	/**
	 * Returns all contact IDs of the current user (for my test user)
	 * https://dev.xing.com/docs/get/users/me/contact_ids
	 */
	@Test
	public void testXing2() throws IOException {
		readJsonFromFile("xing2.json");
	}

	@Test
	public void testXing3() throws IOException {
		readJsonFromFile("xing3.json");
	}

	/**
	 * Returns the requested user's contacts. The nested user data this call
	 * returns are the same as the get user details call. You can't request more
	 * than 100 contacts at once (see limit parameter), but you can perform
	 * several requests in parallel. If you execute this call with limit=0, it
	 * will tell you how many contacts the user has without returning any user data. 
	 * https://dev.xing.com/docs/get/users/:user_id/contacts
	 */
	@Test
	public void testXing4() throws IOException {
		Gson gson = new Gson();
		
		ContactResponse cr = gson.fromJson(readJsonFromFile("xing4.json"), ContactResponse.class);   
		
		assertEquals(47, cr.getContacts().getTotal());
	}
	
	
	/**
	 * https://sites.google.com/site/gson/gson-user-guide#TOC-Primitives-Examples
	 */
	@Test
	public void trivialTest() {
		Gson gson = new Gson();
		gson.toJson(1);            // ==> prints 1
		gson.toJson("abcd");       // ==> prints "abcd"
		gson.toJson(new Long(10)); // ==> prints 10
		int[] values = { 1 };
		gson.toJson(values);       // ==> prints [1]
	}
	
	private String readJsonFromFile(String filename) throws IOException {
		InputStream input = getResourceAsStream(filename);

		// http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
		java.util.Scanner s = new java.util.Scanner(input).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	
	/**
	 * thanks once again stackoverflow 
	 * http://stackoverflow.com/questions/2244203/cleanest-way-to-get-a-file-in-a-junit-test-case-from-maven?rq=1
	 */
	private URL getResourceAsURL(String resource) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource(resource);
	}

	private InputStream getResourceAsStream(String resource)
			throws IOException {
		ClassLoader cl = getClass().getClassLoader();
		InputStream in = cl.getResourceAsStream(resource);

		if (in == null)
			throw new IOException("resource \"" + resource + "\" not found");

		return in;
	}
}
