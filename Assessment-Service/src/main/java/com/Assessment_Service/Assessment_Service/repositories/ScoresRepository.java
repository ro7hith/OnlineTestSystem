package com.Assessment_Service.Assessment_Service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Assessment_Service.Assessment_Service.models.Pojo.AssessScore;
import com.Assessment_Service.Assessment_Service.models.Pojo.Scores;

public interface ScoresRepository extends JpaRepository<AssessScore, Scores> {

	@Query("SELECT Q FROM AssessScore Q WHERE Q.scoreId.assessmentId=?1") 
	List<AssessScore> getAnswers(Integer assId);
	
	@Query("SELECT COUNT(a) FROM AssessScore a WHERE a.answer = a.selectedOption and a.scoreId.assessmentId=:assessmentId")
	int noOfCorrectOptions(@Param("assessmentId") int assessmentId);
	
	@Query("SELECT COUNT(a) FROM AssessScore a WHERE a.scoreId.assessmentId=?1")
	int noOfQuestions(int assessmentId);
	@Query("SELECT COUNT(a) FROM AssessScore a WHERE a.scoreId.assessmentId = :assessmentId AND a.answer <> a.selectedOption")
	int noOfIncorrectOptions(@Param("assessmentId") int assessmentId);
}
