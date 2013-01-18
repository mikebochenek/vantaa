package com.vantaa3;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "recommendationController")
@RequestScoped
public class RecommendationController {

	private static final int MAX_RECOMMENDATIONS = 59; //TODO for now, I need to change this every time..

	private Logger logger = Logger.getLogger(this.getClass().getName()); 

	@EJB
	private RecommendationEJB recommendationEJB;

	private RankedRecommendationList list;
	
	public List<Recommendation> getAll() {
		List<Recommendation> all = new ArrayList<Recommendation>();
		for (int i = 1; i < MAX_RECOMMENDATIONS; i++) {
			all.add(recommendationEJB.load(i));
		}
		
		return all;
	}
	
	public List<String> getTags() {
		if (list == null) {
			initList();
		}
		return list.getTags(5, 100); //TODO
	}
	
	public HashMap<String, List<String>> getTabularData() {
		return list.getTabularData(5,  10);
	}
	
	public List<String> getSentences(String tag) {
		long start = System.currentTimeMillis();
		//logger.warning(" getting sentences for tag: " + tag);
		if (list == null) {
			initList();
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
	
	private void initList() {
		long start = System.currentTimeMillis();
		list = new RankedRecommendationList();
		
		for (int i = 1; i < MAX_RECOMMENDATIONS; i++) {
			Recommendation r = recommendationEJB.load(i);
			String tags[] = r.getTags().split(",");
			for (String tag : tags) {
				list.add(tag, r.getSentence(), r.getRating());
			}
		}
		
		list.randomizeAndTrim();
		
		if (System.currentTimeMillis() - start > 0) {
			logger.info("initList ms:" + (System.currentTimeMillis() - start));
		}
	}
}
