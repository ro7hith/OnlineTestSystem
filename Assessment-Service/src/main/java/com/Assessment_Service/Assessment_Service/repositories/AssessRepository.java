package com.Assessment_Service.Assessment_Service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Assessment_Service.Assessment_Service.models.Pojo.Assessment;

public interface AssessRepository extends JpaRepository<Assessment, Integer> {

	@Query(value = "SELECT A.assessment_id, QS.question, QS.optionA, QS.optionB, QS.optionC, QS.optionD, A.selected_option, QS.correct_answer " +
            "FROM ASSESS_SCORE A " +
            "INNER JOIN Question_Service QS ON A.question_id = QS.question_id " +
            "WHERE A.assessment_id = :assessmentId",
    nativeQuery = true)
		List<Object[]> findAssessmentDetailsById(@Param("assessmentId") int assessmentId);
}
