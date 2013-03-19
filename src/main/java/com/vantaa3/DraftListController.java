/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "draftListController")
@RequestScoped
public class DraftListController {
	
	@EJB
	private DraftEJB draftEJB;

	public List<Draft> getDrafts() {
		return draftEJB.loadAll();
	}

}
