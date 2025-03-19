package com.Assessment_Service.Assessment_Service.dtos;

import java.time.LocalDate;
import java.util.List;

public class AssessmentResponse {
	
	private int assessmentId;
	private String email;
	private String technology;
	private LocalDate assessmentDate;
	private String questionLevel;
	private int noOfQuestions;
	private List<ScoresDto> scoresdto;
	public int getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public LocalDate getAssessmentDate() {
		return assessmentDate;
	}
	public void setAssessmentDate(LocalDate assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	public String getQuestionLevel() {
		return questionLevel;
	}
	public void setQuestionLevel(String questionLevel) {
		this.questionLevel = questionLevel;
	}
	public int getNoOfQuestions() {
		return noOfQuestions;
	}
	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	public List<ScoresDto> getScoresdto() {
		return scoresdto;
	}
	public void setScoresdto(List<ScoresDto> scoresdto) {
		this.scoresdto = scoresdto;
	}
	public AssessmentResponse(int assessmentId, String email, String technology, LocalDate assessmentDate,
			String questionLevel, int noOfQuestions, List<ScoresDto> scoresdto) {
		super();
		this.assessmentId = assessmentId;
		this.email = email;
		this.technology = technology;
		this.assessmentDate = assessmentDate;
		this.questionLevel = questionLevel;
		this.noOfQuestions = noOfQuestions;
		this.scoresdto = scoresdto;
	}
	public AssessmentResponse() {
		super();
	}
}
