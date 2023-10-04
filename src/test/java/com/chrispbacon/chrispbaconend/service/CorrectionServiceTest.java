package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.model.answer.AnswersDto;
import com.chrispbacon.chrispbaconend.model.CorrectionDto;
import com.chrispbacon.chrispbaconend.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CorrectionServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private CorrectionService correctionService;

    @Test
    void evaluateAnswers_AllCorrect() {
        UUID questionId = UUID.randomUUID();
        List<UUID> correctAnswers = Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        when(answerRepository.findCorrectAnswerIdsByQuestionId(questionId)).thenReturn(correctAnswers);
        List<AnswersDto> answersDtos = List.of(new AnswersDto(questionId, correctAnswers));

        List<CorrectionDto> corrections = correctionService.evaluateAnswers(answersDtos);

        assertEquals(1, corrections.size());
        assertTrue(corrections.get(0).isCorrect());
    }

    @Test
    void evaluateAnswers_AllIncorrect() {
        UUID questionId = UUID.randomUUID();
        List<UUID> correctAnswers = Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        when(answerRepository.findCorrectAnswerIdsByQuestionId(questionId)).thenReturn(correctAnswers);
        List<AnswersDto> answersDtos = List.of(new AnswersDto(questionId, Collections.emptyList()));

        List<CorrectionDto> corrections = correctionService.evaluateAnswers(answersDtos);

        assertEquals(1, corrections.size());
        assertFalse(corrections.get(0).isCorrect());
    }
}