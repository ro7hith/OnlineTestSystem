package com.Assessment_Service.Assessment_Service.models.Pojo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AssessScore {

	@EmbeddedId
	private Scores scoreId;
	private String selectedOption;
	private String answer;
	public Scores getScoreId() {
		return scoreId;
	}
	public void setScoreId(Scores scoreId) {
		this.scoreId = scoreId;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getanswer() {
		return answer;
	}
	public void setanswer(String answer) {
		this.answer = answer;
	}
	public AssessScore(Scores scoreId, String selectedOption, String answer) {
		super();
		this.scoreId = scoreId;
		this.selectedOption = selectedOption;
		this.answer = answer;
	}
	public AssessScore() {
		super();
	}
	
}
