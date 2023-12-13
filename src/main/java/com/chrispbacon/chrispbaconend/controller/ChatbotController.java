package com.chrispbacon.chrispbaconend.controller;

import com.chrispbacon.chrispbaconend.chatbot.Chatbot;
import com.chrispbacon.chrispbaconend.chatbot.PromptDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/chrispbacon/support")
@CrossOrigin
public class ChatbotController {
    private final Chatbot chatbot;

    public ChatbotController(Chatbot chatbot) {
        this.chatbot = chatbot;
    }

    @GetMapping()
    public ResponseEntity<PromptDto> getInitialPrompt() {
        return ResponseEntity.ok(chatbot.getInitialPrompt());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromptDto> getNextPrompt(@PathVariable UUID id) {
        return ResponseEntity.ok(chatbot.getNextPrompt(id));
    }
}
