package com.chrispbacon.chrispbaconend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class LearningField {

    @Id
    private long id;
    private String name;
}
