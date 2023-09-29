package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.answer.AnswersDto;
import com.chrispbacon.chrispbaconend.model.CorrectionDto;
import com.chrispbacon.chrispbaconend.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CorrectionService {

    private final AnswerRepository answerRepository;

    public List<CorrectionDto> evaluateAnswers(List<AnswersDto> answersDtos) {
        List<CorrectionDto> corrections = new ArrayList<>(answersDtos.size());
        for (AnswersDto answersDto : answersDtos) {
            List<UUID> correctAnswers = getCorrectAnswerIds(answersDto);
            List<UUID> answersByUser = answersDto.answers();
            boolean isCorrect = new HashSet<>(answersByUser).equals(new HashSet<>(correctAnswers)); //ignores order
            corrections.add(new CorrectionDto(answersDto.questionId(), answersDto.answers(), correctAnswers, isCorrect));
        }
        return corrections;
    }

    private List<UUID> getCorrectAnswerIds(AnswersDto answersDto) {
        List<UUID> correctAnswers = answerRepository.findCorrectAnswerIdsByQuestionId(answersDto.questionId());
        if (correctAnswers == null || correctAnswers.isEmpty()) {
            throw new IllegalInputException("The db did not return any correct answers for this question. Check if the question id " + answersDto.questionId() + " is correct and if the db contains answers for the question.");
        }
        return correctAnswers;
    }
}
