package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.model.InputSetRequest;
import com.chrispbacon.chrispbaconend.model.QuestionAnswerInputRequest;
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
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping("/category")
    public ResponseEntity<Object> receiveCategoryRequest(@RequestBody InputSetRequest inputSetRequest) {
        if (bucket.tryConsume(5)) {
            long id = inputService.handleCategoryRequest(inputSetRequest);
            return ResponseEntity.ok("Saved category request with id " + id + ".");
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @PostMapping("/qa")
    public ResponseEntity<Object> receiveQuestionRequest(@RequestBody QuestionAnswerInputRequest questionAnswerInputRequest) {
        if (bucket.tryConsume(1)) {
            long id = inputService.handleQARequest(questionAnswerInputRequest);
            return ResponseEntity.ok("Saved question request with id " + id + ".");
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
