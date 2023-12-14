package com.chrispbacon.chrispbaconend.support;

import com.chrispbacon.chrispbaconend.repository.ChoiceRepository;
import com.chrispbacon.chrispbaconend.repository.PromptRepository;
import com.chrispbacon.chrispbaconend.util.Guard;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
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
        String askForProblem = "550e8400-e29b-41d4-a716-446655440001";
        String delegateToNonNpc = "550e8400-e29b-41d4-a716-446655440002";
        String sayBye = "550e8400-e29b-41d4-a716-446655440101";
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440011"), UUID.fromString(askForProblem));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440012"), UUID.fromString(askForProblem));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440013"), UUID.fromString(askForProblem));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440017"), UUID.fromString(delegateToNonNpc));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440018"), UUID.fromString("550e8400-e29b-41d4-a716-446655440003"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440016"), UUID.fromString("550e8400-e29b-41d4-a716-446655440004"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440020"), UUID.fromString(delegateToNonNpc));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440021"), UUID.fromString(delegateToNonNpc));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440015"), UUID.fromString("550e8400-e29b-41d4-a716-446655440006"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440023"), UUID.fromString(delegateToNonNpc));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440022"), UUID.fromString("550e8400-e29b-41d4-a716-446655440007"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440014"), UUID.fromString("550e8400-e29b-41d4-a716-446655440008"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440024"), UUID.fromString("550e8400-e29b-41d4-a716-446655440009"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440025"), UUID.fromString("550e8400-e29b-41d4-a716-446655440100"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440019"), UUID.fromString("550e8400-e29b-41d4-a716-446655440005"));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440026"), UUID.fromString(delegateToNonNpc));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440027"), UUID.fromString(sayBye));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440029"), UUID.fromString(delegateToNonNpc));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440028"), UUID.fromString(sayBye));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440030"), UUID.fromString(sayBye));
        choiceToPromptMapping.put(UUID.fromString("550e8400-e29b-41d4-a716-446655440031"), UUID.fromString(delegateToNonNpc));
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
