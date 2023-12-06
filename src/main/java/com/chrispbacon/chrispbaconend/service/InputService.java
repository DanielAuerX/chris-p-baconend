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
import com.chrispbacon.chrispbaconend.util.Guard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InputService {

    private final CategoryRequestRepository categoryRequestRepository;
    private final QuestionRequestRepository questionRequestRepository;
    private final AnswerRequestRepository answerRequestRepository;
    private final LearningFieldRepository learningFieldRepository;

    public long handleCategoryRequest(InputSetRequest inputSetRequest) {
        CategoryInputRequest categoryInputRequest = generateCategoryInputRequest(inputSetRequest);
        validateRequest(inputSetRequest, categoryInputRequest);
        return saveRequest(inputSetRequest, categoryInputRequest);
    }

    private CategoryInputRequest generateCategoryInputRequest(InputSetRequest inputSetRequest) {
        return new CategoryInputRequest(inputSetRequest.learningFieldId(),
                inputSetRequest.categoryName(),
                inputSetRequest.categoryDescription(),
                inputSetRequest.categoryText());
    }

    private long saveRequest(InputSetRequest inputSetRequest, CategoryInputRequest categoryInputRequest) {
        CategoryInputRequest savedCategoryRequest = categoryRequestRepository.save(categoryInputRequest);
        for (QuestionAnswerInputRequest questionAnswerInputRequest : inputSetRequest.questions()) {
            questionAnswerInputRequest.setCategoryId(savedCategoryRequest.getId());
            QuestionInputRequest savedQuestion = saveQuestionRequest(questionAnswerInputRequest);
            saveAnswerRequests(questionAnswerInputRequest, savedQuestion);
        }
        return savedCategoryRequest.getId();
    }

    private void validateRequest(InputSetRequest inputSetRequest, CategoryInputRequest categoryInputRequest) {
        validateCategory(categoryInputRequest);
        validateQuestions(inputSetRequest);
    }

    private void validateQuestions(InputSetRequest inputSetRequest) {
        if (inputSetRequest.questions().size() < 3 || inputSetRequest.questions().size() > 30) {
            throw new IllegalInputException("Provide at least three and a maximum of 30 questions (with answers).");
        }
        for (QuestionAnswerInputRequest questionAnswerInputRequest : inputSetRequest.questions()) {
            validateQARequest(questionAnswerInputRequest, false);
        }
    }

    private void validateCategory(CategoryInputRequest categoryInputRequest) {
        learningFieldRepository.findById(categoryInputRequest.getLearningFieldId()).orElseThrow(() -> new IllegalInputException("Learning field does not exist."));
        if (categoryInputRequest.getCategoryName() == null || categoryInputRequest.getCategoryDescription() == null || categoryInputRequest.getCategoryText() == null) {
            throw new IllegalInputException("Provide a category name, description and text.");
        }
        if (categoryInputRequest.getCategoryText().length() > Category.MAX_TEXT_LENGTH) {
            throw new IllegalInputException("Text can not contain more than " + Category.MAX_TEXT_LENGTH + " characters.");
        }
    }

    public long handleQARequest(QuestionAnswerInputRequest questionAnswerInputRequest) {
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
        Guard.againstNull(questionAnswerInputRequest, "Enter a question and answers!");
        Guard.againstNull(questionAnswerInputRequest.getQuestion(), "Enter a valid question.");
        Guard.againstNull(questionAnswerInputRequest.getAnswerOptions(), "Enter answers.");
        if (idNullCheck) {
            Guard.againstNull(questionAnswerInputRequest.getCategoryId(), "Enter a category ID.");
        }
        if (questionAnswerInputRequest.getAnswerOptions().size() < 3
            || questionAnswerInputRequest.getAnswerOptions().stream().noneMatch(AnswerInputRequest::isCorrect)) {
            throw new IllegalInputException("Enter at least three answer options. At least one of the answers has to be correct.");
        }
    }

}
