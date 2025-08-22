package com.raviraj.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class QuestionResponseDTO {
    private Integer id;
    private String correctAnswer;
}
