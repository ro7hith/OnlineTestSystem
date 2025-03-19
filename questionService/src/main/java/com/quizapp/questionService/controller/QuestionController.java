package com.quizapp.questionService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.questionService.model.dao.ServiceImpl.QuestionDao;
import com.quizapp.questionService.model.dao.Services.IQuestion;
import com.quizapp.questionService.model.dto.QuestionRequest;
import com.quizapp.questionService.model.dto.QuestionResponse;
import com.quizapp.questionService.repositories.QuestionRepository;

@RestController
@RequestMapping("/api/questions")
//@CrossOrigin
public class QuestionController {
	
	@Autowired
	IQuestion questionDao;
	
	
	@RequestMapping("/")
	public String test() {
		return "Test";
	}
	
	@PostMapping("/getAll")
	public ResponseEntity<Object> getQuestions(@RequestBody QuestionRequest questionrequest){
		List<QuestionResponse> list = questionDao.getQuestions(questionrequest);
		
		if (list.size() == 0)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No questions found");
		else
			return ResponseEntity.of(Optional.of(list));
	}
	
	
	

}
