package com.quangoi.question_service.dao;

import com.quangoi.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM Question q " +
            "WHERE q.category=:category " +
            "ORDER BY RANDOM() LIMIT :numQ")
    List<Integer> findRandomQuestionsByCategory(String category, Integer numQ);

    @Query(value = "SELECT q.id FROM Question q " +
            "WHERE q.category=:category " +
            "ORDER BY RANDOM() LIMIT :numQ")
    List<Integer> findRandomQuestionsByCategory2();
}
