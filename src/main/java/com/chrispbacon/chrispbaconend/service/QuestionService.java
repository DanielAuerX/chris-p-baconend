package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.Answer;
import com.chrispbacon.chrispbaconend.model.Question;
import com.chrispbacon.chrispbaconend.model.QuestionDto;
import com.chrispbacon.chrispbaconend.repository.AnswerRepository;
import com.chrispbacon.chrispbaconend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class QuestionService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public QuestionDto getQuestionWithAnswers(Long category_id) {
        Question randomQuestion = questionRepository.findRandomQuestionByCategoryId(category_id);
        if (randomQuestion == null) {
            throw new IllegalInputException("The category id " + category_id + " does not exist! Please get your ducks in a row before you execute the next request.");
        }
        List<Answer> answers = answerRepository.findAnswersByQuestionId(randomQuestion.getId());
        return new QuestionDto(randomQuestion, answers);
    }
}
