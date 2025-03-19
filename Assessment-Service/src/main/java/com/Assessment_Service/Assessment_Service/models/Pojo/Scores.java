package com.Assessment_Service.Assessment_Service.models.Pojo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Embeddable
public class Scores implements Serializable {

	
	private int assessmentId;
	private int questionId;
	public int getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public Scores(int assessmentId, int questionId) {
		super();
		this.assessmentId = assessmentId;
		this.questionId = questionId;
	}
	public Scores() {
		super();
	}
}
