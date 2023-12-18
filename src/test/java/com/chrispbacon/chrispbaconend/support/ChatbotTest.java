package com.chrispbacon.chrispbaconend.support;

import com.chrispbacon.chrispbaconend.config.JwtService;
import com.chrispbacon.chrispbaconend.repository.ChoiceRepository;
import com.chrispbacon.chrispbaconend.repository.PromptRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChatbotTest {

    @Mock
    private PromptRepository promptRepository;

    @Mock
    private ChoiceRepository choiceRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private Chatbot chatbot;

    @Test
    void getInitialPrompt() {
        Optional<Prompt> promptOptional = Optional.of(new Prompt(UUID.randomUUID(), "Hallo {username}!"));
        when(promptRepository.findById(any(UUID.class))).thenReturn(promptOptional);
        String bearer = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqQ29vbCIsImlhdCI6MTcwMjU1NTU4OCwiZXhwIjoxNzAyNjQxOTg4fQ.yjazhEHpDoP3qPSge8RXsVEjDiwp0_PtD8KZMY3DULQ";
        when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(bearer);
        String username = "D@arkL0rdJul!en";
        when(jwtService.extractUsername(any(String.class))).thenReturn(username);
        Choice choice1 = new Choice(UUID.randomUUID(), promptOptional.get(), "choice 1");
        Choice choice2 = new Choice(UUID.randomUUID(), promptOptional.get(), "choice 2");
        List<Choice> choices = List.of(choice1, choice2);
        when(choiceRepository.findAllByPromptId(any(UUID.class))).thenReturn(choices);

        PromptDto result = chatbot.getInitialPrompt(request);

        assertEquals(promptOptional.get().getId(), result.getPromptId());
        assertEquals("Hallo "+username+"!", result.getPromptText());
        assertEquals(choices.size(), result.getChoices().size());
    }

    @Test
    void getNextPrompt_withChoices() {
        UUID previousChoiceId = UUID.fromString("550e8400-e29b-41d4-a716-446655440011");
        String text = "Restart the computer!";
        Optional<Prompt> promptOptional = Optional.of(new Prompt(UUID.randomUUID(), text));
        when(promptRepository.findById(any(UUID.class))).thenReturn(promptOptional);
        Choice choice1 = new Choice(UUID.randomUUID(), promptOptional.get(), "choice 1");
        Choice choice2 = new Choice(UUID.randomUUID(), promptOptional.get(), "choice 2");
        List<Choice> choices = List.of(choice1, choice2);
        when(choiceRepository.findAllByPromptId(any(UUID.class))).thenReturn(choices);

        PromptDto result = chatbot.getNextPrompt(previousChoiceId);

        assertEquals(promptOptional.get().getId(), result.getPromptId());
        assertEquals(text, result.getPromptText());
        assertEquals(choices.size(), result.getChoices().size());
    }

    @Test
    void getNextPrompt_withoutChoices() {
        UUID previousChoiceId = UUID.fromString("550e8400-e29b-41d4-a716-446655440011");
        String text = "Restart the computer!";
        Optional<Prompt> promptOptional = Optional.of(new Prompt(UUID.randomUUID(), text));
        when(promptRepository.findById(any(UUID.class))).thenReturn(promptOptional);
        List<Choice> choices = Collections.emptyList();
        when(choiceRepository.findAllByPromptId(any(UUID.class))).thenReturn(choices);

        PromptDto result = chatbot.getNextPrompt(previousChoiceId);

        assertEquals(promptOptional.get().getId(), result.getPromptId());
        assertEquals(text, result.getPromptText());
        assertNull(result.getChoices());
    }
}