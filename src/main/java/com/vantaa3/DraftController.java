/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import javax.servlet.http.HttpSession;

@ManagedBean(name = "draftController")
@SessionScoped
public class DraftController {
	private Logger logger = Logger.getLogger(this.getClass().getName()); 

	@EJB
	private DraftEJB draftEJB;
	
	private String comment;

	private Draft draft;
	
	public String save(AjaxBehaviorEvent event) {
		logger.finer("sessionId: " + getSessionId());
		
		if (draft == null) {
			draft = new Draft();
			logger.finer("creating new draft object... ");
		}
		
		if (draft.getText() == null || draft.getText().length() < comment.length()) {
			draft.setText(getComment());
			draft = draftEJB.update(draft);
			logger.info("saving draft comment: " + draft);
		} else {
			logger.finer("we don't save because draft hasn't really changed.");
		}
		
		return "write.faces";
	}

	private String getSessionId() {
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
		return session.getId();
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
