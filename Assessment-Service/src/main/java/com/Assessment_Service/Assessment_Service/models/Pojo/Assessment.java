package com.Assessment_Service.Assessment_Service.models.Pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assessment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int assessemntId;
	private String email;
	private String technology;
	private LocalDate assessmentDate;
	private String questionLevel;
	private int noOfQuestions;
	public int getAssessemntId() {
		return assessemntId;
	}
	public void setAssessemntId(int assessemntId) {
		this.assessemntId = assessemntId;
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
	public Assessment(String email, String technology, LocalDate assessmentDate, String questionLevel,
			int noOfQuestions) {
		super();
		this.email = email;
		this.technology = technology;
		this.assessmentDate = assessmentDate;
		this.questionLevel = questionLevel;
		this.noOfQuestions = noOfQuestions;
	}
	public Assessment() {
		super();
	}
}