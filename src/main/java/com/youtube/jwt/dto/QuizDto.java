package com.youtube.jwt.dto;

import com.youtube.jwt.entity.Quiz2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class QuizDto {

    private Integer id;
    private List<RubricQuestionDto> rubricQuestions;

    public static QuizDto quizEntityToDto(Quiz2 quiz){
        if (quiz == null)return null;
        else return QuizDto.builder().id(quiz.getId())
                .rubricQuestions(RubricQuestionDto.RubricQuestionEntityToDtos(quiz.getRubricQuestions())).build();
    }
}
