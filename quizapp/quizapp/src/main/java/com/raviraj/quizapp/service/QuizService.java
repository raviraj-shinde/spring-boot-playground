package com.raviraj.quizapp.service;

import com.raviraj.quizapp.entity.Question;
import com.raviraj.quizapp.entity.Quiz;
import com.raviraj.quizapp.model.QuestionResponseDTO;
import com.raviraj.quizapp.repository.QuestionRepository;
import com.raviraj.quizapp.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepo;
    private final QuestionRepository questionRepo;


    public Quiz createQuiz(String category, int count, String title) {
        List<Question> questions = questionRepo.findRandomQuestionByCategory(category, count);
        Quiz quiz = new Quiz();
        quiz.setCategory(category);
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return quizRepo.save(quiz);
    }

    public List<Quiz> getAllQuiz() {
        return quizRepo.findAll();
    }

    public Quiz getQuiz(int id) {
        return quizRepo.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found by ID"));
    }

    public Integer giveMarksForQuiz(int quizId, List<QuestionResponseDTO> qResDTOList) {
        Quiz currQuiz = quizRepo.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not Found by Id"));
        List<Question> quizQuestions = currQuiz.getQuestions();

        Integer marks = 0;
        for (int i = 0; i < quizQuestions.size(); i++) {
           if ( quizQuestions.get(i).getCorrectAnswer().equals(qResDTOList.get(i).getCorrectAnswer())){
               marks += quizQuestions.get(i).getMarks();
           }
        }
        return marks;
    }
}
