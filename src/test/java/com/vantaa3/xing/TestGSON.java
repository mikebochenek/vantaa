package com.vantaa3.xing;

import com.google.gson.Gson;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

public class TestGSON {
	
	@Test
	public void test() throws IOException {
		getResourceAsStream("xing1.json");
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
