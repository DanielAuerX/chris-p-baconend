package com.chrispbacon.chrispbaconend.repository;

import com.chrispbacon.chrispbaconend.model.Category;
import com.chrispbacon.chrispbaconend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByLearningFieldId(long learningFieldId);
}
