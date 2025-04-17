package com.Assessment_Service.Assessment_Service.dtos;

public class UserTopicAverageDto {

	 private String email;
	    private String technology;
	    private String questionLevel;
	    private double averageScore;
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
		public String getQuestionLevel() {
			return questionLevel;
		}
		public void setQuestionLevel(String questionLevel) {
			this.questionLevel = questionLevel;
		}
		public double getAverageScore() {
			return averageScore;
		}
		public void setAverageScore(double averageScore) {
			this.averageScore = averageScore;
		}
		public UserTopicAverageDto(String email, String technology, String questionLevel, double averageScore) {
			super();
			this.email = email;
			this.technology = technology;
			this.questionLevel = questionLevel;
			this.averageScore = averageScore;
		}
		public UserTopicAverageDto() {
			super();
		}

}
