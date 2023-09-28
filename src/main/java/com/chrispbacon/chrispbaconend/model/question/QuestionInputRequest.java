package com.chrispbacon.chrispbaconend.model.question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "question_request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInputRequest {
    @Id
    @GeneratedValue(generator = "qa_request_seq")
    private Long id;
    private Long categoryId;
    private String question;

    public QuestionInputRequest(Long categoryId, String question) {
        this.categoryId = categoryId;
        this.question = question;
    }
}
