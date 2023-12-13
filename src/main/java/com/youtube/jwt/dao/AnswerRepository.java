package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Answer;
import com.youtube.jwt.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    default void deleteByQuiz(Quiz quiz) {

    }
}
