package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.config.JwtService;
import com.chrispbacon.chrispbaconend.model.answer.AnswersDto;
import com.chrispbacon.chrispbaconend.service.CorrectionService;
import com.chrispbacon.chrispbaconend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/chrispbacon/questionnaire")
@CrossOrigin
@RequiredArgsConstructor
public class QuestionnaireController {


    private final CorrectionService correctionService;
    private final QuestionService questionService;
    private final JwtService jwtService;

    @GetMapping()
    public ResponseEntity<Object> getQuestionsWithAnswers(@RequestParam Long categoryId) {
        return ResponseEntity.ok(questionService.getQuestionsWithAnswers(categoryId));
    }

    @GetMapping("/random")
    public ResponseEntity<Object> getQuestionsWithAnswers(@RequestParam Long categoryId, @RequestParam Integer numberOfQuestions) {
        return ResponseEntity.ok(questionService.getRandomQuestionWithAnswers(categoryId, numberOfQuestions));
    }

    @GetMapping("/correction")
    public ResponseEntity<Object> validateQuestion(@RequestHeader("Authorization") String token, @RequestBody List<AnswersDto> answers) {
        token = token.split(" ")[1].trim();
        String username = jwtService.extractUsername(token);
        return ResponseEntity.ok(correctionService.evaluateAnswers(answers, username));
    }
}
