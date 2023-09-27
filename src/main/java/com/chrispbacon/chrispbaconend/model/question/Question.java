package com.chrispbacon.chrispbaconend.model.question;

import com.chrispbacon.chrispbaconend.model.LearningField;
import com.chrispbacon.chrispbaconend.model.category.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    private UUID id;
    @ManyToOne(optional = false)
    private LearningField learningField;
    @ManyToOne(optional = false)
    private Category category;
    private String question;
}
