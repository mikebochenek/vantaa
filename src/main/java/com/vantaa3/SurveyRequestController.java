package com.vantaa3;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "surveyRequestController")
@RequestScoped
public class SurveyRequestController {

	private Logger logger = Logger.getLogger(this.getClass().getName()); 
	
	@EJB
	private SurveyRequestEJB requestEJB;

	private String subject;
	private String owner;
	private String par1;
	private String par2;
	private String par3;
	private String par4;
	private String par5;
	private String par6;
	private String par7;
	private String par8;
	private String par9;
	private String par10;
	
	
	public String submit() {
		long start = System.currentTimeMillis();

		SurveyRequest request = new SurveyRequest();
		request.setSubject(subject);
		request.setOwner(owner);
		
		createParticipant(request, getPar1());
		createParticipant(request, getPar2());
		createParticipant(request, getPar3());
		createParticipant(request, getPar4());
		createParticipant(request, getPar5());
		createParticipant(request, getPar6());
		createParticipant(request, getPar7());
		createParticipant(request, getPar8());
		createParticipant(request, getPar9());
		createParticipant(request, getPar10());

		request = requestEJB.createSurveyRequest(request);
		
		if (System.currentTimeMillis() - start > 0) {
			logger.info("submit ms:" + (System.currentTimeMillis() - start));
		}
		return "surveyrequestcompleted.faces" + SimpleHash.generate(request.getOwner()) + "-" + request.getId();
	}
	
	
	private void createParticipant(SurveyRequest request, String participantInput) {
		
		if (participantInput != null && participantInput.trim().length() > 0) {
			SurveyResponse emptyResponse = new SurveyResponse();
			emptyResponse.setCreateDate(new Date());
			
			String email = participantInput; //TODO maybe try to smartly split out name from email
			
			emptyResponse.setEmail(email);
			emptyResponse.setRequestKey(SimpleHash.generate(email));
			request.getSurveyResponses().add(emptyResponse);
		}
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPar1() {
		return par1;
	}

	public void setPar1(String par1) {
		this.par1 = par1;
	}

	public String getPar2() {
		return par2;
	}

	public void setPar2(String par2) {
		this.par2 = par2;
	}

	public String getPar3() {
		return par3;
	}

	public void setPar3(String par3) {
		this.par3 = par3;
	}

	public String getPar4() {
		return par4;
	}

	public void setPar4(String par4) {
		this.par4 = par4;
	}

	public String getPar5() {
		return par5;
	}

	public void setPar5(String par5) {
		this.par5 = par5;
	}

	public String getPar6() {
		return par6;
	}

	public void setPar6(String par6) {
		this.par6 = par6;
	}

	public String getPar7() {
		return par7;
	}

	public void setPar7(String par7) {
		this.par7 = par7;
	}

	public String getPar8() {
		return par8;
	}

	public void setPar8(String par8) {
		this.par8 = par8;
	}

	public String getPar9() {
		return par9;
	}

	public void setPar9(String par9) {
		this.par9 = par9;
	}

	public String getPar10() {
		return par10;
	}

	public void setPar10(String par10) {
		this.par10 = par10;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
