package com.quizapp.questionService.model.dao.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.questionService.model.dao.Services.IQuestion;
import com.quizapp.questionService.model.dto.QuestionRequest;
import com.quizapp.questionService.model.dto.QuestionResponse;
import com.quizapp.questionService.model.pogo.QuestionService;
import com.quizapp.questionService.repositories.QuestionRepository;


@Service
public class QuestionDao implements IQuestion{
	
	@Autowired
	QuestionRepository questionRepository;

	@Override
	public List<QuestionResponse> getQuestions(QuestionRequest Q) {

	    List<QuestionService> filteredQuestions = questionRepository.findQuestionsByLevelAndTechnology(
	            Q.getQuestionLevel(), Q.getTechnology());

	    System.out.println("Filtered Questions: " + filteredQuestions.size());

	    int noOfQuestions = Q.getNoOfQuestions();

	    if (filteredQuestions.isEmpty()) {
	        throw new RuntimeException("No questions available for the selected level and technology.");
	    }

	    if (noOfQuestions > filteredQuestions.size()) {
	        throw new RuntimeException("Requested number of questions exceeds available questions.");
	    }

	    List<QuestionService> selectedQuestions = new ArrayList<>();
	    Random random = new Random();

	    while (selectedQuestions.size() < noOfQuestions) {
	        int index = random.nextInt(filteredQuestions.size());
	        QuestionService selectedQuestion = filteredQuestions.get(index);
	        if (!selectedQuestions.contains(selectedQuestion)) {
	            selectedQuestions.add(selectedQuestion);
	        }
	    }

	    List<QuestionResponse> questionResponses = new ArrayList<>();
	    for (QuestionService question : selectedQuestions) {
	        questionResponses.add(mapToQuestionResponse(question));
	    }

	    return questionResponses;
	}

	
	private QuestionResponse mapToQuestionResponse(QuestionService Q) {
		return new QuestionResponse(
				Q.getQuestionId(), 
				Q.getQuestionLevel(),
				Q.getTechnology(),
				Q.getQuestion(),
				Q.getOptionA(),
				Q.getOptionB(),
				Q.getOptionC(),
				Q.getOptionD(),
				Q.getCorrectAnswer()
				);	
	}



	
}
