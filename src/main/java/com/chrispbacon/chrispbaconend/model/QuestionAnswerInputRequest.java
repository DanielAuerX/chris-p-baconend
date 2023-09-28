package com.chrispbacon.chrispbaconend.model;

import com.chrispbacon.chrispbaconend.model.answer.AnswerInputRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class QuestionAnswerInputRequest {
    @Setter
    private Long categoryId;
    private final String question;
    private final List<AnswerInputRequest> answerOptions;

    public QuestionAnswerInputRequest(Long categoryId, String question, List<AnswerInputRequest> answerOptions) {
        this.categoryId = categoryId;
        this.question = question;
        this.answerOptions = answerOptions;
    }
}
