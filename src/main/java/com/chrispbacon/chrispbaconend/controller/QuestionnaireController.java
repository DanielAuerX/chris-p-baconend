package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.model.AnswersDto;
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
    public ResponseEntity<Object> getQuestion(@RequestParam Long category_id) {
        return ResponseEntity.ok(questionService.getQuestionWithAnswers(category_id));
    }

    @GetMapping("/correction")
    public ResponseEntity<Object> getQuestion(@RequestBody AnswersDto answersDto) {
        CorrectionDto correction = correctionService.evaluateAnswers(answersDto);
        return ResponseEntity.ok(correction);
    }
}
