package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.AnswersDto;
import com.chrispbacon.chrispbaconend.model.CorrectionDto;
import com.chrispbacon.chrispbaconend.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CorrectionService {

    private final AnswerRepository answerRepository;

    public CorrectionDto evaluateAnswers(AnswersDto answersDto) {
        List<UUID> correctAnswers = answerRepository.findCorrectAnswerIdsByQuestionId(answersDto.questionId());
        if (correctAnswers == null || correctAnswers.isEmpty()) {
            throw new IllegalInputException("The db did not return any correct answers for this question. Check if the question id " + answersDto.questionId() + " is correct and if the db contains answers for the question.");
        }
        List<UUID> answersByUser = answersDto.answers();
        boolean isCorrect = new HashSet<>(answersByUser).equals(new HashSet<>(correctAnswers)); //ignores order
        return new CorrectionDto(answersDto.questionId(), answersDto.answers(), correctAnswers, isCorrect);
    }
}
