package com.Assessment_Service.Assessment_Service.dtos;

public class ScoresDto {

	
	private int questionId;
	private String selectedOption;
	private String answer;
	
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public ScoresDto( int questionId, String selectedOption, String answer) {
		super();
	
		this.questionId = questionId;
		this.selectedOption = selectedOption;
		this.answer = answer;
	}
	public ScoresDto() {
		super();
	}
}
