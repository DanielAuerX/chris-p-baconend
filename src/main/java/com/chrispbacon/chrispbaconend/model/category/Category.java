package com.chrispbacon.chrispbaconend.model.category;

import com.chrispbacon.chrispbaconend.model.LearningField;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    public static final int MAX_TEXT_LENGTH = 4000;

    @Id
    @GeneratedValue(generator = "category_seq")
    private long id;
    @ManyToOne(optional = false)
    private LearningField learningField;
    private String name;
    private String description;
    @Column(length = MAX_TEXT_LENGTH)
    private String text;
}
