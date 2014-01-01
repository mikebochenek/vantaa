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
public class XingContactEJB {
	
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

    @PersistenceContext(unitName = "chapter10PU")
    private EntityManager em;

    
    public XingContact load(long id) {
    	XingContact x = em.find(XingContact.class, id);
    	logger.fine("loading id:" + id + " r.getId(): " + (x==null ? "null" : x.getId()));
    	return x;
    }

    public XingContact create(XingContact x) {
    	em.persist(x);
    	return x;
    }
    
    public XingContact update(XingContact x) {
    	em.merge(x);
    	return x;
    }
    
    public void delete(XingContact x) {
    	em.remove(em.find(XingContact.class, x.getId()));
    }

    @SuppressWarnings("unchecked")
	public List<XingContact> loadByXingId(String xingId) {
    	Query query = em.createQuery("select x from XingContact x where x.xingId = :p ");
    	query.setHint("org.hibernate.fetchSize", "100");
    	query.setHint("org.hibernate.readOnly", "true");
    	query.setParameter("p", xingId);
    	return query.getResultList();
    }


}
