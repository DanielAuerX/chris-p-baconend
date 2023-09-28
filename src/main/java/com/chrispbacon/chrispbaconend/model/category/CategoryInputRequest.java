package com.chrispbacon.chrispbaconend.model.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "category_request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInputRequest {
    @Id
    @GeneratedValue(generator = "category_seq")
    private Long id;
    private Long learningFieldId;
    private String categoryName;
    private String categoryDescription;
    private String categoryText;

    public CategoryInputRequest(Long learningFieldId, String categoryName, String categoryDescription, String categoryText) {
        this.learningFieldId = learningFieldId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryText = categoryText;
    }

}
