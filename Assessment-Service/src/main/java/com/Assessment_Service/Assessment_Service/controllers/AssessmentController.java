package com.Assessment_Service.Assessment_Service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Assessment_Service.Assessment_Service.dtos.AssessmentDetailsDto;
import com.Assessment_Service.Assessment_Service.dtos.AssessmentRequest;
import com.Assessment_Service.Assessment_Service.dtos.AssessmentResponse;
import com.Assessment_Service.Assessment_Service.dtos.UserTopicAverageDto;
import com.Assessment_Service.Assessment_Service.models.Dao.Services.AssessmentServices;

@RestController
//@CrossOrigin(origins = "*") 
@RequestMapping("/api/assess")
public class AssessmentController {
	@Autowired
	AssessmentServices assessServices;
	
		
	@PostMapping("/start")
	public ResponseEntity<Map<String, Object>> insertAssessment(@RequestBody AssessmentRequest ass) {
	    int assessmentId = assessServices.insertAssessment(ass);
	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "Assessment saved successfully");
	    response.put("assessmentId", assessmentId);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getScores/{assessmentId}")
	public ResponseEntity<Map<String, Object>> getScores(@PathVariable("assessmentId") int assessmentId) {
	    String score = assessServices.getScores(assessmentId);
	    Map<String, Object> response = new HashMap<>();
	    response.put("assessmentId", assessmentId);
	    response.put("score", score);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getStats/{assessmentId}")
	public ResponseEntity<Map<String, Object>> getStats(@PathVariable("assessmentId") int assessmentId) {
	    String stats = assessServices.getStats(assessmentId);
	    Map<String, Object> response = new HashMap<>();
	    response.put("assessmentId", assessmentId);
	    response.put("statistics", stats);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/details/{assessmentId}")
	public ResponseEntity<Object> getQuestions(@PathVariable("assessmentId") int assessmentId){
		List<AssessmentDetailsDto> list = assessServices.getAssessmentDetailsById(assessmentId);
		
		if (list.size() == 0)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No questions found");
		else
			return ResponseEntity.of(Optional.of(list));
	}
	
	@GetMapping("/usertopicaverages")
	public List<UserTopicAverageDto> getUserTopicAverages(@RequestParam("email") String email) {
	    return assessServices.getUserTopicAveragesForUser(email);
	}

	
}
		

	
	


