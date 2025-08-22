package com.raviraj.quizapp.service;

import com.raviraj.quizapp.entity.Question;
import com.raviraj.quizapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository repository;

    public List<Question> giveAllQuestions() {
        return repository.findAll();
    }

    public Question addQuestion(Question question) {
        return repository.save(question);
    }

    public List<Question> FilterQuestionsBasedOnCategory(String category) {
        return repository.findQuestionsByCategory(category);
    }
}
