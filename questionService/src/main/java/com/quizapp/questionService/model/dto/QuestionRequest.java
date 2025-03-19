package com.quizapp.questionService.model.dto;



public class QuestionRequest {
	
	private String technology;
	private String questionLevel;
	private int noOfQuestions;
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
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
	public QuestionRequest(String technology, String questionLevel, int noOfQuestions) {
		super();
		this.technology = technology;
		this.questionLevel = questionLevel;
		this.noOfQuestions = noOfQuestions;
	}
	
	
	
	
}
