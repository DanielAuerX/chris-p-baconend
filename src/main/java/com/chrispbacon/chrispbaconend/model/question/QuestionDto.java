package com.chrispbacon.chrispbaconend.model.question;

import com.chrispbacon.chrispbaconend.model.answer.Answer;

import java.util.List;

public record QuestionDto (Question question, List<Answer> answers){
}
