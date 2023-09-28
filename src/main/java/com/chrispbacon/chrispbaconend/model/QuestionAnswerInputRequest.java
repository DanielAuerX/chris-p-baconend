package com.chrispbacon.chrispbaconend.model;

import com.chrispbacon.chrispbaconend.model.answer.AnswerInputRequest;

import java.util.List;


public record QuestionAnswerInputRequest(Long categoryId, String question, List<AnswerInputRequest> answerOptions) {

}
