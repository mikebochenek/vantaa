package com.vantaa3;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SurveyResponseEJB {

	private Logger logger = Logger.getLogger(this.getClass().getName()); 

    @PersistenceContext(unitName = "chapter10PU")
    private EntityManager em;

    public SurveyResponse save(SurveyResponse response) {
    	//logger.warning("saving  id:" + response.getId() + " with comment:" + response.getComment());
    	
    	response.setSubmitDate(new Date());
    	em.merge(response);
    	return response;
    }
    
    public SurveyResponse load(long id, String key) {
    	//logger.warning("loading  id:" + id + "  key:" + key);
    	
    	SurveyResponse r = em.find(SurveyResponse.class, id);
    	
    	if (r != null && key != null) {
        	String generatedKey = SimpleHash.generate(r.getEmail());
        	
        	if (key.equals(generatedKey)) {
        		return r;
        	}
    	} else {
    		logger.warning("em.find failed to return r: " + (r == null) +  " key: " + key);
    	}
    	
    	return null;
    }
}
