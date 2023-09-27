package com.chrispbacon.chrispbaconend.model.answer;

import com.chrispbacon.chrispbaconend.model.QuestionAnswerInputRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "answer_request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerInputRequest {
    @Id
    @GeneratedValue(generator = "answer_seq")
    private Long id;
    private String answer;
    private boolean correct;
    @ManyToOne
    @JoinColumn(name = "question_answer_request_id")
    private QuestionAnswerInputRequest questionAnswerRequest;

    public AnswerInputRequest(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public void setQuestionAnswerRequest(QuestionAnswerInputRequest questionAnswerRequest) {
        this.questionAnswerRequest = questionAnswerRequest;
    }
}
