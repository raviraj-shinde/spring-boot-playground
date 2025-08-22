package com.raviraj.quizapp.repository;

import com.raviraj.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findQuestionsByCategory(String category);

    @Query(value = "SELECT * FROM tbl_questions where category = :category ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int count);
}
