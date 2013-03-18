/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.List;
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
	
	private final String nextJSFPage = "update.faces";

	public String update() {
		recommendationEJB.update(getRecommendation());
		logger.info("updated " + recommendation.toString());
		return nextJSFPage;
	}
	
	public String getAllTags() {
		String s = "";
		List<String> tags = recommendationEJB.initList().getTags();
		for (int i = 0; i < tags.size(); i++) {
			s += tags.get(i) + "," + (i % 10 == 9 ? "\n" : "");
		}
		return s;
	}
	
	public String getSuggestedTags() {
		String suggestion = "";
		
		String sentence = getRecommendation().getSentence();
		sentence = sentence == null ? "" : sentence.toLowerCase();
		
		List<String> tags = recommendationEJB.initList().getTags();
		for (String tag : tags) {
			if (sentence.contains(tag)) {
				suggestion += (suggestion.length() == 0 ? "" : ",") + tag;
			}
		}
		
		return suggestion;
	}
	
	public String load() {
		int id = getRecommendation().getId();
		logger.info("calling load by id=" + id);
		recommendation = recommendationEJB.load(id);
		if (recommendation == null) {
			logger.info("hmmm... somehow didn't find by id=" + id);
		}
		return nextJSFPage;
	}
	
	public String next() {
		int id = recommendation.getId();
		logger.info("calling next! id=" + id);
		for (int i = 1; i < 10; i++) {
			recommendation = recommendationEJB.load(id + i);
			if (recommendation != null && recommendation.getId() == (id + i)) break;
		}
		return nextJSFPage;
	}
	
	public String previous() {
		int id = getRecommendation().getId();
		logger.info("calling previous! id=" + id);
		for (int i = 1; i < 10; i++) {
			recommendation = recommendationEJB.load(id - i);
			if (recommendation != null && recommendation.getId() == (id - i)) break;
		}
		return nextJSFPage;
	}
	
	public String delete() {
		logger.info("calling delete for id=" + getRecommendation().getId());
		recommendationEJB.delete(getRecommendation());
		return nextJSFPage;
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
