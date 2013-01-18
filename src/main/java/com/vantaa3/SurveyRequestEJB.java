package com.vantaa3;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SurveyRequestEJB {
	
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

    @PersistenceContext(unitName = "chapter10PU")
    private EntityManager em;

    public SurveyRequest createSurveyRequest(SurveyRequest request) {
		request.setCreateDate(new Date());

    	em.persist(request);
    	return request;
    }

    public SurveyRequest load(long id, String key) {
    	SurveyRequest r = em.find(SurveyRequest.class, id);

    	//logger.warning("loading  id:" + id + "  key:" + key +  " r.getId(): " + (r==null ? "null" : r.getId()));

    	if (r != null && key != null) {
    		for (SurveyResponse response : r.getSurveyResponses()) {
    			String generatedKey = SimpleHash.generate(response.getEmail());

    			//logger.warning(" generatedKey: " + generatedKey);
    			
            	if (key.equals(generatedKey)) { // && response.getSubmitDate() == null) {
            		return r;
            	}
    		}
    		logger.warning("load() we shouldn't get here: " + r.getSurveyResponses().size());
    	} else {
    		logger.warning("em.find failed to return r: " + (r == null) +  " key: " + key);
    	}
    	
    	return null;
    }

}
