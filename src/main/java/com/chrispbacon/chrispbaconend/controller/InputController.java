package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.model.QuestionAnswerInputRequest;
import com.chrispbacon.chrispbaconend.model.category.CategoryInputRequest;
import com.chrispbacon.chrispbaconend.service.InputService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("api/chrispbacon/input")
@CrossOrigin
public class InputController {

    private final InputService inputService;
    private final Bucket bucket;

    public InputController(InputService inputService) {
        this.inputService = inputService;
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(5)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping("/category")
    public ResponseEntity<Object> validateQuestion(@RequestBody CategoryInputRequest categoryInputRequest) {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(inputService.saveCategoryRequest(categoryInputRequest));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @PostMapping("/qa")
    public ResponseEntity<Object> validateQuestion(@RequestBody QuestionAnswerInputRequest questionAnswerInputRequest) {
        if (bucket.tryConsume(1)) {
            long id = inputService.saveQARequest(questionAnswerInputRequest);
            return ResponseEntity.ok("Saved request with id " + id + ".");
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
