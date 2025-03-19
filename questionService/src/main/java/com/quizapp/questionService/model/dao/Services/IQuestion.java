package com.quizapp.questionService.model.dao.Services;

import java.util.List;

import com.quizapp.questionService.model.dto.QuestionRequest;
import com.quizapp.questionService.model.dto.QuestionResponse;

public interface IQuestion {
	List<QuestionResponse> getQuestions(QuestionRequest Q);
}
