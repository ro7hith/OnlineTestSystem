package com.Assessment_Service.Assessment_Service.models.Dao.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Assessment_Service.Assessment_Service.dtos.AssessmentDetailsDto;
import com.Assessment_Service.Assessment_Service.dtos.AssessmentRequest;
import com.Assessment_Service.Assessment_Service.models.Dao.ServiceImpl.IAssessment;
import com.Assessment_Service.Assessment_Service.models.Pojo.AssessScore;
import com.Assessment_Service.Assessment_Service.models.Pojo.Assessment;
import com.Assessment_Service.Assessment_Service.models.Pojo.Scores;
import com.Assessment_Service.Assessment_Service.repositories.AssessRepository;
import com.Assessment_Service.Assessment_Service.repositories.ScoresRepository;

@Service
public class AssessmentServices implements IAssessment {
	@Autowired
	AssessRepository assessrepository;
	@Autowired
	ScoresRepository scoresRepo;

	public int insertAssessment(AssessmentRequest ass) {
		// TODO Auto-generated method stub
		Assessment A = new Assessment(ass.getEmail(), ass.getTechnology(), ass.getAssessmentDate(), ass.getQuestionLevel(), ass.getNoOfQuestions());
		assessrepository.save(A);
		int maxId = assessrepository.findAll().stream().map(i -> i.getAssessemntId()).max(Integer::compareTo).orElseThrow(() -> new RuntimeException("list is empty"));
		AssessScore AS = new AssessScore();
		Scores S = null;
		System.out.println(ass.getScoresdto().size()+ "A size");
		for(int i = 0; i<ass.getScoresdto().size(); i++) {
			S = new Scores(maxId, ass.getScoresdto().get(i).getQuestionId());
			AS = new AssessScore(S, ass.getScoresdto().get(i).getSelectedOption(), ass.getScoresdto().get(i).getAnswer());
			System.out.println(AS.getScoreId().getAssessmentId()+ " " + AS.getScoreId().getQuestionId() + " " + AS.getSelectedOption());
			scoresRepo.save(AS);
		}
		return maxId;
	}

	public String getScores(int assessmentId) {
		// TODO Auto-generated method stub
		return scoresRepo.noOfCorrectOptions(assessmentId) + " Out of " + scoresRepo.noOfQuestions(assessmentId);
	}
	public String getStats(int assessmentId) {
		// TODO Auto-generated method stub
		return "Correct Answers"+scoresRepo.noOfCorrectOptions(assessmentId) +"\n" +
				"Questions"+ scoresRepo.noOfQuestions(assessmentId)+"\n"+
				"IncorrectAnswers"+scoresRepo.noOfIncorrectOptions(assessmentId);
	}
	
	public List<AssessmentDetailsDto> getAssessmentDetailsById(int assessmentId) {
        List<Object[]> results = assessrepository.findAssessmentDetailsById(assessmentId);
        List<AssessmentDetailsDto> assessmentDetails = new ArrayList<>();

        for (Object[] row : results) {
            AssessmentDetailsDto dto = new AssessmentDetailsDto();
            dto.setAssessmentId(((Number) row[0]).intValue());
            dto.setQuestion(row[1] != null ? row[1].toString() : null);
            dto.setOptionA(row[2] != null ? row[2].toString() : null);
            dto.setOptionB(row[3] != null ? row[3].toString() : null);
            dto.setOptionC(row[4] != null ? row[4].toString() : null);
            dto.setOptionD(row[5] != null ? row[5].toString() : null);
            dto.setSelectedOption(row[6] != null ? row[6].toString() : null);
            dto.setCorrectAnswer(row[7] != null ? row[7].toString() : null);
            assessmentDetails.add(dto);
        }
        return assessmentDetails;
    }

}
