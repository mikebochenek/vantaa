package com.vantaa3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SurveyRequest {

    @Id
    @GeneratedValue
    private Long id;

    private String owner;
    private String subject;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    private String settings;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SurveyResponse> surveyResponses;

	public List<SurveyResponse> getSurveyResponses() {
		if (surveyResponses == null) surveyResponses = new ArrayList<SurveyResponse>();
		
		return surveyResponses;
	}
	public void setSurveyResponses(List<SurveyResponse> surveyResponses) {
		this.surveyResponses = surveyResponses;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getSettings() {
		return settings;
	}
	public void setSettings(String settings) {
		this.settings = settings;
	}
    
}
