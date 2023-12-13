package com.chrispbacon.chrispbaconend.chatbot;

import com.chrispbacon.chrispbaconend.repository.ChoiceRepository;
import com.chrispbacon.chrispbaconend.repository.PromptRepository;
import com.chrispbacon.chrispbaconend.util.Guard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class Chatbot {

    private final PromptRepository promptRepository;
    private final ChoiceRepository choiceRepository;

    public Chatbot(PromptRepository promptRepository, ChoiceRepository choiceRepository) {
        this.promptRepository = promptRepository;
        this.choiceRepository = choiceRepository;
    }

    public PromptDto getInitialPrompt() {
        UUID initialPromptId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        Prompt initialPrompt = promptRepository.findById(initialPromptId).orElseThrow();
        List<Choice> initialChoices = choiceRepository.findAllByPromptId(initialPromptId);
        Guard.againstEmptyList(initialChoices, "No choices found for prompt " + initialPromptId);
        return new PromptDto(initialPrompt.getId(), initialPrompt.getText(), initialChoices);
    }

}
