/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

@ManagedBean(name = "xingAPIController")
@RequestScoped
public class XingAPIController {
    private String apiKey       = "";
    private String apiSecret    = "";
    private String callUrl      = "";
	
    /* mix between
     * https://github.com/fernandezpablo85/scribe-java/wiki/getting-started
     * 
     * TODO class and managedBean name should be probably renamed
     */
    public void call() {
    	
        // Step One: Create the OAuthService object
        OAuthService service = new ServiceBuilder()
                .provider(XingApi.class)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build();
    	
        // Step Two: Get the request token
        Token requestToken = service.getRequestToken();

        // Step Three: Making the user validate your request token
        String authUrl = service.getAuthorizationUrl(requestToken);

        // Step Four: Get the access Token
        Verifier v = new Verifier("verifier you got from the user");
        Token accessToken = service.getAccessToken(requestToken, v); // the requestToken you had from step 2

        // Step Five: Sign request
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.twitter.com/1/account/verify_credentials.xml");
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println(response.getBody());

    }

}
