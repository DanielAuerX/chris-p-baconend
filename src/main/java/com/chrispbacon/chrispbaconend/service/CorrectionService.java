package com.chrispbacon.chrispbaconend.service;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.model.CorrectionDto;
import com.chrispbacon.chrispbaconend.model.answer.AnswersDto;
import com.chrispbacon.chrispbaconend.model.question.Question;
import com.chrispbacon.chrispbaconend.model.user.Student;
import com.chrispbacon.chrispbaconend.repository.AnswerRepository;
import com.chrispbacon.chrispbaconend.repository.QuestionRepository;
import com.chrispbacon.chrispbaconend.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public List<CorrectionDto> evaluateAnswers(List<AnswersDto> answersDtos, String username) {
        List<CorrectionDto> corrections = new ArrayList<>(answersDtos.size());
        for (AnswersDto answersDto : answersDtos) {
            List<UUID> correctAnswers = getCorrectAnswerIds(answersDto);
            List<UUID> answersByUser = answersDto.answers();
            boolean isCorrect = new HashSet<>(answersByUser).equals(new HashSet<>(correctAnswers)); //ignores order
            corrections.add(new CorrectionDto(answersDto.questionId(), answersDto.answers(), correctAnswers, isCorrect));
        }
        saveResults(corrections, username);
        return corrections;
    }

    private void saveResults(List<CorrectionDto> corrections, String username) {
        if (corrections.stream().allMatch(CorrectionDto::isCorrect)) {
            Student user = userRepository.findByUserName(username)
                    .orElseThrow();
            Question question = questionRepository.findById(corrections.get(0).questionId()).orElseThrow();
            ArrayList<Long> finishedCategories = new ArrayList<>(user.getFinishedCategories());
            long categoryId = question.getCategory().getId();
            finishedCategories.add(categoryId);
            user.setFinishedCategories(finishedCategories);
            userRepository.save(user);
        }
    }

    private List<UUID> getCorrectAnswerIds(AnswersDto answersDto) {
        List<UUID> correctAnswers = answerRepository.findCorrectAnswerIdsByQuestionId(answersDto.questionId());
        if (correctAnswers == null || correctAnswers.isEmpty()) {
            throw new IllegalInputException("The db did not return any correct answers for this question. Check if the question id " + answersDto.questionId() + " is correct and if the db contains answers for the question.");
        }
        return correctAnswers;
    }
}
