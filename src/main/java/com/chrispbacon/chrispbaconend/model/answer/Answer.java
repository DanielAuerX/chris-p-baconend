package com.chrispbacon.chrispbaconend.model.answer;

import com.chrispbacon.chrispbaconend.model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class Answer {

    @Id
    private UUID id;
    @ManyToOne(optional = false)
    @JsonIgnore
    private Question question;
    private String answer;
    private boolean correct;
}
