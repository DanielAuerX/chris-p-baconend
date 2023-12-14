package com.chrispbacon.chrispbaconend.support;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "chatbot_prompt")
public class Prompt {

    @Id
    private UUID id;
    private String text;

    public Prompt(UUID id, String text) {
        this.id = id;
        this.text = text;
    }

    public Prompt() {
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
