package com.quizapp.questionService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizapp.questionService.model.pogo.QuestionService;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionService, Long> {
    @Query("SELECT q FROM QuestionService q WHERE q.questionLevel = :level AND q.technology = :technology")
    List<QuestionService> findQuestionsByLevelAndTechnology(String level, String technology);
}
