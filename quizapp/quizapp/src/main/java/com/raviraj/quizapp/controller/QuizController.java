package com.raviraj.quizapp.controller;

import com.raviraj.quizapp.entity.Quiz;
import com.raviraj.quizapp.model.QuestionResponseDTO;
import com.raviraj.quizapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category,@RequestParam("quesCount") int count, @RequestParam String title){
        Quiz quiz = quizService.createQuiz(category, count, title);
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuiz(){
        return ResponseEntity.ok(quizService.getAllQuiz());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.FOUND).body(quizService.getQuiz(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Integer> giveMarksForQuiz(@PathVariable("id") int quizId, @RequestBody List<QuestionResponseDTO> qResDTOList){
        Integer marks = quizService.giveMarksForQuiz(quizId, qResDTOList);
        return ResponseEntity.ok(marks);
    }
}
