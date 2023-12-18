package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.model.CorrectionDto;
import com.chrispbacon.chrispbaconend.model.answer.AnswersDto;
import com.chrispbacon.chrispbaconend.model.category.Category;
import com.chrispbacon.chrispbaconend.model.question.Question;
import com.chrispbacon.chrispbaconend.model.user.Role;
import com.chrispbacon.chrispbaconend.model.user.Student;
import com.chrispbacon.chrispbaconend.repository.AnswerRepository;
import com.chrispbacon.chrispbaconend.repository.QuestionRepository;
import com.chrispbacon.chrispbaconend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CorrectionServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private CorrectionService correctionService;

    @Test
    void evaluateAnswers_AllCorrect() {
        UUID questionId = UUID.randomUUID();
        List<UUID> correctAnswers = Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        Student user = new Student(UUID.randomUUID(), "email@web.de", "jCool", "123", "Julien", "Peter", Role.USER, Collections.emptyList());
        Category category = new Category(1L, null, null, null, null);
        Question question = new Question(questionId, null, category, null);
        when(answerRepository.findCorrectAnswerIdsByQuestionId(questionId)).thenReturn(correctAnswers);
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(user));
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
        when(userRepository.save(user)).thenReturn(user);
        List<AnswersDto> answersDtos = List.of(new AnswersDto(questionId, correctAnswers));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqQ29vbCIsImlhdCI6MTY5NTgwNjI4OSwiZXhwIjoxNjk1ODkyNjg5fQ.Hej6ujXE9jzoGjFoIM8AP-MGriUFAoPyAPdWr3Vtu7s";

        List<CorrectionDto> corrections = correctionService.evaluateAnswers(answersDtos, token);

        assertEquals(1, corrections.size());
        assertTrue(corrections.get(0).isCorrect());
        verify(userRepository).findByUsername(any(String.class));
        verify(questionRepository).findById(questionId);
        verify(userRepository).save(user);
    }

    @Test
    void evaluateAnswers_AllIncorrect() {
        UUID questionId = UUID.randomUUID();
        List<UUID> correctAnswers = Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        when(answerRepository.findCorrectAnswerIdsByQuestionId(questionId)).thenReturn(correctAnswers);
        List<AnswersDto> answersDtos = List.of(new AnswersDto(questionId, Collections.emptyList()));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqQ29vbCIsImlhdCI6MTY5NTgwNjI4OSwiZXhwIjoxNjk1ODkyNjg5fQ.Hej6ujXE9jzoGjFoIM8AP-MGriUFAoPyAPdWr3Vtu7s";

        List<CorrectionDto> corrections = correctionService.evaluateAnswers(answersDtos, token);

        assertEquals(1, corrections.size());
        assertFalse(corrections.get(0).isCorrect());
    }
}