package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.model.answer.AnswersDto;
import com.chrispbacon.chrispbaconend.model.CorrectionDto;
import com.chrispbacon.chrispbaconend.service.CorrectionService;
import com.chrispbacon.chrispbaconend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/chrispbacon/questionnaire")
@CrossOrigin
@RequiredArgsConstructor
public class QuestionnaireController {


    private final CorrectionService correctionService;
    private final QuestionService questionService;


    @GetMapping()
    public ResponseEntity<Object> getQuestion(@RequestParam Long categoryId) {
        return ResponseEntity.ok(questionService.getQuestionWithAnswers(categoryId));
    }

    @GetMapping("/questions")
    public ResponseEntity<Object> getQuestionsWithAnswers(@RequestParam Long categoryId) {
        return ResponseEntity.ok(questionService.getQuestionsWithAnswers(categoryId));
    }

    @GetMapping("/correction")
    public ResponseEntity<Object> validateQuestion(@RequestBody AnswersDto answersDto) {
        CorrectionDto correction = correctionService.evaluateAnswers(answersDto);
        return ResponseEntity.ok(correction);
    }
}
