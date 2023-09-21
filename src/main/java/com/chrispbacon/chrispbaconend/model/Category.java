package com.chrispbacon.chrispbaconend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Category {

    @Id
    private long id;
    @ManyToOne(optional = false)
    private LearningField learningField;
    private String name;
    @Column(length = 1000)
    private String text;
}
