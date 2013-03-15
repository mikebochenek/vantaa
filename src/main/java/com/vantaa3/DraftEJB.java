/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DraftEJB {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@PersistenceContext(unitName = "chapter10PU")
	private EntityManager em;

    public Draft update(Draft d) {
    	d.setCreateDate(new Date());
    	em.merge(d);
    	return d;
    }

}
