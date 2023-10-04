package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.LearningField;
import com.chrispbacon.chrispbaconend.model.answer.Answer;
import com.chrispbacon.chrispbaconend.model.category.Category;
import com.chrispbacon.chrispbaconend.model.question.Question;
import com.chrispbacon.chrispbaconend.model.question.QuestionDto;
import com.chrispbacon.chrispbaconend.repository.AnswerRepository;
import com.chrispbacon.chrispbaconend.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;


    @Test
    void getQuestionsWithAnswers_ValidCategory() {
        long categoryId = 1L;
        Question question1 = new Question(UUID.randomUUID(), new LearningField(), new Category(), "Question 1");
        Answer answer1 = new Answer(UUID.randomUUID(), question1, "Wrong answer", false);
        Answer answer2 = new Answer(UUID.randomUUID(), question1, "Correct answer", true);
        Question question2 = new Question(UUID.randomUUID(), new LearningField(), new Category(), "Question 2");
        Answer answer3 = new Answer(UUID.randomUUID(), question2, "Wrong answer", false);
        Answer answer4 = new Answer(UUID.randomUUID(), question2, "Correct answer", true);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        when(questionRepository.findAllByCategoryId(categoryId)).thenReturn(questions);
        when(answerRepository.findAnswersByQuestionId(question1.getId())).thenReturn(List.of(answer1, answer2));
        when(answerRepository.findAnswersByQuestionId(question2.getId())).thenReturn(List.of(answer3, answer4));
        List<QuestionDto> expectedQuestionDtos = new ArrayList<>();
        expectedQuestionDtos.add(new QuestionDto(question1, List.of(answer1, answer2)));
        expectedQuestionDtos.add(new QuestionDto(question2, List.of(answer3, answer4)));

        List<QuestionDto> result = questionService.getQuestionsWithAnswers(categoryId);

        assertEquals(expectedQuestionDtos.size(), result.size());
        assertEquals(expectedQuestionDtos, result);
    }

    @Test
    void getQuestionsWithAnswers_NoQuestionsFound() {
        long categoryId = 1L;
        when(questionRepository.findAllByCategoryId(categoryId)).thenReturn(Collections.emptyList());

        assertThrows(IllegalInputException.class, () -> questionService.getQuestionsWithAnswers(categoryId));
    }

}