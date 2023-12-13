package com.chrispbacon.chrispbaconend.chatbot;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromptDto {
    private final UUID promptId;
    private final String promptText;
    private final List<Choice> choices;

    public PromptDto(UUID promptId, String promptText) {
        this.promptId = promptId;
        this.promptText = promptText;
        this.choices = null;
    }

    public PromptDto(UUID promptId, String promptText, List<Choice> choices) {
        this.promptId = promptId;
        this.promptText = promptText;
        this.choices = choices;
    }

    public UUID getPromptId() {
        return promptId;
    }

    public String getPromptText() {
        return promptText;
    }

    public List<Choice> getChoices() {
        return choices;
    }
}

