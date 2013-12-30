/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "xingRecommendationController")
@RequestScoped
public class XingRecommendationController {
	
	@EJB
	private XingRecommendationEJB xingRecEJB;

	private List<XingRecommendation> writtenRecommendations = null;
	private List<XingRecommendation> receivedRecommendations = null;
	
	//TODO this test should be removed...
	public XingRecommendation getOne() {
		XingRecommendation rec = //xingRecEJB.load(0l);
				xingRecEJB.loadByOwner("mike").get(0);
				// xingRecEJB.loadBySubject("valentin").get(0);
		if (rec == null) return new XingRecommendation();
		return rec;
	}

	public int getWrittenRecommendationsCount() {
		return getWrittenRecommendations().size();
	}
	
	public List<XingRecommendation> getWrittenRecommendations() {
		if (writtenRecommendations == null) {
			writtenRecommendations = xingRecEJB.loadByOwner("mike"); //TODO from session or something?
		}
		return writtenRecommendations;
	}
	
	public int getReceivedRecommendationsCount() {
		return getReceivedRecommendations().size();
	}

	public List<XingRecommendation> getReceivedRecommendations() {
		if (receivedRecommendations == null) {
			receivedRecommendations = xingRecEJB.loadBySubject("valentin"); //TODO from session or something?
		}
		return receivedRecommendations;
	}

}
