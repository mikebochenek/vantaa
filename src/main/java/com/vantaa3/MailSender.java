package com.vantaa3;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.model.*;

public class MailSender {
	
	public static void test(String body) {

		List<String> toAddresses = new ArrayList<String>();
		toAddresses.add("michael_bochenek@yahoo.com");
		
		send("mikey320b@hotmail.com", toAddresses, "Test of Amazon SES", body != null ? body : "Hello - I hope you're having a good day.");
	}

	public static void send(String from, List<String> toAddresses, String subject, String bodyString) throws AmazonClientException {

		SendEmailRequest request = new SendEmailRequest().withSource(from);

		Destination dest = new Destination().withToAddresses(toAddresses);
		request.setDestination(dest);

		Content subjContent = new Content().withData(subject);
		Message msg = new Message().withSubject(subjContent);

		// Include a body in both text and HTML formats
		Content textContent = new Content()
				.withData(bodyString);
		Content htmlContent = new Content()
				.withData("<h1>" + bodyString + "</h1>");
		Body body = new Body().withHtml(htmlContent).withText(textContent);
		msg.setBody(body);

		request.setMessage(msg);

		// Set AWS access credentials
		AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(
				new BasicAWSCredentials("AKIAJRZOMGNQBT7LRLGA",
						"F8+2np+O3Ar74ZML8d6IwOwuzXVmdDFKJVICIZHJ"));

		// Call Amazon SES to send the message
		try {
			client.sendEmail(request);
		} catch (AmazonClientException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}