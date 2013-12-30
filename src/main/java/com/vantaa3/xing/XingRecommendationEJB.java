/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class XingRecommendationEJB {
	
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

    @PersistenceContext(unitName = "chapter10PU")
    private EntityManager em;

    
    public XingRecommendation load(long id) {
    	XingRecommendation r = em.find(XingRecommendation.class, id);
    	logger.fine("loading id:" + id + " r.getId(): " + (r==null ? "null" : r.getId()));
    	return r;
    }

    public XingRecommendation create(XingRecommendation r) {
    	em.persist(r);
    	return r;
    }
    
    public XingRecommendation update(XingRecommendation r) {
    	em.merge(r);
    	return r;
    }
    
    public void delete(XingRecommendation r) {
    	em.remove(em.find(XingRecommendation.class, r.getId()));
    }

    @SuppressWarnings("unchecked")
	public List<XingRecommendation> loadByOwner(String owner) {
    	Query query = em.createQuery("select r from XingRecommendation r where r.owner = :p ");
    	query.setHint("org.hibernate.fetchSize", "100");
    	query.setHint("org.hibernate.readOnly", "true");
    	query.setParameter("p", owner);
    	return query.getResultList();
    }

    @SuppressWarnings("unchecked")
	public List<XingRecommendation> loadBySubject(String subject) {
    	Query query = em.createQuery("select r from XingRecommendation r where r.subject = :p ");
    	query.setHint("org.hibernate.fetchSize", "100");
    	query.setHint("org.hibernate.readOnly", "true");
    	query.setParameter("p", subject);
    	return query.getResultList();
    }

}
