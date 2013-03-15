/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "statusController")
@RequestScoped
public class StatusController {
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

	@EJB
	private RecommendationEJB recommendationEJB;

	private RankedRecommendationList list;
	
	public List<Recommendation> getAll() {
		return recommendationEJB.loadAllWithTags();
	}
	
	public int getListSize() {
		return getAll().size();
	}
	
	public int getTotalListSize() {
		return recommendationEJB.loadAll().size();
	}

	public String getUptime() {
		return uptime.toGMTString();
	}
	private static Date uptime = Calendar.getInstance().getTime();
	
	/**
	 * trying to fix java.lang.IllegalStateException: Cannot create a session after the response has been committed
	 * 	http://stackoverflow.com/questions/7433575/cannot-create-a-session-after-the-response-has-been-committed
	 */
	@PostConstruct
	void initialiseSession() {
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
	
	public long getTimeInitList() {
		long start = System.currentTimeMillis();
		list = new RankedRecommendationList();
		
		List<Recommendation> all = getAll();
		for (Recommendation r : all) {
			String tags[] = r.getTags().split(",");
			for (String tag : tags) {
				list.add(tag, r.getSentence(), r.getRating());
			}
		}
		
		list.randomizeAndTrim();
		
		return (System.currentTimeMillis() - start);
	}
}
