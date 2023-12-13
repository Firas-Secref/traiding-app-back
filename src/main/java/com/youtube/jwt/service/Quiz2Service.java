package com.youtube.jwt.service;

import com.youtube.jwt.dao.CourseRepository;
import com.youtube.jwt.dao.Quiz2Repository;
import com.youtube.jwt.dto.QuizDto;
import com.youtube.jwt.entity.Course;
import com.youtube.jwt.entity.Quiz2;
import com.youtube.jwt.entity.RubricQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class Quiz2Service {

    private Quiz2Repository quiz2Repository;
    private CourseRepository courseRepository;
    private CourseService courseService;

    @Autowired
    public Quiz2Service(Quiz2Repository quiz2Repository, CourseRepository courseRepository, CourseService courseService) {
        this.quiz2Repository = quiz2Repository;
        this.courseRepository = courseRepository;
        this.courseService = courseService;
    }

    @Transactional
    public QuizDto addNewQuiz(Quiz2 quiz, int courseId){
        Quiz2 newQuiz = this.quiz2Repository.save(quiz);
        for (RubricQuestion rq: newQuiz.getRubricQuestions()) {
            rq.setQuiz(newQuiz);
        }
        this.courseService.associateQuizToCourse(newQuiz,courseId);
        return QuizDto.quizEntityToDto(newQuiz);
    }

}
