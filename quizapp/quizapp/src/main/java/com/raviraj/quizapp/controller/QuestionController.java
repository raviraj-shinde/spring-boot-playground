package com.raviraj.quizapp.controller;

import com.raviraj.quizapp.entity.Question;
import com.raviraj.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService service;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> giveAllQuestions(){
        return ResponseEntity.ok(service.giveAllQuestions());
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addQuestion(question));
    }

    @GetMapping("/filter/{category}")
    public ResponseEntity<List<Question>> FilterQuestionsBasedOnCategory(@PathVariable String category){
        List<Question> questions = service.FilterQuestionsBasedOnCategory(category);
        return (questions.isEmpty()) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList()) : ResponseEntity.ok(questions);
    }

}
