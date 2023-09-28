package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.InputSetRequest;
import com.chrispbacon.chrispbaconend.model.QuestionAnswerInputRequest;
import com.chrispbacon.chrispbaconend.model.answer.AnswerInputRequest;
import com.chrispbacon.chrispbaconend.model.category.Category;
import com.chrispbacon.chrispbaconend.model.category.CategoryInputRequest;
import com.chrispbacon.chrispbaconend.model.question.QuestionInputRequest;
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

    public long saveCategoryRequest(InputSetRequest inputSetRequest) {
        CategoryInputRequest categoryInputRequest = new CategoryInputRequest(inputSetRequest.learningFieldId(), inputSetRequest.categoryName(), inputSetRequest.categoryDescription(), inputSetRequest.categoryText());
        validateRequest(inputSetRequest, categoryInputRequest);
        return saveRequest(inputSetRequest, categoryInputRequest);
    }

    private long saveRequest(InputSetRequest inputSetRequest, CategoryInputRequest categoryInputRequest) {
        CategoryInputRequest savedCategory = categoryRequestRepository.save(categoryInputRequest);
        for (QuestionAnswerInputRequest questionAnswerInputRequest : inputSetRequest.questions()) {
            QuestionInputRequest savedQuestion = saveQuestionRequest(questionAnswerInputRequest);
            saveAnswerRequests(questionAnswerInputRequest, savedQuestion);
        }
        return savedCategory.getId();
    }

    private void validateRequest(InputSetRequest inputSetRequest, CategoryInputRequest categoryInputRequest) {
        validateCategoryRequest(categoryInputRequest);
        for (QuestionAnswerInputRequest questionAnswerInputRequest : inputSetRequest.questions()) {
            validateQARequest(questionAnswerInputRequest, false);
        }
    }

    private void validateCategoryRequest(CategoryInputRequest categoryInputRequest) {
        learningFieldRepository.findById(categoryInputRequest.getLearningFieldId()).orElseThrow(() -> new IllegalInputException("Learning field does not exist."));
        if (categoryInputRequest.getCategoryName() == null || categoryInputRequest.getCategoryDescription() == null || categoryInputRequest.getCategoryText() == null) {
            throw new IllegalInputException("Provide a category name, description and text.");
        }
        if (categoryInputRequest.getCategoryText().length() > Category.MAX_TEXT_LENGTH) {
            throw new IllegalInputException("Text can not contain more than " + Category.MAX_TEXT_LENGTH + " characters.");
        }
    }

    public long saveQARequest(QuestionAnswerInputRequest questionAnswerInputRequest) {
        validateQARequest(questionAnswerInputRequest, true);
        QuestionInputRequest savedQuestion = saveQuestionRequest(questionAnswerInputRequest);
        saveAnswerRequests(questionAnswerInputRequest, savedQuestion);
        return savedQuestion.getId();
    }

    private void saveAnswerRequests(QuestionAnswerInputRequest questionAnswerInputRequest, QuestionInputRequest savedQuestion) {
        for (AnswerInputRequest answerOption : questionAnswerInputRequest.getAnswerOptions()) {
            answerOption.setQuestionAnswerRequest(savedQuestion);
            answerRequestRepository.save(answerOption);
        }
    }

    private QuestionInputRequest saveQuestionRequest(QuestionAnswerInputRequest questionAnswerInputRequest) {
        QuestionInputRequest questionInputRequest = new QuestionInputRequest(questionAnswerInputRequest.getCategoryId(), questionAnswerInputRequest.getQuestion());
        return questionRequestRepository.save(questionInputRequest);
    }

    private void validateQARequest(QuestionAnswerInputRequest questionAnswerInputRequest, boolean idNullCheck) {
        if (questionAnswerInputRequest == null) {
            throw new IllegalInputException("Enter a question and answers!");
        }
        if (idNullCheck && questionAnswerInputRequest.getCategoryId() == null) {
            throw new IllegalInputException("Enter a category ID.");
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
