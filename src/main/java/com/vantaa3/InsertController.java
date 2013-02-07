/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "insertController")
@RequestScoped
public class InsertController {
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

	@EJB
	private RecommendationEJB recommendationEJB;

	private Recommendation recommendation;

	public String create() {
		recommendationEJB.create(recommendation);
		logger.info("created new " + recommendation.toString());
		recommendation = null; // is this how I ensure that I can reuse the same insert.xhtml?
		return "insert.faces";
	}
	
	public Recommendation getRecommendation() {
		if (recommendation == null) {
			recommendation = new Recommendation();
			recommendation.setRating(DEFAULT_RATING);
			recommendation.setId(recommendationEJB.loadAll().size() + 1); //TODO Recommendation.id should be @Generated!
		}
		
		return recommendation;
	}

	public void setRecommendation(Recommendation recommendation) {
		this.recommendation = recommendation;
	}
	
	private final int DEFAULT_RATING = 5;
}
