package com.raviraj.quizapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String qText;
    private String category;
        private String level; //easy-medium-hard
    private Integer marks;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    @Column(nullable = false)
    private String correctAnswer;
}
