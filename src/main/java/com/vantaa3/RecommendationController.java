/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "recommendationController")
@RequestScoped
public class RecommendationController {
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

	@EJB
	private RecommendationEJB recommendationEJB;

	private RankedRecommendationList list;
	
	public List<Recommendation> getAll() {
		return recommendationEJB.loadAllWithTags();
	}
	
	public List<String> getTags() {
		if (list == null) {
			list = recommendationEJB.initList();
		}
		counter++;
		return list.getTags(5, 100); //TODO
	}
	
	private static long counter = 0l;
	public long getCounter() { return counter; }
	

	/**
	 * trying to fix java.lang.IllegalStateException: Cannot create a session after the response has been committed
	 * 	http://stackoverflow.com/questions/7433575/cannot-create-a-session-after-the-response-has-been-committed
	 */
	@PostConstruct
	void initialiseSession() {
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
		
	public HashMap<String, List<String>> getTabularData() {
		return list.getTabularData(5,  10);
	}
	
	public List<String> getSentences(String tag) {
		long start = System.currentTimeMillis();
		//logger.warning(" getting sentences for tag: " + tag);
		if (list == null) {
			list = recommendationEJB.initList();
		}
		
		List<String> retVal = list.getRecommendationSentence(tag, 5, 100);
		
		if (retVal != null && retVal.size() > 0) {
			currentSentence = retVal.get(0);
		}
		
		if (System.currentTimeMillis() - start > 0) {
			logger.info("getSentences ms:" + (System.currentTimeMillis() - start));
		}
		
		return retVal;
	}
	
	private String currentSentence;
	public String getCurrentSentence() {
		return currentSentence;
	}
	
}
