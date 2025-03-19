package com.Assessment_Service.Assessment_Service.dtos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AssessmentRequest {

	private String email;
	private String technology;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate assessmentDate;
	private String questionLevel;
	private int noOfQuestions;
	private List<ScoresDto> scoresdto;
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
	public AssessmentRequest(String email, String technology, LocalDate assessmentDate, String questionLevel,
			int noOfQuestions, List<ScoresDto> scoresdto) {
		super();
		this.email = email;
		this.technology = technology;
		this.assessmentDate = assessmentDate;
		this.questionLevel = questionLevel;
		this.noOfQuestions = noOfQuestions;
		this.scoresdto = scoresdto;
	}
	public AssessmentRequest() {
		super();
	}
}
