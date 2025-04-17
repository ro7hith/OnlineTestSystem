package com.Assessment_Service.Assessment_Service.models.Dao.ServiceImpl;

import java.util.List;

import com.Assessment_Service.Assessment_Service.dtos.AssessmentDetailsDto;
import com.Assessment_Service.Assessment_Service.dtos.AssessmentRequest;
import com.Assessment_Service.Assessment_Service.dtos.UserTopicAverageDto;

public interface IAssessment {

	public int insertAssessment(AssessmentRequest ass);
	public String getScores(int assessmentId);
	public String getStats(int assessmentId);
	public List<AssessmentDetailsDto> getAssessmentDetailsById(int assessmentId);
	public List<UserTopicAverageDto> getUserTopicAveragesForUser(String email);
}
