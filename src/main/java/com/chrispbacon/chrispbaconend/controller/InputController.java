package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.model.QuestionAnswerInputRequest;
import com.chrispbacon.chrispbaconend.model.category.CategoryInputRequest;
import com.chrispbacon.chrispbaconend.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/chrispbacon/input")
@CrossOrigin
@RequiredArgsConstructor
public class InputController {

    private final InputService inputService;

    @PostMapping("/category")
    public ResponseEntity<Object> validateQuestion(@RequestBody CategoryInputRequest categoryInputRequest) {
        CategoryInputRequest savedRequest = inputService.insertIntoCategoryRequest(categoryInputRequest);
        return ResponseEntity.ok(savedRequest);
    }

    @PostMapping("/qa")
    public ResponseEntity<Object> validateQuestion(@RequestBody QuestionAnswerInputRequest questionAnswerInputRequest) {
        QuestionAnswerInputRequest savedRequest = inputService.insertQARequest(questionAnswerInputRequest);
        return ResponseEntity.ok(savedRequest);
    }
}
