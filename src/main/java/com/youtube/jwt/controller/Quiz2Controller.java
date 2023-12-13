package com.youtube.jwt.controller;

import com.youtube.jwt.dto.QuizDto;
import com.youtube.jwt.entity.Quiz2;
import com.youtube.jwt.service.Quiz2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Quiz2Controller {

    private Quiz2Service quiz2Service;

    @Autowired
    public Quiz2Controller(Quiz2Service quiz2Service) {
        this.quiz2Service = quiz2Service;
    }

    @PostMapping("/quiz/new/{courseId}")
    public QuizDto addNewQuiz(@RequestBody Quiz2 quiz, @PathVariable int courseId){
        return this.quiz2Service.addNewQuiz(quiz, courseId);
    }

}
