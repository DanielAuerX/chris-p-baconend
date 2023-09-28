package com.chrispbacon.chrispbaconend.model;

import java.util.List;

public record InputSetRequest(Long learningFieldId, String categoryName, String categoryDescription, String categoryText, List<QuestionAnswerInputRequest> questions) {
}
