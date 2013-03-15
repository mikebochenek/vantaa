/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "draftController")
@RequestScoped
public class DraftController {
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

	@EJB
	private DraftEJB draftEJB;
	
	private String comment;

	
	public String save() {
		Draft draft = new Draft();
		draft.setText(getComment());
		draftEJB.update(draft);
		
		logger.info("saving draft comment: " + draft);
		return "write.faces";
	}

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
