package com.chrispbacon.chrispbaconend.model;

import com.chrispbacon.chrispbaconend.model.category.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
public class Question {

    @Id
    private UUID id;
    @ManyToOne(optional = false)
    private LearningField learningField;
    @ManyToOne(optional = false)
    private Category category;
    private String question;
}
