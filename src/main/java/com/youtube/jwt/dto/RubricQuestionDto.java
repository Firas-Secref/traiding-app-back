package com.youtube.jwt.dto;

import com.youtube.jwt.entity.RubricQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class RubricQuestionDto {
    private Integer id;

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private String correctAnswer;
    private boolean questionState;

    public static RubricQuestionDto RubricQuestionEntityToDto(RubricQuestion rubricQuestion){
        if (rubricQuestion == null)return null;
        else return RubricQuestionDto.builder()
                .id(rubricQuestion.getId())
                .question(rubricQuestion.getQuestion())
                .answer1(rubricQuestion.getAnswer1())
                .answer2(rubricQuestion.getAnswer2())
                .answer3(rubricQuestion.getAnswer3())
                .answer4(rubricQuestion.getAnswer4())
                .correctAnswer(rubricQuestion.getCorrectAnswer())
                .questionState(rubricQuestion.isQuestionState()).build();
    }

    public static List<RubricQuestionDto> RubricQuestionEntityToDtos(List<RubricQuestion> rubricQuestions){
        return rubricQuestions.stream().map(x-> RubricQuestionEntityToDto(x)).collect(Collectors.toList());
    }

}
