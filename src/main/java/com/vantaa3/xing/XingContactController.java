/*
 * Copyright (c) 2013 Michael Bochenek 
 */
package com.vantaa3.xing;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "xingContactController")
@RequestScoped
public class XingContactController {
	private Logger logger = Logger.getLogger(this.getClass().getName()); 
	
	@EJB
	private XingContactEJB xingContactEJB;

	public XingContact getContact() {
		long startTS = System.currentTimeMillis();
		
		List<XingContact> list = xingContactEJB.loadByXingId("xxx"); //TODO remove hardcoded
		
		logger.info("xingContacts loaded " + list.size()  + " recs in "
				+ (System.currentTimeMillis() - startTS) + " ms");
		
		return list.size() > 0 ? list.get(0) : null;
	}

}
