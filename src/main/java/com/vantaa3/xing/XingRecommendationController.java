/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "xingRecommendationController")
@RequestScoped
public class XingRecommendationController {
	private Logger logger = Logger.getLogger(this.getClass().getName()); 
	
	@EJB
	private XingRecommendationEJB xingRecEJB;

	private List<XingRecommendation> writtenRecommendations = null;
	private List<XingRecommendation> receivedRecommendations = null;

	public int getWrittenRecommendationsCount() {
		return getWrittenRecommendations().size();
	}
	
	public List<XingRecommendation> getWrittenRecommendations() {
		long startTS = System.currentTimeMillis();
		if (writtenRecommendations == null) {
			writtenRecommendations = xingRecEJB.loadByOwner("mike"); //TODO from session or something?
		}
		logger.info("writtenRecommendations loaded "
				+ writtenRecommendations.size() + " recs in "
				+ (System.currentTimeMillis() - startTS) + " ms");
		
		return writtenRecommendations;
	}
	
	public int getReceivedRecommendationsCount() {
		return getReceivedRecommendations().size();
	}

	public List<XingRecommendation> getReceivedRecommendations() {
		long startTS = System.currentTimeMillis();
		if (receivedRecommendations == null) {
			receivedRecommendations = xingRecEJB.loadBySubject("valentin"); //TODO from session or something?
		}
		logger.info("receivedRecommendations loaded "
				+ receivedRecommendations.size() + " recs in "
				+ (System.currentTimeMillis() - startTS) + " ms");
		
		return receivedRecommendations;
	}

}
