package com.chrispbacon.chrispbaconend.model;

import com.chrispbacon.chrispbaconend.model.answer.AnswerInputRequest;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "question_request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerInputRequest {
    @Id
    @GeneratedValue(generator = "qa_request_seq")
    private Long id;
    @Nullable
    private Long categoryRequestId;
    @Nullable
    private Long categoryId;
    private String question;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionAnswerRequest")
    private List<AnswerInputRequest> answerOptions;

    public QuestionAnswerInputRequest(@Nullable Long categoryRequestId, @Nullable Long categoryId, String question, List<AnswerInputRequest> answerOptions) {
        this.categoryRequestId = categoryRequestId;
        this.categoryId = categoryId;
        this.question = question;
        this.answerOptions = answerOptions;
    }
}
