/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

public class MailHelper {

	static final String NAME_TOKEN = "$NAME";
	static final String COMPLETED_TOKEN = "$COMPLETED_BY";
	static final String TEXT_TOKEN = "$TEXT";
	static final String LINK_TOKEN = "$LINK";
	
	static final String header = "<p><div style=\"font-size: 28px;\">recommendme.ch</div>";
	static final String footer = "</p>";
	
	static final String requestSubject = "you have been ask for a recommendation";
	
	static final String responseCompletedSubject = "recommendation has been submitted by: " + NAME_TOKEN;
	
	static final String requestBody = "";
	
	static final String responseCompletedBody = "";
	
	public static String generateRequestSubject() {
		return requestSubject;
	}
	
	public static String generateRequestBody() {
		return header + requestBody + footer;
	}
	
	public static String generateResponseSubject(String replacement) {
		return responseCompletedSubject.replaceAll(NAME_TOKEN, replacement);
	}
	
	public static String generateResponseBody(String subject, String completedBy, String text) {
		String body = responseCompletedBody.replaceAll(NAME_TOKEN, subject);
		body = body.replaceAll(COMPLETED_TOKEN, completedBy);
		body = body.replaceAll(TEXT_TOKEN, text);
		return header + body + footer;
	}
}
