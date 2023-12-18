package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.support.Chatbot;
import com.chrispbacon.chrispbaconend.support.PromptDto;
import com.chrispbacon.chrispbaconend.support.TicketDto;
import com.chrispbacon.chrispbaconend.support.TicketService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("api/chrispbacon/support")
@CrossOrigin
public class SupportController {
    private final Chatbot chatbot;
    private final TicketService ticketService;
    private final Bucket bucket;

    public SupportController(Chatbot chatbot, TicketService ticketService) {
        this.chatbot = chatbot;
        this.ticketService = ticketService;
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping()
    public ResponseEntity<PromptDto> getInitialPrompt(HttpServletRequest request) {
        return ResponseEntity.ok(chatbot.getInitialPrompt(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromptDto> getNextPrompt(@PathVariable UUID id) {
        return ResponseEntity.ok(chatbot.getNextPrompt(id));
    }

    @PostMapping("/ticket")
    public ResponseEntity<Object> createTicket(@RequestBody TicketDto ticketDto, HttpServletRequest request) {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(ticketService.createGitHubIssue(ticketDto, request));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

}
