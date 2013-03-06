/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RecommendationEJB {
	
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

    @PersistenceContext(unitName = "chapter10PU")
    private EntityManager em;

    
    public Recommendation load(int id) {
    	Recommendation r = em.find(Recommendation.class, id);
    	logger.fine("loading id:" + id + " r.getId(): " + (r==null ? "null" : r.getId()));
    	return r;
    }
    
    @SuppressWarnings("unchecked")
	public List<Recommendation> loadAll() {
    	Query query = em.createQuery("select r from Recommendation r ");
    	return query.getResultList();
    }
    
    public Recommendation create(Recommendation r) {
    	em.persist(r);
    	return r;
    }
    
    public Recommendation update(Recommendation r) {
    	em.merge(r);
    	return r;
    }
    
    public void delete(Recommendation r) {
    	em.remove(em.find(Recommendation.class, r.getId()));
    }

}
