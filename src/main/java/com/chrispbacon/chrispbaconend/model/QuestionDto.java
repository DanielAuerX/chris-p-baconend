package com.chrispbacon.chrispbaconend.model;

import java.util.List;

public record QuestionDto (Question question, List<Answer> answers){
}
