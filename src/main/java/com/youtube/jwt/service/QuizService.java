package com.youtube.jwt.service;

import com.youtube.jwt.dao.AnswerRepository;
import com.youtube.jwt.dao.QuizRepository;
import com.youtube.jwt.entity.Answer;
import com.youtube.jwt.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId).orElse(null);
    }

    public int calculateScore() {
        List<Quiz> quizzes = quizRepository.findAll();
        int correctCount = 0;

        for (Quiz quiz : quizzes) {
            String responseState = quiz.getResponseState();

            if ("Correct! Well done.".equals(responseState)) {
                correctCount++;
            }
        }

        System.out.println("Correct Count: " + correctCount);

        return correctCount;
    }

    public Quiz testQuiz(Long quizId, String userAnswer) {
        Quiz quiz = getQuizById(quizId);

        if (quiz != null) {
            String correctAnswer = quiz.getCorrectAnswer();

            if (userAnswer.equals(correctAnswer)) {
                quiz.setResponseState("Correct! Well done.");
            } else {
                quiz.setResponseState("Incorrect. Try again.");
            }

            // Ajoutez la réponse à la base de données
            Answer answer = new Answer();
            answer.setQuiz(quiz);
            answer.setUserAnswer(userAnswer);
            answerRepository.save(answer);
        }

        return quiz;
    }
    @Transactional
    public boolean deleteQuiz(Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);

        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            answerRepository.deleteByQuiz(quiz);
            quizRepository.delete(quiz);
            return true;
        } else {
            return false;
        }
    }

    public Quiz updateQuiz(Long quizId, Quiz updatedQuiz) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);

        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            quiz.setQuestion(updatedQuiz.getQuestion());
            quiz.setPropositions(updatedQuiz.getPropositions());
            quiz.setCorrectAnswer(updatedQuiz.getCorrectAnswer());
            return quizRepository.save(quiz);
        } else {
            return null;
        }
    }
    @Transactional
    public void deleteAllQuizzes() {
        answerRepository.deleteAll();
        quizRepository.deleteAll();
    }

    // Autres méthodes selon vos besoins
}