package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.answer.Answer;
import com.chrispbacon.chrispbaconend.model.question.Question;
import com.chrispbacon.chrispbaconend.model.question.QuestionDto;
import com.chrispbacon.chrispbaconend.repository.AnswerRepository;
import com.chrispbacon.chrispbaconend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class QuestionService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public QuestionDto getQuestionWithAnswers(Long category_id) {
        Question randomQuestion = questionRepository.findRandomQuestionByCategoryId(category_id);
        if (randomQuestion == null) {
            throw new IllegalInputException("There are no questions linked to category id " + category_id + "! Please get your ducks in a row before you execute the next request.");
        }
        List<Answer> answers = answerRepository.findAnswersByQuestionId(randomQuestion.getId());
        return new QuestionDto(randomQuestion, answers);
    }

    public List<QuestionDto> getQuestionsWithAnswers(Long category_id) {
        List<Question> questions = getQuestions(category_id);
        return getAnswers(questions);
    }

    private List<QuestionDto> getAnswers(List<Question> questions) {
        List<QuestionDto> questionsWithAnswers = new ArrayList<>(questions.size());
        for (Question question : questions) {
            List<Answer> answers = answerRepository.findAnswersByQuestionId(question.getId());
            if (answers == null || answers.isEmpty()) {
                log.warn("No answers have been found for question " + question.getId() +". The question is being skipped.");
                continue;
            }
            Collections.shuffle(answers);
            questionsWithAnswers.add(new QuestionDto(question, answers));
        }
        return questionsWithAnswers;
    }

    private List<Question> getQuestions(Long category_id) {
        List<Question> questions = questionRepository.findAllByCategoryId(category_id);
        if (questions == null || questions.isEmpty()) {
            throw new IllegalInputException("There are no questions linked to category id " + category_id + "! Please get your ducks in a row before you execute the next request.");
        }
        Collections.shuffle(questions);
        return questions;
    }
}
