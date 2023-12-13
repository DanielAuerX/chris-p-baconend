package com.chrispbacon.chrispbaconend.chatbot;

import java.util.List;
import java.util.UUID;

public record PromptDto(UUID promptId, String promptText, List<Choice> choices) {
}
