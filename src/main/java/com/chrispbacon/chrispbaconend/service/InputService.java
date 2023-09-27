package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.QuestionAnswerInputRequest;
import com.chrispbacon.chrispbaconend.model.answer.AnswerInputRequest;
import com.chrispbacon.chrispbaconend.model.category.Category;
import com.chrispbacon.chrispbaconend.model.category.CategoryInputRequest;
import com.chrispbacon.chrispbaconend.repository.AnswerRequestRepository;
import com.chrispbacon.chrispbaconend.repository.CategoryRequestRepository;
import com.chrispbacon.chrispbaconend.repository.LearningFieldRepository;
import com.chrispbacon.chrispbaconend.repository.QuestionRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InputService {

    private final CategoryRequestRepository categoryRequestRepository;
    private final QuestionRequestRepository questionRequestRepository;
    private final AnswerRequestRepository answerRequestRepository;
    private final LearningFieldRepository learningFieldRepository;

    public CategoryInputRequest saveCategoryRequest(CategoryInputRequest categoryInputRequest) {
        validateCategoryRequest(categoryInputRequest);
        return categoryRequestRepository.save(categoryInputRequest);
    }

    private void validateCategoryRequest(CategoryInputRequest categoryInputRequest) {
        learningFieldRepository.findById(categoryInputRequest.getLearningFieldId()).orElseThrow(() -> new IllegalInputException("Learning field does not exist."));
        if (categoryInputRequest.getCategoryName() == null || categoryInputRequest.getCategoryDescription() == null || categoryInputRequest.getCategoryText() == null) {
            throw new IllegalInputException("Provide a category name, description and text.");
        }
        if ( categoryInputRequest.getCategoryText().length() > Category.MAX_TEXT_LENGTH) {
            throw new IllegalInputException("Text can not contain more than " + Category.MAX_TEXT_LENGTH + " characters.");
        }
    }

    public QuestionAnswerInputRequest saveQARequest(QuestionAnswerInputRequest questionAnswerInputRequest) {
        validateQARequest(questionAnswerInputRequest);
        QuestionAnswerInputRequest savedRequest = questionRequestRepository.save(questionAnswerInputRequest);
        for (AnswerInputRequest answerOption : savedRequest.getAnswerOptions()) {
            answerOption.setQuestionAnswerRequest(savedRequest);
            answerRequestRepository.save(answerOption);
        }
        return savedRequest;
    }

    private void validateQARequest(QuestionAnswerInputRequest questionAnswerInputRequest) {
        if (questionAnswerInputRequest == null) {
            throw new IllegalInputException("Enter a question and answers!");
        }
        if (questionAnswerInputRequest.getCategoryRequestId() == null && questionAnswerInputRequest.getCategoryId() == null) {
            throw new IllegalInputException("Enter a category ID. Either an existing category ('categoryId') or a new category ('categoryRequestId').");
        }
        if (questionAnswerInputRequest.getQuestion() == null || questionAnswerInputRequest.getQuestion().length() < 5) {
            throw new IllegalInputException("Enter a valid question.");
        }
        if (questionAnswerInputRequest.getAnswerOptions() == null
            || questionAnswerInputRequest.getAnswerOptions().size() < 3
            || questionAnswerInputRequest.getAnswerOptions().stream().noneMatch(AnswerInputRequest::isCorrect)) {
            throw new IllegalInputException("Enter at least three answer options. At least one of the answers has to be correct.");
        }
    }

}
