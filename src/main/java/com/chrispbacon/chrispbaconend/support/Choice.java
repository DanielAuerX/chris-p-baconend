package com.chrispbacon.chrispbaconend.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity(name = "chatbot_choice")
public class Choice {
    @Id
    private UUID id;
    @ManyToOne(optional = false)
    @JsonIgnore
    private Prompt prompt;
    private String choiceText;

    public Choice(UUID id, Prompt prompt, String choiceText) {
        this.id = id;
        this.prompt = prompt;
        this.choiceText = choiceText;
    }

    public Choice() {
    }

    public UUID getId() {
        return id;
    }

    public String getChoiceText() {
        return choiceText;
    }
}
