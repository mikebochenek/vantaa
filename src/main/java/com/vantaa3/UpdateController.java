/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "updateController")
@RequestScoped
public class UpdateController {
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

	@EJB
	private RecommendationEJB recommendationEJB;

	private Recommendation recommendation;

	public String update() {
		recommendationEJB.update(recommendation);
		logger.info("updated " + recommendation.toString());
		return "update.faces";
	}
	
	public String load() {
		int id = recommendation.getId();
		logger.info("calling load by id=" + id);
		recommendation = recommendationEJB.load(id);
		if (recommendation == null) {
			logger.info("hmmm... somehow didn't find by id=" + id);
		}
		return "update.faces";
	}
	
	public String delete() {
		logger.info("calling delete for id=" + recommendation.getId());
		recommendationEJB.delete(recommendation);
		return "update.faces";
	}
	
	public Recommendation getRecommendation() {
		if (recommendation == null) {
			recommendation = new Recommendation();
		}
		
		return recommendation;
	}

	public void setRecommendation(Recommendation recommendation) {
		this.recommendation = recommendation;
	}
}
