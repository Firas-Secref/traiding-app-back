package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Quiz;
import com.youtube.jwt.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuiz);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }
    @GetMapping("/test/{quizId}/{userAnswer}")
    public ResponseEntity<String> testQuiz(@PathVariable Long quizId, @PathVariable String userAnswer) {
        Quiz quiz = quizService.testQuiz(quizId, userAnswer);

        if (quiz != null) {
            return ResponseEntity.ok(quiz.getResponseState());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found with ID: " + quizId);
        }
    }

    @GetMapping("/score")
    public ResponseEntity<String> calculateScore() {
        int score = quizService.calculateScore();
        return ResponseEntity.ok("Score: " + score);
    }
    @DeleteMapping("/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) {
        boolean deleted = quizService.deleteQuiz(quizId);

        if (deleted) {
            return ResponseEntity.ok("Quiz deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found with ID: " + quizId);
        }
    }

    // Mettre à jour un Quiz
    @PutMapping("/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long quizId, @RequestBody Quiz updatedQuiz) {
        Quiz updated = quizService.updateQuiz(quizId, updatedQuiz);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllQuizzes() {
        quizService.deleteAllQuizzes();
        return ResponseEntity.ok("All quizzes deleted successfully.");
    }
}
