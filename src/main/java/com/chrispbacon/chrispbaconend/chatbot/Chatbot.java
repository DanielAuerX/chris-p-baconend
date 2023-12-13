package com.chrispbacon.chrispbaconend.chatbot;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;
import com.chrispbacon.chrispbaconend.repository.ChoiceRepository;
import com.chrispbacon.chrispbaconend.repository.PromptRepository;
import com.chrispbacon.chrispbaconend.util.Guard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class Chatbot {

    private final PromptRepository promptRepository;
    private final ChoiceRepository choiceRepository;
    private final Map<UUID, UUID> choiceToPromptMapping;

    public Chatbot(PromptRepository promptRepository, ChoiceRepository choiceRepository) {
        this.promptRepository = promptRepository;
        this.choiceRepository = choiceRepository;
        this.choiceToPromptMapping = getChoiceToPromptMapping();
    }

    private Map<UUID, UUID> getChoiceToPromptMapping() {
        HashMap<UUID, UUID> choiceToPromptMapping = new HashMap<>();
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440011"), UUID.fromString("550e8400-e29b-41d4-a716-446655440001"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440012"), UUID.fromString("550e8400-e29b-41d4-a716-446655440001"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440013"), UUID.fromString("550e8400-e29b-41d4-a716-446655440001"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440017"), UUID.fromString("550e8400-e29b-41d4-a716-446655440002"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440018"), UUID.fromString("550e8400-e29b-41d4-a716-446655440003"));
        return choiceToPromptMapping;
    }

    public PromptDto getInitialPrompt() {
        UUID initialPromptId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        Prompt initialPrompt = promptRepository.findById(initialPromptId).orElseThrow();
        List<Choice> initialChoices = choiceRepository.findAllByPromptId(initialPromptId);
        Guard.againstEmptyList(initialChoices, "No choices found for prompt " + initialPrompt);
        return new PromptDto(initialPrompt.getId(), initialPrompt.getText(), initialChoices);
    }

    public PromptDto getNextPrompt(UUID previousChoiceId) {
        UUID nextPromptId = choiceToPromptMapping.get(previousChoiceId);
        Prompt initialPrompt = promptRepository.findById(nextPromptId).orElseThrow();
        List<Choice> nextChoices = choiceRepository.findAllByPromptId(nextPromptId);
        if (nextChoices.isEmpty()){
            return new PromptDto(initialPrompt.getId(), initialPrompt.getText());
        }
        return new PromptDto(initialPrompt.getId(), initialPrompt.getText(), nextChoices);
    }

}
