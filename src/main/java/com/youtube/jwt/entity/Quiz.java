package com.youtube.jwt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String question;

    @ElementCollection
    @CollectionTable(name = "quiz_propositions", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "proposition")
    private List<String> propositions;

    @NotBlank
    private String correctAnswer;
    private String responseState;
    @OneToMany(mappedBy = "quiz")
    private List<Answer> answers;

    // Getters and setters
}
